DROP TABLE IF EXISTS Flux;
DROP TABLE IF EXISTS FluxTexte;
DROP TABLE IF EXISTS FluxAudio;
DROP TABLE IF EXISTS FluxVideo;
DROP TABLE IF EXISTS Dept;
DROP TABLE IF EXISTS Emp;
DROP TABLE IF EXISTS PisteAPourCategorie;
DROP TABLE IF EXISTS Album;
DROP TABLE IF EXISTS AlbumAPourCategorie;
DROP TABLE IF EXISTS Codec;
DROP TABLE IF EXISTS Client;
DROP TABLE IF EXISTS SupporteCodec;
DROP TABLE IF EXISTS CategorieMusique;
DROP TABLE IF EXISTS Artiste;
DROP TABLE IF EXISTS APourRole;
DROP TABLE IF EXISTS Piste;
DROP TABLE IF EXISTS APourInstrument;
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Fichier;
DROP TABLE IF EXISTS Film;
DROP TABLE IF EXISTS EstUnFilm;
DROP TABLE IF EXISTS EstUnePiste;
DROP TABLE IF EXISTS ImgExtraiteFilm;
DROP TABLE IF EXISTS CategorieFilm;
DROP TABLE IF EXISTS APourCategorie;

/*
After foreign key:
ON DELETE CASCADE: if a row in the parent is deleted, then all the rows in the child table that reference the removed row will be deleted.
ON DELETE SET NULL: if a row in the parent is deleted, then all the rows in the child table reference the removed row will be set to NULL for the foreign key columns.
*/ 

/*
mettre la taille des identifiants de table
*/
CREATE TABLE PisteAPourCategorie(
  idPiste integer NOT NULL,
  idAlbum integer NOT NULL,
  typeCategorieMusique varchar(20) NOT NULL REFERENCES CategorieMusique (typeCategorieMusique),
  CONSTRAINT pkPisteAPourCategorie PRIMARY KEY (idPiste, idAlbum, typeCategorieMusique),
  CONSTRAINT fkIdPisteIdAlbum FOREIGN KEY (idPiste, idAlbum) REFERENCES Piste (idPiste, idAlbum)
);

/*
mettre la taille des identifiants de table
*/
CREATE TABLE Album(
  idAlbum integer GENERATED ALWAYS AS IDENTITY,
  titreAlbum varchar(50) NOT NULL,
  nomAlbum varchar(50) NOT NULL,
  dateSortieAlbum date NOT NULL,
  urlImagePochette varchar(100) NOT NULL,
  CONSTRAINT pkAlbum PRIMARY KEY (idAlbum)
);

/*
mettre la taille des identifiants de table
*/
CREATE TABLE AlbumAPourCategorie(
  idAlbum integer NOT NULL REFERENCES Album (idAlbum),
  typeCategorieMusique varchar(20) NOT NULL REFERENCES CategorieMusique (typeCategorieMusique),
  CONSTRAINT pkPisteAPourCategorie PRIMARY KEY (idAlbum, typeCategorieMusique)
);

/*
revoir le check à la fin des déclarations en contrainte
*/
CREATE TABLE Codec(
  nomCodec varchar(20) NOT NULL,
  typeCodec varchar(20) NOT NULL check(typeCodec in ('audio', 'video', 'texte')),
  CONSTRAINT pkCodec PRIMARY KEY (nomCodec)
);

/*
mettre la taille des number
*/
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
  nomCodec varchar(20) NOT NULL REFERENCES Codec (nomCodec),
  CONSTRAINT pkSupporteCodec PRIMARY KEY (marque, modele, nomCodec),
  CONSTRAINT fkClient FOREIGN KEY (marque, modele) REFERENCES Client (marque, modele)
);


CREATE TABLE Artiste(
  idArtiste integer GENERATED ALWAYS AS IDENTITY,
  nomArtiste varchar(50) NOT NULL,
  dateNaissance date, urlPhoto varchar(150) NOT NULL,
  Specialite varchar(50) NOT NULL,
  biographie varchar(8000),
  CONSTRAINT pkArtiste PRIMARY KEY (idArtiste)
);

/*
verifier qu'un film est associé à au moins un artiste
vérifier qu'on le format year de anneeSortie
*/
CREATE TABLE APourRole(
  roleFilm varchar(50) NOT NULL,
  titreFilm varchar(50) NOT NULL,
  anneeSortie integer NOT NULL,
  idArtiste integer NOT NULL REFERENCES Artiste (idArtiste),
  CONSTRAINT fkFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie),
  CONSTRAINT pkAPourRole PRIMARY KEY (titreFilm, anneeSortie, idArtiste)
);


CREATE TABLE Piste(
  idPiste integer GENERATED ALWAYS AS IDENTITY,
  numPiste integer NOT NULL,
  titrePiste varchar(50) NOT NULL,
  dureePiste time(0) NOT NULL,
  idAlbum integer NOT NULL REFERENCES Album (idAlbum),
  idFichier integer NOT NULL REFERENCES Fichier (idFichier),
  CONSTRAINT pkPiste PRIMARY KEY (idPiste)
);


CREATE TABLE APourInstrument(
  instrument varchar(50) NOT NULL,
  idArtiste integer NOT NULL REFERENCES Piste (idPiste),
  idPiste integer NOT NULL REFERENCES Artiste (idArtiste),
  CONSTRAINT pkAPourInstrument PRIMARY KEY (idArtiste, idPiste)
);


