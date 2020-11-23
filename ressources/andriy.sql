CREATE TABLE Utilisateur(
		email	VARCHAR(255),
    nom		VARCHAR(50) NOT NULL,
    prenom	VARCHAR(30) NOT NULL,
    age		INTEGER NOT NULL CHECK(age > 0),
    langueDiffusion VARCHAR(30) NOT NULL,
    code	CHAR(4) NOT NULL,
    CONSTRAINT pk_util PRIMARY KEY (email),
    CONSTRAINT chek_code CHECK (code like '[0-9][0-9][0-9][0-9]') -- check que le code est bien constitué que de 4 chiffres
);

CREATE TABLE Fichier(
		idFichier INTEGER,
		taille		INTEGER NOT NULL,
		dateDepot DATE NOT NULL,
		email			INTEGER,
		CONSTRAINT pk_fich PRIMARY KEY (idFichier),
		CONSTRAINT fk_util FOREIGN KEY (email) REFERENCES Utilisateur(email)
);

CREATE TABLE Film(
		titreFilm VARCHAR(1000),
		anneeSortie INTEGER, -- Il faut peut-être rajouter une contrainte de valeur
		resume MEDIUMTEXT NOT NULL, -- Faut vérifier si ce type existe pour Oracle sinon VARCHAR(65000)
		ageMin INTEGER CHECK (ageMin > 0), -- Peut être null selon moi
		urlAffiche TEXT NOT NULL, -- Si le type TEXT fonctionne pas pour Oracle utiliser VARCHAR(2083)
		CONSTRAINT pk_film PRIMARY KEY (titreFilm, anneeSortie)
);

CREATE TABLE EstUnFilm(
		idFichier INTEGER REFERENCES Fichier(idFichier),
		titreFilm VARCHAR(1000) REFERENCES Film(titreFilm),
		anneeSortie INTEGER REFERENCES Film(anneeSortie),
		CONSTRAINT pk_euf PRIMARY KEY (idFichier)
);

CREATE TABLE EstUnePiste(
		idFichier INTEGER REFERENCES Fichier(idFichier),
		idPiste INTEGER REFERENCES Piste(idPiste),
		CONSTRAINT pk_eup PRIMARY KEY (idFichier)
);

CREATE TABLE ImgExtraiteFilm(
		urlImage TEXT,
		titreFilm VARCHAR(1000) REFERENCES Film(titreFilm),
		anneeSortie INTEGER REFERENCES Film(anneeSortie),
		CONSTRAINT pk_imgfilm PRIMARY KEY (urlImage)
);

CREATE TABLE CategorieFilm(
		typeCategorieFilm VARCHAR(255),
		CONSTRAINT pk_catgfilm PRIMARY KEY (typeCategorieFilm)
);

CREATE TABLE APourCategorie(
		titreFilm VARCHAR(1000) REFERENCES Film(titreFilm),
		anneeSortie INTEGER REFERENCES Film(anneeSortie),
		typeCategorieFilm VARCHAR(255) REFERENCES CategorieFilm(typeCategorieFilm),
		CONSTRAINT pk_apcatg PRIMARY KEY (titreFilm, anneeSortie, typeCategorieFilm)
);
