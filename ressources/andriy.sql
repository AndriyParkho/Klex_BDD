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

CREATE TABLE EstUnFilm(
		idFichier INTEGER REFERENCES Fichier(idFichier),
		titreFilm VARCHAR(255),
		anneeFilm INTEGER,
		
);
