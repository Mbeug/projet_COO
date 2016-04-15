/*Creation de la table profil*/

CREATE TABLE PROFIL
	(
		ID_PROFIL INTEGER primary key NOT NULL,
		NOM varchar(15),
		SEXE char(1),
		AGE	INTEGER,
		COULEUR_CHEVEUX varchar(10),
		TAILLE_CHEVEUX varchar(10),
		COULEUR_YEUX varchar(10),
		LOCALISATION varchar(20),
		PHOTO char(10),
		ORIENTATION char(2),
		DATE_DISPO char(10),
		ID_USER INTEGER,

		foreign key (ID_USER) references UTILISATEUR(ID_USER)
	);


/*Creation de la table utilisateur*/

CREATE table UTILISATEUR
	(
		ID_User INTEGER not null,		
		Login varchar(12) not null,
		Mail varchar(20) not null,
		Password varchar(12) not null,
		ID_Profil INTEGER not null,
		ID_Historique INTEGER not null,
		ID_Preferences INTEGER not null, 

		PRIMARY KEY (ID_User),
		FOREIGN KEY (ID_Historique) REFERENCES Historique(ID_Historique),
		FOREIGN KEY (ID_Preferences) REFERENCES Preferences(ID_Preferences)
	);

/*Creation de la table historique*/

CREATE TABLE Historique 
	(
		ID_Historique INTEGER NOT NULL,
		ID_EVENT INTEGER NOT NULL REFERENCES EVENEMENT
	);

/*Creation de la table evenement*/

CREATE TABLE EVENEMENT 
(
	ID_EVENT INTEGER  AUTOINCREMENT  NOT NULL, 
	DATE date NOT NULL DEFAULT CURRENT_TIMESTAMP,
	TYPE varchar(30) NOT NULL ,
	ID_HISTORIQUE INTEGER NOT NULL,

	Primary key (ID_EVENT),
	Foreign key (ID_HISTORIQUE) references HISTORIQUE(ID_HISTORIQUE)
);

/*Creation de la table relation*/

CREATE TABLE Relation
(
	ID_Relation INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	Etat_acceptation BOOLEAN CHECK (Etat_acceptation <2 AND Etat_acceptation>=0),
	ID_Utilisateur_1 INTEGER NOT NULL REFERENCES Utilisateur,
	ID_Utilisateur_2 INTEGER NOT NULL REFERENCES Utilisateur
);

/*Creation de la table message*/

CREATE TABLE Message
	(
		ID_MESSAGE INTEGER not null,		
		TEXTE varchar(300) not null,
		DATE varchar(12),
		ID_RELATION INTEGER,

		PRIMARY KEY (ID_Message),
		FOREIGN KEY (ID_RELATION) REFERENCES RELATION(ID_RELATION)
	);

/*Creation de la table preference*/
CREATE TABLE Preferences 
	(	
		ID_Pref INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL , 
		Langue CHAR[15] NOT NULL , 
		Niveau_confidentialite  CHAR[15] NOT NULL 
	);


