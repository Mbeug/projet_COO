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
		when P1.DATE_DISPO=P2.DATE_DISPO then 1 else 0 end as FLOAT
	 )/10
from UTILISATEUR U1, UTILISATEUR U2, PROFIL P1, PROFIL P2
where U1.ID_PROFIL = P1.ID_PROFIL 
and U2.ID_PROFIL = P2.ID_PROFIL;