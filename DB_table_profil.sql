/*Creation de la table profil*/

CREATE TABLE PROFIL
	(
		ID_PROFIL decimal(9,2) primary key NOT NULL,
		NOM varchar(15),
		SEXE char(1),
		AGE	decimal(9,2),
		COULEUR_CHEVEUX varchar(10),
		TAILLE_CHEVEUX varchar(10),
		COULEUR_YEUX varchar(10),
		LOCALISATION varchar(20),
		PHOTO char(10),
		ORIENTATION char(2),
		DATE_DISPO char(10),
		ID_USER decimal(9,2),
		foreign key (ID_USER) references UTILISATEUR(ID_USER)
	);

/*Insertion de la population de la table profil*/

	INSERT into PROFIL values ( 1,'Gano', 'M', 35, 'brun', 'court', 'brun', 'Namur', 'NULL', 'F', '12-03-2016', 1);
	INSERT into PROFIL values ( 2,'Yves', 'M', 21, 'blond', 'mi-long', 'bleu','Wavre', 'NULL', 'F', '21-03-2016', 2);
	INSERT into PROFIL values ( 3,'Etienne', 'M', 46, 'chatain', 'long', 'vert', 'Bruxelles','NULL', 'F', '19-03-2016', 3);
	INSERT into PROFIL values ( 4,'Benoît', 'M', 20, 'brun', 'court', 'brun', 'Bruxelles', 'NULL', 'F', '01-04-2016', 4);
	INSERT into PROFIL values ( 5,'Jaques', 'M', 49, 'gris', 'mi-long', 'bleu','Bruxelles', 'NULL', 'F', 'JJ-MM-2016', 5);
	INSERT into PROFIL values ( 6,'Georges', 'M', 36, 'chatain', 'court', 'brun','Wavre','NULL', 'F', 'JJ-MM-2016', 6);
	INSERT into PROFIL values ( 7,'Alexandre', 'M', 29, 'brun', 'court', 'bleu','Bruxelles', 'NULL', 'F', 'JJ-MM-2016', 7);
	INSERT into PROFIL values ( 8,'Benjamin', 'M', 44, 'chatain', 'mi-long', 'brun', 'Wavre', 'NULL', 'F', 'JJ-MM-2016', 8);
	INSERT into PROFIL values ( 9,'Maurice', 'M', 39, 'brun', 'long', 'gris','Wavre', 'NULL', 'H', 'JJ-MM-2016', 9);
	INSERT into PROFIL values ( 10,'Joseph', 'M', 23, 'chatain', 'court', 'bleu', 'Bruxelles', 'NULL', 'H', 'JJ-MM-2016', 10);
	INSERT into PROFIL values ( 11,'Armand', 'M', 23, 'blond', 'court', 'brun','Wavre', 'NULL', 'Bi', 'JJ-MM-2016', 11);
	INSERT into PROFIL values ( 12,'Carole', 'F', 21, 'brun', 'long', 'brun', 'Namur', 'NULL', 'H', 'JJ-MM-2016', 12);
	INSERT into PROFIL values ( 13,'Melissandre', 'F', 21, 'blond', 'long', 'bleu','Wavre','NULL', 'H', 'JJ-MM-2016', 13);
	INSERT into PROFIL values ( 14,'Violette', 'F', 36, 'chatain', 'court', 'vert','Namur', 'NULL', 'H', 'JJ-MM-2016', 14);
	INSERT into PROFIL values ( 15,'Juliette', 'F', 36, 'brun', 'mi-long', 'bleu', 'Wavre', 'NULL', 'F', 'JJ-MM-2016', 15);
	INSERT into PROFIL values ( 16,'Angelique', 'F', 45, 'chatain', 'mi-long', 'brun','Namur', 'NULL', 'Bi', 'JJ-MM-2016', 16);
	INSERT into PROFIL values ( 17,'Claudette', 'F', 50, 'brun', 'long', 'vert', 'Bruxelles', 'NULL', 'H', 'JJ-MM-2016', 17);
	INSERT into PROFIL values ( 18,'Fleur', 'F', 21, 'brun', 'long', 'bleu','Namur', 'NULL', 'F', 'JJ-MM-2016', 18);
	INSERT into PROFIL values ( 19,'Pascaline', 'F', 36, 'noir', 'long', 'brun','Bruxelles', 'NULL', 'H', 'JJ-MM-2016', 19);





	
	


















