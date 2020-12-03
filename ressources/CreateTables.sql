CREATE TABLE CategorieMusique(
  typeCategorieMusique varchar(50) NOT NULL,
  CONSTRAINT pkCategorieMusique PRIMARY KEY (typeCategorieMusique)
);

CREATE TABLE Album(
  idAlbum integer GENERATED ALWAYS AS IDENTITY,
  titreAlbum varchar(50) NOT NULL,
  nomGroupe varchar(50) NOT NULL,
  dateSortieAlbum date NOT NULL,
  urlImagePochette varchar(100) NOT NULL,
  CONSTRAINT pkAlbum PRIMARY KEY (idAlbum)
);

CREATE TABLE Utilisateur(
  email varchar(255) NOT NULL,
  nom varchar(50) NOT NULL,
  prenom varchar(30) NOT NULL,
  age integer NOT NULL CHECK (age > 0),
  langueDiffusion varchar(100) NOT NULL CHECK (langueDiffusion in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
  code integer NOT NULL CHECK (code BETWEEN 0000 AND 9999), -- check que le code est bien constitué que de 4 chiffres
  CONSTRAINT pkUtilisateur PRIMARY KEY (email)
);

CREATE TABLE Fichier(
  idFichier integer GENERATED ALWAYS AS IDENTITY,
  taille integer NOT NULL,
  dateDepot DATE NOT NULL,
  email varchar(255) NOT NULL REFERENCES Utilisateur (email) ON DELETE CASCADE, -- la suppression d'un utilisateur supprime ses fichiers
  CONSTRAINT pkFichier PRIMARY KEY (idFichier)
);

CREATE TABLE Piste(
  idPiste integer GENERATED ALWAYS AS IDENTITY,
  numPiste integer NOT NULL,
  titrePiste varchar(50) NOT NULL,
  dureePiste interval day (0) to second(0) NOT NULL, -- select only hour-min-seconds, see REGEXP_SUBSTR
  idAlbum integer NOT NULL REFERENCES Album (idAlbum) ON DELETE CASCADE, -- la suppression d'un album supprime ses pistes
  idFichier integer NOT NULL REFERENCES Fichier (idFichier) ON DELETE CASCADE, -- la suppression d'un fichier supprime la piste associée
  CONSTRAINT pkPiste PRIMARY KEY (idPiste, idAlbum)
);

CREATE TABLE PisteAPourCategorie(
  idPiste integer NOT NULL,
  idAlbum integer NOT NULL,
  typeCategorieMusique varchar(20) NOT NULL REFERENCES CategorieMusique (typeCategorieMusique) ON DELETE CASCADE,
  CONSTRAINT fkPisteAPourCategoriePiste FOREIGN KEY (idPiste, idAlbum) REFERENCES Piste (idPiste, idAlbum) ON DELETE CASCADE,
  CONSTRAINT pkPisteAPourCategorie PRIMARY KEY (idPiste, idAlbum, typeCategorieMusique)
);

CREATE TABLE EstUnePiste(
  idFichier integer NOT NULL REFERENCES Fichier (idFichier) ON DELETE CASCADE,
  idPiste integer NOT NULL,
  idAlbum integer NOT NULL,
  CONSTRAINT fkEstUnePistePiste FOREIGN KEY (idPiste, idAlbum) REFERENCES Piste (idPiste, idAlbum) ON DELETE CASCADE,
  CONSTRAINT pkEstUnePiste PRIMARY KEY (idFichier)
);

CREATE TABLE AlbumAPourCategorie(
  idAlbum integer NOT NULL REFERENCES Album (idAlbum) ON DELETE CASCADE,
  typeCategorieMusique varchar(20) NOT NULL REFERENCES CategorieMusique (typeCategorieMusique) ON DELETE CASCADE,
  CONSTRAINT pkAlbumAPourCategorie PRIMARY KEY (idAlbum, typeCategorieMusique)
);

CREATE TABLE Codec(
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL check(typeCodec in ('audio', 'video', 'texte')),
  CONSTRAINT pkCodec PRIMARY KEY (nomCodec, typeCodec)
);

CREATE TABLE Client(
  marque varchar(20) NOT NULL,
  modele varchar(20) NOT NULL,
  largeurMax integer NOT NULL,
  hauteurMax integer NOT NULL,
  CONSTRAINT pkClient PRIMARY KEY (marque, modele)
);

CREATE TABLE SupporteCodec(
  marque varchar(20) NOT NULL,
  modele varchar(20) NOT NULL,
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL, 
  CONSTRAINT pkSupporteCodec PRIMARY KEY (marque, modele, nomCodec, typeCodec),
  CONSTRAINT fkClient FOREIGN KEY (marque, modele) REFERENCES Client (marque, modele) ON DELETE CASCADE,
  CONSTRAINT fkCodecSupporteCodec FOREIGN KEY (nomCodec, typeCodec) REFERENCES Codec (nomCodec, typeCodec) ON DELETE CASCADE
);

CREATE TABLE Artiste(
  idArtiste integer GENERATED ALWAYS AS IDENTITY,
  nomArtiste varchar(50) NOT NULL,
  dateNaissance date,
  urlPhoto varchar(150) NOT NULL,
  specialite varchar(50) NOT NULL,
  biographie CLOB,
  CONSTRAINT pkArtiste PRIMARY KEY (idArtiste)
);

CREATE TABLE CategorieFilm(
  typeCategorieFilm varchar(255) NOT NULL,
  CONSTRAINT pkCategorieFilm PRIMARY KEY (typeCategorieFilm)
);

/*
Il faut peut-être rajouter une contrainte de valeur pour anneeSortie
Il faut vérifier que resume existe pour Oracle sinon VARCHAR(65000)
ageMin peut être null ?
Si le type TEXT fonctionne pas pour Oracle utiliser VARCHAR(2083)
*/
CREATE TABLE Film(
  titreFilm varchar(1000) NOT NULL,
  anneeSortie date NOT NULL,
  resume CLOB NOT NULL,
  ageMin integer NOT NULL CHECK (ageMin > 0),
  urlAffiche varchar(1000) NOT NULL,
  CONSTRAINT pkFilm PRIMARY KEY (titreFilm, anneeSortie)
);

CREATE TABLE FilmAPourCategorie(
  titreFilm varchar(1000) NOT NULL,
  anneeSortie date NOT NULL,
  typeCategorieFilm varchar(255) NOT NULL REFERENCES CategorieFilm (typeCategorieFilm) ON DELETE CASCADE,
  CONSTRAINT fkFilmAPourCategorie FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie) ON DELETE CASCADE,
  CONSTRAINT pkFilmAPourCategorie PRIMARY KEY (titreFilm, anneeSortie, typeCategorieFilm)
);

