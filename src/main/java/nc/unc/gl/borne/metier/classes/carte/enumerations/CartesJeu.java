package nc.unc.gl.borne.metier.classes.carte.enumerations;

public enum CartesJeu {

    ATTAQUE_ACCIDENT(NomCarte.ACCIDENT, 3, TypeCarte.ATTAQUE, "attaque_accident.jpeg"),
    ATTAQUE_CREVAISON(NomCarte.CREVAISON, 3,TypeCarte.ATTAQUE, "attaque_crevaison.jpeg"),
    ATTAQUE_ESSENCE(NomCarte.ESSENCE, 3,TypeCarte.ATTAQUE, "attaque_essence.jpeg"),
    ATTAQUE_FEU(NomCarte.FEU, 5,TypeCarte.ATTAQUE, "attaque_feu.jpeg"),
    ATTAQUE_VITESSE(NomCarte.VITESSE, 4,TypeCarte.ATTAQUE, "attaque_vitesse.jpeg"),
    BORNE_25(NomCarte.VINGT_CINQ,10,TypeCarte.BORNE, "borne_vingt_cinq.jpeg"),
    BORNE_50(NomCarte.CINQUANTE, 10,TypeCarte.BORNE, "borne_cinquante.jpeg"),
    BORNE_75(NomCarte.SOIXANTE_QUINZE, 10,TypeCarte.BORNE, "borne_soixante-quinze.jpeg"),
    BORNE_100(NomCarte.CENT, 12,TypeCarte.BORNE, "borne_cent.jpeg"),
    BORNE_200(NomCarte.DEUX_CENTS, 4, TypeCarte.BORNE, "borne_deux_cents.jpeg"),
    BOTTE_ACCIDENT(NomCarte.ACCIDENT, 1,TypeCarte.BOTTE, "botte_accident.jpeg"),
    BOTTE_CREVAISON(NomCarte.CREVAISON, 1,TypeCarte.BOTTE, "botte_crevaison.jpeg"),
    BOTTE_ESSENCE(NomCarte.ESSENCE, 1,TypeCarte.BOTTE, "botte_essence.jpeg"),
    BOTTE_VITESSE(NomCarte.VITESSE, 1,TypeCarte.BOTTE, "botte_vitesse.jpeg"),
    PARADE_ACCIDENT(NomCarte.ACCIDENT, 6,TypeCarte.PARADE, "parade_accident.jpeg"),
    PARADE_CREVAISON(NomCarte.CREVAISON, 6,TypeCarte.PARADE, "parade_crevaison.jpeg"),
    PARADE_ESSENCE(NomCarte.ESSENCE, 6,TypeCarte.PARADE, "parade_essence.jpeg"),
    PARADE_FEU(NomCarte.FEU, 14,TypeCarte.PARADE, "parade_feu.jpeg"),
    PARADE_VITESSE(NomCarte.VITESSE, 6,TypeCarte.PARADE, "parade_vitesse.jpeg");

    public final NomCarte nom;
    public final int nombreCartes;
    public final TypeCarte type;
    public final String image;

    CartesJeu(NomCarte nom, int nombreCartes, TypeCarte type, String image){
        this.nom = nom;
        this.nombreCartes = nombreCartes;
        this.type = type;
        this.image = image;
    }
}
