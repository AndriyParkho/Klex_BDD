delete from Album;
delete from AlbumAPourCategorie;
delete from APourInstrument;
delete from APourRole;
delete from Artiste;
delete from CategorieFilm;
delete from CategorieMusique;
delete from Client;
delete from Codec;
delete from EstUnePiste;
delete from EstUnFilm;
delete from Fichier;
delete from Film;
delete from FilmAPourCategorie;
delete from Flux;
delete from FluxAudio;
delete from FluxVideo;
delete from FluxTexte;
delete from ImgExtraiteFilm;
delete from Piste;
delete from PisteAPourCategorie;
delete from SupporteCodec;
delete from Utilisateur;

insert into Utilisateur values (
    'theo.manfredi@grenoble-inp.fr',
    'Manfredi',
    'Theo',
    22,
    'Français',
    0123
);

insert into Utilisateur values (
    'nicolas.vincent@grenoble-inp.fr',
    'Vincent',
    'Nicolas',
    22,
    'Français',
    7777
);

insert into Utilisateur values (
    'madelines.salles@grenoble-inp.fr',
    'Salles',
    'Madelines',
    21,
    'Français',
    1999
);

insert into Utilisateur values (
    'adrien.argento@grenoble-inp.fr',
    'Argento',
    'Adrien',
    22,
    'Français',
    7402
);

insert into Utilisateur values (
    'andriy.parkhomenko@grenoble-inp.fr',
    'Parkhomenko',
    'Andriy',
    22,
    'Français',
    9387
);

insert into Fichier values (
    1,
    25000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);

insert into Fichier values (
    2,
    87000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);

insert into Fichier values (
    3,
    309000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);

insert into Fichier values (
    4,
    309000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);

insert into Fichier values (
    5,
    309000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);


insert into Fichier values (
    6,
    309000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);


insert into Fichier values (
    7,
    309000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);


insert into Fichier values (
    8,
    309000,
    to_date('07/03/2020', 'dd/mm/yyyy'),
    'theo.manfredi@grenoble-inp.fr'
);

INSERT INTO "ALBUM" ("IDALBUM", "TITREALBUM", "NOMGROUPE", "DATESORTIEALBUM", "URLIMAGEPOCHETTE")
VALUES ('1', 'Hera', 'Georgio', to_date('01/01/2016', 'dd/mm/yyyy'), 'www.photoalbumhera.com');

INSERT INTO "ALBUM" ("IDALBUM", "TITREALBUM", "NOMGROUPE", "DATESORTIEALBUM", "URLIMAGEPOCHETTE")
VALUES ('2', 'Inconnu', 'Georgio', to_date('01/01/2021', 'dd/mm/yyyy'), 'www.photoalbumhera2.com');

INSERT INTO CategorieMusique
VALUES ('rap');

INSERT INTO CategorieMusique
VALUES ('electro');

INSERT INTO CategorieFilm
VALUES ('action');

INSERT INTO CategorieFilm
VALUES ('horreur');

INSERT INTO CategorieFilm
VALUES ('sci-fi');

INSERT INTO CategorieFilm
VALUES ('fantastique');

INSERT INTO CategorieFilm
VALUES ('comédie');

INSERT INTO CategorieFilm
VALUES ('comédie musicale');

INSERT INTO CategorieFilm
VALUES ('blockbuster');

INSERT INTO CategorieFilm
VALUES ('thriller');

INSERT INTO CategorieFilm
VALUES ('romantique');

INSERT INTO AlbumAPourCategorie
VALUES (1, 'rap');

INSERT INTO AlbumAPourCategorie
VALUES (2, 'rap');

INSERT INTO AlbumAPourCategorie
VALUES (2, 'electro');

INSERT INTO Artiste (idArtiste, nomArtiste, dateNaissance, urlPhoto, specialite, biographie)
VALUES ('1', 'Georgio', to_date('04/11/1987', 'dd/mm/yyyy'), 'https://static.wikia.nocookie.net/mario/images/b/bc/NSMBGoomba.png/revision/latest?cb=20170428120903&path-prefix=fr', 'Rappeur', 'Le meilleur rappeur');

INSERT INTO Artiste (idArtiste, nomArtiste, dateNaissance, urlPhoto, specialite, biographie)
VALUES ('2', 'DiCaprio', to_date('04/11/1967', 'dd/mm/yyyy'), 'https://static.wikia.nocookie.net/mario/images/b/bc/NSMBGoomba.png/revision/latest?cb=20170428120903&path-prefix=fr', 'Realisateur', 'Le meilleur realisateur');

INSERT INTO Client
VALUES ('Sony', 'M1', 720, 720);

INSERT INTO Client
VALUES ('Sony2', 'M2', 1080, 720);

