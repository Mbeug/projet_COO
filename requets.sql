/*Requetes n°1*/

SELECT COUNT(Etat_acceptation) AS ReqEntrNonRep
FROM Relation R, Utilisateur U
WHERE U.ID_Relation = R.ID_Relation -- car plus de FK dans utilisateur
AND R.Etat_acceptation = 1;

/*Requetes n°2*/

SELECT login 
FROM Utilisateur U, Profil P, Relation R
WHERE U.ID_User = R.ID_Utilisateur_1
AND R.Etat_acceptation = 2
AND R.ID_Utilisateur_2 = P.ID_User
AND P.sexe = 'F';

/*Requetes n°3*/

select P1.NOM as 'Utilisateur_donné',P1.LOCALISATION, P2.NOM as 'Correspondance'
from PROFIL P1, PROFIL P2
where P1.NOM ='Etienne'
and P1.LOCALISATION=P2.LOCALISATION
and P1.SEXE != P2.SEXE;

/*Requetes n°4*/

SELECT 
	(	
		SELECT age 
		FROM Utilisateur U, Profil P
		WHERE U.ID_Profil = P.ID_Profil AND U.login = 'login'
	) - AVG(age) AS DiffAgeUserEtAgeMoyenAmis
FROM Utilisateur U, Profil P, Relation R
WHERE U.ID_Relation = R.ID_Relation --ID_relation dans user n'existe plus
AND R.Etat_acceptation = 1
AND R.ID_Utilisateur = U.ID_Utilisateur 
AND U.ID_Profil = P.ID_Profil;

/*Requetes n°5*/

SELECT Etat_acceptation 
FROM Relation R, Utilisateur U
WHERE U.ID_User = R.ID_Utilisateur_1
AND R.Etat_acceptation = 1;

/*Requetes n°6*/
select P1.ID_PROFIL,P2.ID_PROFIL, 
cast(	case
		when P1.NOM=P2.NOM then 1 else 0 end +
		case
		when P1.SEXE=P2.SEXE then 1 else 0 end +
		case
		when P1.AGE=P2.AGE then 1 else 0 end +
		case
		when P1.COULEUR_CHEVEUX=P2.COULEUR_CHEVEUX then 1 else 0 end +
		case
		when P1.TAILLE_CHEVEUX=P2.TAILLE_CHEVEUX then 1 else 0 end +
		case
		when P1.COULEUR_YEUX=P2.COULEUR_YEUX then 1 else 0 end +
		case
		when P1.LOCALISATION=P2.LOCALISATION then 1 else 0 end +
		case
		when P1.PHOTO=P2.PHOTO then 1 else 0 end +
		case
		when P1.ORIENTATION=P2.ORIENTATION then 1 else 0 end +
		case
		when P1.DATE_DISPO=P2.DATE_DISPO then 1 else 0 end as DOUBLE
	 )/10
from UTILISATEUR U1, UTILISATEUR U2, PROFIL P1, PROFIL P2
where U1.ID_PROFIL = P1.ID_PROFIL 
and U2.ID_PROFIL = P2.ID_PROFIL;