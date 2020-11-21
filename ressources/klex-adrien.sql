drop Flux;
drop FluxTexte;
drop FluxAudio;
drop FluxVideo;

LANGUES := ('Français', 'Anglais', 'Italien', 'Espagnol', 'Allemand');

create table Flux (
	idFlux int not null,
	debit float CHECK (debit > 0),
	idFichier int not null,
	nomCodec varchar(100), -- revoir la taille pour les nom de codec
	constraint pk_idFlux primary key (idFlux),
)

create table FluxTexte (
	idFlux int,
	debit float check (debit > 0),
	idFichier int,
	nomCodec varchar(100),
	langueTexte varchar(100) check (langueTexte in LANGUES),
	constraint pk_idFlux primary key (idFlux),
	constraint fk_idFlux foreign key (idFlux) references Flux(idFlux) --voir pour auto_increment
)

create table FluxAudio (
	idFlux int,
	debit float check (debit > 0),
	idFichier int,
	nomCodec varchar(100),
	echantillonage int check (echantillonage in (16, 24, 32)),
	langueAudio varchar(100) check (langueTexte in LANGUES),
	constraint pk_idFlux primary key (idFlux),
	constraint fk_idFlux foreign key (idFlux) references Flux(idFlux)
)

create table FluxVideo (
	idFlux int,
	debit float check (debit > 0),
	idFichier int,
	nomCodec varchar(100),
	largeurImage int,
	hauteurImage int,
	constraint pk_idFlux primary key (idFlux),
	constraint fk_idFlux foreign key (idFlux) references Flux(idFlux)
)