INSERT INTO Client
VALUES ('Sony3', 'M3', 1920, 720);

INSERT INTO Client
VALUES ('Sony4', 'M4', 1920, 1080);

INSERT INTO Client
VALUES ('Sony5', 'M5', 1920, 1920);

INSERT INTO Codec
VALUES ('MPEG2', 'video');

INSERT INTO Codec
VALUES ('MPEG4', 'video');

INSERT INTO Codec
VALUES ('DivX', 'video');

INSERT INTO Codec
VALUES ('H264', 'video');

INSERT INTO Codec
VALUES ('MP3', 'audio');

INSERT INTO Codec
VALUES ('MP4', 'video');

INSERT INTO Codec
VALUES ('MPEG4', 'audio');

INSERT INTO Codec
VALUES ('ACC', 'audio');

INSERT INTO Codec
VALUES ('ACC3', 'audio');

INSERT INTO Codec
VALUES ('AQTitle', 'texte');

INSERT INTO Codec
VALUES ('DKS', 'texte');

INSERT INTO Codec
VALUES ('Kate', 'texte');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (1, 20, 1, 'MP3', 'audio');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (2, 20, 2, 'MP3', 'audio');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (3, 20, 3, 'MP3', 'audio');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (4, 20, 4, 'MP4', 'video');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (5, 20, 5, 'MP4', 'video');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (6, 20, 6, 'MP4', 'video');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (7, 20, 7, 'MP4', 'video');

INSERT INTO Flux (idFlux, debit, idFichier, nomCodec, typeCodec)
VALUES (8, 20, 8, 'MP4', 'video');

INSERT INTO FluxAudio
VALUES (1, 20, 1, 'MP3', 'audio', 16, 'Français');

INSERT INTO FluxAudio
VALUES (2, 20, 2, 'MP3', 'audio', 16, 'Français');

INSERT INTO FluxAudio
VALUES (3, 20, 3, 'MP3', 'audio', 16, 'Français');

INSERT INTO Piste (numPiste, titrePiste, dureePiste, idAlbum, idFichier)
VALUES (1, 'Hera', INTERVAL '03:23' MINUTE TO SECOND, 1, 1);

INSERT INTO Piste (numPiste, titrePiste, dureePiste, idAlbum, idFichier)
VALUES (2, 'Brule', INTERVAL '04:12' MINUTE TO SECOND, 1, 2);

INSERT INTO Piste (numPiste, titrePiste, dureePiste, idAlbum, idFichier)
VALUES (1, 'Les Anges Dans Des Robes Rouges', INTERVAL '03:56' MINUTE TO SECOND, 2, 3);

INSERT INTO PisteAPourCategorie
VALUES (1, 1, 'electro');

INSERT INTO PisteAPourCategorie
VALUES (2, 1, 'rap');

INSERT INTO PisteAPourCategorie
VALUES (1, 2, 'rap');

INSERT INTO SupporteCodec
VALUES ('Sony', 'M1', 'MPEG2', 'video');

INSERT INTO SupporteCodec
VALUES ('Sony', 'M1', 'MPEG4', 'video');

INSERT INTO SupporteCodec
VALUES ('Sony', 'M1', 'MP3', 'audio');

INSERT INTO SupporteCodec
VALUES ('Sony', 'M1', 'MPEG4', 'audio');

INSERT INTO SupporteCodec
VALUES ('Sony', 'M1', 'AQTitle', 'texte');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'DivX', 'video');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'H264', 'video');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'MP3', 'audio');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'ACC', 'audio');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'ACC3', 'audio');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'DKS', 'texte');

INSERT INTO SupporteCodec
VALUES ('Sony2', 'M2', 'Kate', 'texte');

INSERT INTO EstUnePiste
VALUES (1, 1, 1);

INSERT INTO EstUnePiste
VALUES (2, 2, 1);

INSERT INTO EstUnePiste
VALUES (3, 1, 2);

INSERT INTO APourInstrument
VALUES ('Voix', 1, 1, 1);

INSERT INTO APourInstrument
VALUES ('Voix', 1, 1, 2);

INSERT INTO APourInstrument
VALUES ('Voix', 1, 2, 1);

INSERT INTO Film
VALUES ('Forrest Gump', to_date('03/11/1994', 'dd/mm/yyyy'), 
'Quelques décennies d''histoire américaine, des années 1940 à la fin du XXème siècle, à travers le regard et l''étrange odyssée d''un homme simple et pur, Forrest Gump.',
1,
'https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcT0NxVrYdi8Ro5zRnIbewaT_uCOl5_2uhxxyQoU6s9gOfEDovGi');

