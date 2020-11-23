drop table if exists Dept;
drop table if exists Emp;

create table Artiste(  
  idArtiste     integer,  
  nomArtiste      varchar(50) NOT NULL,  
  dateNaissance        date NOT NULL,
  urlPhoto        varchar(150) NOT NULL,
  Specialite        varchar(50) NOT NULL,
  biographie        varchar(8000) NOT NULL,
  
  constraint pkArtiste primary key (idArtiste)  
);

create table APourRole(  
  roleFilm     varchar(50) NOT NULL,  
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
/**/
);

create table CategorieMusique(
  typeCategorieMusique varchar(50),  
  constraint pkTypeCategorieMusique primary key (typeCategorieMusique), 
);

