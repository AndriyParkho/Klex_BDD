drop table if exists Flux;
drop table if exists FluxTexte;
drop table if exists FluxAudio;
drop table if exists FluxVideo;
drop table if exists Dept;
drop table if exists Emp;
drop table if exists PisteAPourCategorie;
drop table if exists Album;
drop table if exists AlbumAPourCategorie;
drop table if exists Codec;
drop table if exists Client;
drop table if exists SupporteCodec;
drop table if exists CategorieMusique;
drop table if exists Artiste;
drop table if exists APourRole;
drop table if exists Piste;
drop table if exists APourInstrument;
drop table if exists Utilisateur;
drop table if exists Fichier;
drop table if exists Film;
drop table if exists EstUnFilm;
drop table if exists EstUnePiste;
drop table if exists ImgExtraiteFilm;
drop table if exists CategorieFilm;
drop table if exists APourCategorie;

/*
mettre la taille des identifiants de table
ajouter les contraintes not null
*/
create table PisteAPourCategorie(
    idPiste     int,
    idAlbum     int,
    typeCategorieMusique    varchar(20),
    constraint pkPisteAPourCategorie primary key (idPiste, idAlbum, typeCategorieMusique),
    constraint fkIdPisteIdAlbum foreign key (idPiste, idAlbum) references Piste(idPiste, idAlbum),
    constraint fkTypeCategorieMusique foreign key (typeCategorieMusique) references CategorieMusique(typeCategorieMusique)
);

/*
mettre la taille des identifiants de table
ajouter les contraintes not null
*/
create table Album(
    idAlbum     int,
    titreAlbum  varchar(50),
    nomAlbum    varchar(50),
    dateSortieAlbum     date,
    urlImagePochette    varchar(100),
    constraint pkAlbum primary key (idAlbum)
);

/*
mettre la taille des identifiants de table
ajouter les contraintes not null
*/
create table AlbumAPourCategorie(
    idAlbum     int,
    typeCategorieMusique    varchar(20),
    constraint pkPisteAPourCategorie primary key (idAlbum, typeCategorieMusique),
    constraint fkIdAlbum foreign key (idAlbum) references Album(idAlbum),
    constraint fkTypeCategorieMusique foreign key (typeCategorieMusique) references CategorieMusique(typeCategorieMusique)
);

/*
revoir le check à la fin des déclarations en contrainte
ajouter les contraintes not null
*/
create table Codec(
    nomCodec    varchar(20),
    typeCodec   varchar(20) check(typeCodec in ('audio', 'video', 'texte')),
    constraint pkCodec primary key (nomCodec)
);

/*
mettre la taille des number
ajouter les contraintes not null
*/
create table Client(
    marque  varchar(20),
    modele  varchar(20),
    largeurMax  int,
    hauteurMax  int,
    constraint pkClient primary key (marque, modele)
);

/*
ajouter les contraintes not null
*/
create table SupporteCodec(
    marque  varchar(20),
    modele  varchar(20),
    nomCodec    varchar(20),
    constraint pkSupporteCodec primary key (marque, modele, nomCodec),
    constraint fkClient foreign key (marque, modele) references Client(marque, modele),
    constraint fkCodec foreign key (nomCodec) references Codec(nomCodec)
);


create table Artiste(
  idArtiste     int,
  nomArtiste      varchar(50) not null,
  dateNaissance        date not null,
  urlPhoto        varchar(150) not null,
  Specialite        varchar(50) not null,
  biographie        varchar(8000) not null,

  constraint pkArtiste primary key (idArtiste)
);

create table APourRole(
  roleFilm     varchar(50) not null,
  titreFilm      varchar(50),
  anneeSortie        integer,/*normalement on devrait pouvoir mettre year*/
  idArtiste        integer,

  constraint fkTitreFilm foreign key (titreFilm) references Film (titreFilm)
  constraint fkAnneeSortie foreign key (anneeSortie) references Film (anneeSortie)
  constraint fkIdArtiste foreign key (idArtiste) references Artiste (idArtiste)
/* verifier qu'un film est associé à au moins un artiste*/
);


create table Piste(
  idPiste    integer,
  numPiste    integer,
  titrePiste      varchar(50),
  dureePiste      time(0),
  idAlbum    integer,
  idFichier      integer references Fichier (idFichier),
  constraint pkIdPiste primary key (idPiste),
  constraint fkidAlbum foreign key (idAlbum) references Album (idAlbum)
);