INSERT INTO Film
VALUES ('La Ligne Verte', to_date('03/11/1999', 'dd/mm/yyyy'), 
'Paul Edgecomb, Gardien-chef du pénitencier de Cold Mountain en 1935, était chargé de veiller au bon déroulement des exécutions capitales. Parmi les prisonniers se trouvait un colosse du nom de John Coffey...',
12,
'https://fr.web.img4.acsta.net/medias/nmedia/18/66/15/78/19254683.jpg');

INSERT INTO Film
VALUES ('La Liste de Schindler', to_date('03/11/1993', 'dd/mm/yyyy'), 
'Evocation des années de guerre d''Oskar Schindler, industriel autrichien rentré à Cracovie en 1939 avec les troupes allemandes. Il va, tout au long de la guerre, protéger des juifs en les faisant travailler dans sa fabrique.',
1,
'https://fr.web.img6.acsta.net/pictures/19/03/14/10/37/5927961.jpg');

INSERT INTO Film
VALUES ('Le Parrain', to_date('03/11/1972', 'dd/mm/yyyy'), 
'En 1945, à New York, les Corleone sont une des cinq familles de la mafia. Don Vito Corleone marie sa fille à un bookmaker. Sollozzo, ''parrain'' de la famille Tattaglia, propose à Don Vito une association dans le trafic de drogue...',
10,
'https://fr.web.img4.acsta.net/medias/nmedia/18/35/57/73/18660716.jpg');

INSERT INTO Film
VALUES ('12 Hommes en colère', to_date('03/11/1957', 'dd/mm/yyyy'), 
'Lors d''un procès, un juré émet l''hypothèse que l''homme qu''il doit juger n''est peut-être pas coupable. Il va tenter de convaincre les onze autres jurés.',
10,
'https://fr.web.img3.acsta.net/medias/nmedia/18/35/24/09/18784292.jpg');

INSERT INTO APourRole 
VALUES ('Producteur', 'La Liste de Schindler', to_date('03/11/1993', 'dd/mm/yyyy'), 2);

INSERT INTO APourRole 
VALUES ('Triangle', 'Le Parrain', to_date('03/11/1972', 'dd/mm/yyyy'), 2);

INSERT INTO FilmAPourCategorie
VALUES ('Forrest Gump', to_date('03/11/1994', 'dd/mm/yyyy'), 'comédie');

INSERT INTO FilmAPourCategorie
VALUES ('Forrest Gump', to_date('03/11/1994', 'dd/mm/yyyy'), 'comédie musicale');

INSERT INTO FilmAPourCategorie
VALUES ('Forrest Gump', to_date('03/11/1994', 'dd/mm/yyyy'), 'romantique');

INSERT INTO FilmAPourCategorie
VALUES ('La Liste de Schindler', to_date('03/11/1993', 'dd/mm/yyyy'), 'thriller');

INSERT INTO FilmAPourCategorie
VALUES ('Le Parrain', to_date('03/11/1972', 'dd/mm/yyyy'), 'action');

INSERT INTO FilmAPourCategorie
VALUES ('Le Parrain', to_date('03/11/1972', 'dd/mm/yyyy'), 'thriller');

INSERT INTO ImgExtraiteFilm
VALUES ('https://upload.wikimedia.org/wikipedia/commons/6/6c/GarySiniseDanTaylorForrestGump94.jpg', 'Forrest Gump', to_date('03/11/1994', 'dd/mm/yyyy'));

INSERT INTO FluxVideo
VALUES (4, 20, 4, 'MP4', 'video', 1920, 1080);

INSERT INTO FluxVideo
VALUES (5, 20, 5, 'MP4', 'video', 1920, 1080);

INSERT INTO FluxVideo
VALUES (6, 20, 6, 'MP4', 'video', 1920, 1080);

INSERT INTO FluxVideo
VALUES (7, 20, 7, 'MP4', 'video', 1920, 1080);

INSERT INTO FluxVideo
VALUES (8, 20, 8, 'MP4', 'video', 1920, 1080);

INSERT INTO EstUnFilm
VALUES (4, 'Forrest Gump', to_date('03/11/1994', 'dd/mm/yyyy'));

INSERT INTO EstUnFilm
VALUES (5, 'La Ligne Verte', to_date('03/11/1999', 'dd/mm/yyyy'));

INSERT INTO EstUnFilm
VALUES (6, 'La Liste de Schindler', to_date('03/11/1993', 'dd/mm/yyyy'));

INSERT INTO EstUnFilm
VALUES (7, 'Le Parrain', to_date('03/11/1972', 'dd/mm/yyyy'));

INSERT INTO EstUnFilm
VALUES (8, '12 Hommes en colère', to_date('03/11/1957', 'dd/mm/yyyy'));
