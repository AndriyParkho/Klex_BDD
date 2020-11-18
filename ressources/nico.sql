drop table if exists PisteAPourCategorie;
drop table if exists Album;
drop table if exists AlbumAPourCategorie;
drop table if exists Codec;
drop table if exists Client;
drop table if exists SupporteCodec;

/* 
mettre la taille des identifiants de table 
ajouter les contraintes not null
*/
create table PisteAPourCategorie(
    idPiste     number,
    idAlbum     number,
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
    idAlbum     number,
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
    idAlbum     number,
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
    largeurMax  number,
    hauteurMax  number,
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