CREATE TABLE EstUnFilm(
  idFichier integer NOT NULL REFERENCES Fichier (idFichier) ON DELETE CASCADE,
  titreFilm varchar(1000) NOT NULL,
  anneeSortie date NOT NULL,
  CONSTRAINT fkEstUnFilmFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie) ON DELETE CASCADE,
  CONSTRAINT pkEstUnFilm PRIMARY KEY (idFichier)
);

CREATE TABLE ImgExtraiteFilm(
  urlImage varchar(1000) NOT NULL,
  titreFilm varchar(1000) NOT NULL,
  anneeSortie date NOT NULL,
  CONSTRAINT fkImgExtraiteFilmFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie) ON DELETE CASCADE,
  CONSTRAINT pkImgExtraiteFilm PRIMARY KEY (urlImage)
);

/*
verifier qu'un film est associé à au moins un artiste
vérifier le format year de anneeSortie
*/
CREATE TABLE APourRole(
  roleFilm varchar(50) NOT NULL,
  titreFilm varchar(50) NOT NULL,
  anneeSortie date NOT NULL,
  idArtiste integer NOT NULL REFERENCES Artiste (idArtiste) ON DELETE CASCADE,
  CONSTRAINT fkAPourRoleFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie) ON DELETE CASCADE,
  CONSTRAINT pkAPourRole PRIMARY KEY (titreFilm, anneeSortie, idArtiste)
);

CREATE TABLE APourInstrument(
  instrument varchar(50) NOT NULL,
  idArtiste integer NOT NULL,
  idAlbum integer NOT NULL,
  idPiste integer NOT NULL REFERENCES Artiste (idArtiste) ON DELETE CASCADE,
  CONSTRAINT fkAPourInstrumentPiste FOREIGN KEY (idPiste, idAlbum) REFERENCES Piste (idPiste, idAlbum) ON DELETE CASCADE,
  CONSTRAINT pkAPourInstrument PRIMARY KEY (idArtiste, idPiste)
);

/* revoir la taille pour les nom de codec */
CREATE TABLE Flux(
  idFlux integer GENERATED ALWAYS AS IDENTITY,
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL REFERENCES Fichier (idFichier) ON DELETE CASCADE,
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL,
  CONSTRAINT pkFlux PRIMARY KEY (idFlux),
  CONSTRAINT fkCodecFlux FOREIGN KEY (nomCodec, typeCodec) REFERENCES Codec (nomCodec, typeCodec) ON DELETE CASCADE
);

/* references pour idFichier, debit et nomCodec ? */
CREATE TABLE FluxTexte(
  idFlux integer NOT NULL REFERENCES Flux (idFlux) ON DELETE CASCADE,
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL,
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL,
  langueTexte varchar(100) NOT NULL CHECK (langueTexte in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
  CONSTRAINT pkFluxTexte PRIMARY KEY (idFlux)
);


/* references pour idFichier, debit et nomCodec ? */
CREATE TABLE FluxAudio(
  idFlux integer NOT NULL REFERENCES Flux (idFlux) ON DELETE CASCADE,
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL,
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL,
  echantillonage integer NOT NULL CHECK (echantillonage in (16, 24, 32)),
  langueAudio varchar(100) NOT NULL CHECK (langueAudio in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
  CONSTRAINT pkFluxAudio PRIMARY KEY (idFlux)
);

/* references pour idFichier, debit et nomCodec ? */
CREATE TABLE FluxVideo(
  idFlux integer NOT NULL REFERENCES Flux (idFlux) ON DELETE CASCADE,
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL,
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL,
  largeurImage integer NOT NULL,
  hauteurImage integer NOT NULL,
  CONSTRAINT pkFluxVideo PRIMARY KEY (idFlux)
);

