package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import connections.JDBCUtilities;
import model.Artiste;
import model.CategorieFilm;
import model.Film;
import model.ImgExtraiteFilm;

public class DAOFilm extends DAO<Film> {

    @Override
    public void create(Film film) throws SQLException {
        final String insertFilmQuery = "INSERT INTO Film VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statementFilm = this.connection.prepareStatement(insertFilmQuery)) {
            statementFilm.setString(1, film.getTitreFilm());
            statementFilm.setDate(2, film.getAnneeSortie());
            statementFilm.setString(3, film.getResume());
            statementFilm.setInt(4, film.getAgeMin());
            statementFilm.setString(5, film.getUrlAffiche());
            statementFilm.executeUpdate();
        }
        // connection.commit();

        // on doit créer les catégories
        for (CategorieFilm categorieFilm : film.getCategoriesFilm()) {
            DAOFactory.getCategorieFilmDAO().createOrUpdate(categorieFilm);
        }
        // connection.commit();

        // connection.commit(); // because idArtiste RETURNED INTO :6
        // on doit créer les artistes
        for (Artiste artiste : film.getArtistes().keySet()) {
            DAOFactory.getArtisteDAO().create(artiste);
        }

        // NECESSARY because ImgExtraiteFilm references Film (titreFilm, anneeSortie)
        // NECESSARY because FilmAPourCategorie references CategorieFilm
        // (typeCategorieFilm) and Film (titreFilm, anneeSortie)
        // NECESSARY because APourRole references Artiste (idArtiste) and Film
        // (titreFilm, anneeSortie)
        // connection.commit();

        // on doit créer les ImgExtraitesFilm
        for (ImgExtraiteFilm imgExtraiteFilm : film.getImgExtraitesFilm()) {
            DAOFactory.getImgExtraiteFilmDAO().createOrUpdate(imgExtraiteFilm);
        }
        // connection.commit();

        for (Map.Entry<Artiste, String> entry : film.getArtistes().entrySet()) {
            // insertion dans la table intermédiaire, c'est un nouveau film donc on est sur
            // que le couple n'existe pas
            createAPourRole(entry.getValue(), film, entry.getKey());
        }
        // connection.commit();

        for (CategorieFilm categorieFilm : film.getCategoriesFilm()) {
            // insertion dans la table intermédiaire, c'est un nouveau film mais la
            // catégorie peut exister
            createFilmAPourCategorie(film, categorieFilm);
        }
    }

    @Override
    public void createOrUpdate(Film film) throws SQLException {
        try {
            this.create(film);
        } catch (final SQLIntegrityConstraintViolationException e) {
            if (e.getErrorCode() != 1) {
                JDBCUtilities.printSQLException(e);
            } else {
                System.out.println(film + " est déjà dans la BDD. On l'update.");
                this.update(film);
            }
        }
    }

    @Override
    public Film find(Film film) throws SQLException {
        return this.find(film.getTitreFilm(), film.getAnneeSortie());
    }

    public Film find(String titreFilm, Date anneeSortie) throws SQLException {
        Film film = null;
        // jointures sur ImgExtraiteFilm, FilmAPourCategorie/CategorieFilm et
        // APourRole/Artiste
        final String query = "SELECT * FROM Film LEFT JOIN ImgExtraiteFilm ON Film.titreFilm = ImgExtraiteFilm.titreFilm AND Film.anneeSortie = ImgExtraiteFilm.anneeSortie AND Film.titreFilm = '"
                + titreFilm + "' AND Film.anneeSortie = TO_DATE('" + anneeSortie
                + "', 'YYYY-MM-DD') LEFT JOIN FilmAPourCategorie ON Film.titreFilm = FilmAPourCategorie.titreFilm AND Film.anneeSortie = FilmAPourCategorie.anneeSortie AND Film.titreFilm = '"
                + titreFilm + "' AND Film.anneeSortie = TO_DATE('" + anneeSortie
                + "', 'YYYY-MM-DD') INNER JOIN CategorieFilm ON CategorieFilm.typeCategorieFilm = FilmAPourCategorie.typeCategorieFilm"
                + " LEFT JOIN APourRole ON Film.titreFilm = APourRole.titreFilm AND Film.anneeSortie = APourRole.anneeSortie AND Film.titreFilm = '"
                + titreFilm + "' AND Film.anneeSortie = TO_DATE('" + anneeSortie
                + "', 'YYYY-MM-DD') INNER JOIN Artiste ON Artiste.idArtiste = APourRole.idArtiste";
        try (ResultSet rs = this.connection
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(query)) {
            // le ResultSet n'est pas vide, on construit un nouvel objet qui contient les
            // attributs de la ligne
            if (rs.first()) {
                final HashSet<CategorieFilm> categoriesFilm = new HashSet<CategorieFilm>();
                final HashSet<ImgExtraiteFilm> imgExtraitesFilm = new HashSet<ImgExtraiteFilm>();
                final HashMap<Artiste, String> artistes = new HashMap<Artiste, String>();

                // System.out.println(JDBCUtilities.dumpResultSet(rs) + "\n");

                rs.beforeFirst();
                while (rs.next()) {
                    if (rs.getString("typeCategorieFilm") != null)
                        categoriesFilm.add(DAOFactory.getCategorieFilmDAO().find(rs.getString("typeCategorieFilm")));
                    if (rs.getString("urlImage") != null)
                        imgExtraitesFilm.add(DAOFactory.getImgExtraiteFilmDAO().find(rs.getString("urlImage")));
                    if (rs.getString("idArtiste") != null)
                        artistes.put(DAOFactory.getArtisteDAO().find(rs.getLong("idArtiste")),
                                rs.getString("roleFilm"));
                }

                // on se replace
                rs.first();
                film = new Film(rs.getString("titreFilm"), rs.getDate("anneeSortie"), rs.getString("resume"),
                        rs.getInt("ageMin"), rs.getString("urlAffiche"), categoriesFilm, imgExtraitesFilm, artistes);
            }
        }
        connection.commit();

        return film;
    }

    @Override
    public void update(Film film) throws SQLException {
        // TODO Auto-generated method stub
    }

    @Override
    public void delete(Film film) throws SQLException {
        // TODO Auto-generated method stub

    }

    private void createFilmAPourCategorie(Film film, CategorieFilm categorieFilm) throws SQLException {
        final String insertFilmAPourCategorieQuery = "INSERT INTO FilmAPourCategorie VALUES (?, ?, ?)";
        try (PreparedStatement statementFilmAPourCategorie = this.connection
                .prepareStatement(insertFilmAPourCategorieQuery)) {
            statementFilmAPourCategorie.setString(1, film.getTitreFilm());
            statementFilmAPourCategorie.setDate(2, film.getAnneeSortie());
            statementFilmAPourCategorie.setString(3, categorieFilm.getCategorie());
            statementFilmAPourCategorie.executeUpdate();
        }
    }

    private void createAPourRole(String role, Film film, Artiste artiste) throws SQLException {
        final String insertAPourRoleQuery = "INSERT INTO APourRole VALUES (?, ?, ?, ?)";
        try (PreparedStatement statementAPourRole = this.connection.prepareStatement(insertAPourRoleQuery)) {
            statementAPourRole.setString(1, role);
            statementAPourRole.setString(2, film.getTitreFilm());
            statementAPourRole.setDate(3, film.getAnneeSortie());
            statementAPourRole.setLong(4, artiste.getId());
            statementAPourRole.executeUpdate();
        }
    }
}