create table APourInstrument(
  instrument     varchar(50),
  idArtiste      integer,
  idPiste        integer,

  constraint fkIdPiste foreign key (idPiste) references Piste (idPiste)
  constraint fkIdArtiste foreign key (idArtiste) references Artiste (idArtiste)
);

create table CategorieMusique(
  typeCategorieMusique varchar(50),
  constraint pkTypeCategorieMusique primary key (typeCategorieMusique)
);

create table Flux(
	idFlux int,
	debit float CHECK (debit > 0),
	idFichier int,
	nomCodec varchar(100), -- revoir la taille pour les nom de codec
	constraint pkIdFlux primary key (idFlux)
);

create table FluxTexte(
	idFlux int,
	debit float check (debit > 0),
	idFichier int,
	nomCodec varchar(100),
	langueTexte varchar(100) check (langueTexte in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
	constraint pkIdFlux primary key (idFlux),
	constraint fkIdFlux foreign key (idFlux) references Flux(idFlux) --voir pour auto_increment
);

create table FluxAudio(
	idFlux int,
	debit float check (debit > 0),
	idFichier int,
	nomCodec varchar(100),
	echantillonage int check (echantillonage in (16, 24, 32)),
	langueAudio varchar(100) check (langueAudio in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
	constraint pkIdFlux primary key (idFlux),
	constraint fkIdFlux foreign key (idFlux) references Flux(idFlux)
);

create table FluxVideo(
	idFlux int,
	debit float check (debit > 0),
	idFichier int,
	nomCodec varchar(100),
	largeurImage int,
	hauteurImage int,
	constraint pkIdFlux primary key (idFlux),
	constraint fkIdFlux foreign key (idFlux) references Flux(idFlux)
);

CREATE TABLE Utilisateur(
	email	varchar(255),
    nom		varchar(50) not null,
    prenom	varchar(30) not null,
    age		int not null check(age > 0),
    langueDiffusion varchar(100) check (langueDiffusion in ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand')),
    code	char(4) not null,
    constraint pkUtil primary key (email),
    constraint chekCode check (code like '[0-9][0-9][0-9][0-9]') -- check que le code est bien constitué que de 4 chiffres
);

CREATE TABLE Fichier(
	idFichier int,
	taille		int not null,
	dateDepot DATE not null,
	email			int,
	constraint pkFich primary key (idFichier),
	constraint fkUtil foreign key (email) references Utilisateur(email)
);

CREATE TABLE Film(
	titreFilm varchar(1000),
	anneeSortie int, -- Il faut peut-être rajouter une contrainte de valeur
	resume MEDIUMTEXT not null, -- Faut vérifier si ce type existe pour Oracle sinon VARCHAR(65000)
	ageMin int check (ageMin > 0), -- Peut être null selon moi
	urlAffiche text not null, -- Si le type TEXT fonctionne pas pour Oracle utiliser VARCHAR(2083)
	constraint pkFilm primary key (titreFilm, anneeSortie)
);

CREATE TABLE EstUnFilm(
	idFichier int references Fichier(idFichier),
	titreFilm varchar(1000) references Film(titreFilm),
	anneeSortie int references Film(anneeSortie),
	constraint pkEuf primary key (idFichier)
);

CREATE TABLE EstUnePiste(
	idFichier int references Fichier(idFichier),
	idPiste int references Piste(idPiste),
	constraint pkEup primary key (idFichier)
);

CREATE TABLE ImgExtraiteFilm(
	urlImage text,
	titreFilm varchar(1000) references Film(titreFilm),
	anneeSortie int references Film(anneeSortie),
	constraint pkImgfilm primary key (urlImage)
);

CREATE TABLE CategorieFilm(
	typeCategorieFilm varchar(255),
	constraint pkCatgfilm primary key (typeCategorieFilm)
);

CREATE TABLE APourCategorie(
	titreFilm varchar(1000) references Film(titreFilm),
	anneeSortie int references Film(anneeSortie),
	typeCategorieFilm varchar(255) references CategorieFilm(typeCategorieFilm),
	constraint pkApcatg primary key (titreFilm, anneeSortie, typeCategorieFilm)
);