CREATE TABLE CategorieMusique(
  typeCategorieMusique varchar(50) NOT NULL,
  CONSTRAINT pkCategorieMusique PRIMARY KEY (typeCategorieMusique)
);

/* revoir la taille pour les nom de codec */
CREATE TABLE Flux(
  idFlux integer GENERATED ALWAYS AS IDENTITY,
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL REFERENCES Fichier (idFichier),
  nomCodec varchar(100) NOT NULL REFERENCES Codec (nomCodec),
  CONSTRAINT pkFlux PRIMARY KEY (idFlux)
);

/* references pour idFichier, debit et nomCodec ? */
CREATE TABLE FluxTexte(
  idFlux integer NOT NULL REFERENCES Flux (idFlux),
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL,
  nomCodec varchar(100) NOT NULL,
  langueTexte varchar(100) NOT NULL CHECK (langueTexte in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
  CONSTRAINT pkFluxTexte PRIMARY KEY (idFlux)
);

/* references pour idFichier, debit et nomCodec ? */
CREATE TABLE FluxAudio(
  idFlux integer NOT NULL REFERENCES Flux (idFlux),
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL,
  nomCodec varchar(100) NOT NULL,
  echantillonage integer NOT NULL CHECK (echantillonage in (16, 24, 32)),
  langueAudio varchar(100) NOT NULL CHECK (langueAudio in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
  CONSTRAINT pkFluxAudio PRIMARY KEY (idFlux)
);

/* references pour idFichier, debit et nomCodec ? */
CREATE TABLE FluxVideo(
  idFlux integer NOT NULL REFERENCES Flux (idFlux),
  debit float NOT NULL CHECK (debit > 0),
  idFichier integer NOT NULL,
  nomCodec varchar(100) NOT NULL,
  largeurImage integer NOT NULL,
  hauteurImage integer NOT NULL,
  CONSTRAINT pkFluxVideo PRIMARY KEY (idFlux)
);


CREATE TABLE Utilisateur(
  email varchar(255) NOT NULL,
  nom varchar(50) NOT NULL,
  prenom varchar(30) NOT NULL,
  age integer NOT NULL check(age > 0),
  langueDiffusion varchar(100) NOT NULL CHECK (langueDiffusion in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
  code char(4) NOT NULL CHECK (code like '[0-9][0-9][0-9][0-9]'), -- check que le code est bien constitué que de 4 chiffres
  CONSTRAINT pkUtilisateur PRIMARY KEY (email)
);


CREATE TABLE Fichier(
  idFichier integer GENERATED ALWAYS AS IDENTITY,
  taille integer NOT NULL,
  dateDepot DATE NOT NULL,
  email integer NOT NULL REFERENCES Utilisateur (email),
  CONSTRAINT pkFichier PRIMARY KEY (idFichier)
);

/*
Il faut peut-être rajouter une contrainte de valeur pour anneeSortie
Il faut vérifier que resume existe pour Oracle sinon VARCHAR(65000)
ageMin peut être null ?
Si le type TEXT fonctionne pas pour Oracle utiliser VARCHAR(2083)
*/
CREATE TABLE Film(
  titreFilm varchar(1000) NOT NULL,
  anneeSortie integer NOT NULL,
  resume MEDIUMTEXT NOT NULL,
  ageMin integer NOT NULL CHECK (ageMin > 0),
  urlAffiche text NOT NULL,
  CONSTRAINT pkFilm PRIMARY KEY (titreFilm, anneeSortie)
);


CREATE TABLE EstUnFilm(
  idFichier integer NOT NULL REFERENCES Fichier (idFichier),
  titreFilm varchar(1000) NOT NULL,
  anneeSortie integer NOT NULL,
  CONSTRAINT fkFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie),
  CONSTRAINT pkEstUnFilm PRIMARY KEY (idFichier)
);


CREATE TABLE EstUnePiste(
  idFichier integer NOT NULL REFERENCES Fichier (idFichier),
  idPiste integer NOT NULL REFERENCES Piste (idPiste),
  CONSTRAINT pkEstUnePiste PRIMARY KEY (idFichier)
);


CREATE TABLE ImgExtraiteFilm(
  urlImage text NOT NULL,
  titreFilm varchar(1000) NOT NULL,
  anneeSortie integer NOT NULL,
  CONSTRAINT fkFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie),
  CONSTRAINT pkImgExtraiteFilm PRIMARY KEY (urlImage)
);


CREATE TABLE CategorieFilm(
  typeCategorieFilm varchar(255) NOT NULL,
  CONSTRAINT pkCategorieFilm PRIMARY KEY (typeCategorieFilm)
);


CREATE TABLE APourCategorie(
  titreFilm varchar(1000) NOT NULL,
  anneeSortie integer NOT NULL,
  typeCategorieFilm varchar(255) NOT NULL REFERENCES CategorieFilm (typeCategorieFilm),
  CONSTRAINT fkFilm FOREIGN KEY (titreFilm, anneeSortie) REFERENCES Film (titreFilm, anneeSortie),
  CONSTRAINT pkAPourCategorie PRIMARY KEY (titreFilm, anneeSortie, typeCategorieFilm)
);