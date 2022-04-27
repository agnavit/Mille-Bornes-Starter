package nc.unc.gl.borne.carte;

public enum EnumCard {

    ATTAQUE_ACCIDENT(NomCarte.ACCIDENT, 3,TypeCarte.ATTAQUE, "attaque_accident.jpeg"),
    ATTAQUE_CREVAISON(NomCarte.CREVAISON, 3,TypeCarte.ATTAQUE, "attaque_crevaison.jpeg"),
    ATTAQUE_ESSENCE(NomCarte.ESSENCE, 3,TypeCarte.ATTAQUE, "attaque_essence.jpeg"),
    ATTAQUE_FEU(NomCarte.FEU, 5,TypeCarte.ATTAQUE, "attaque_feu.jpeg"),
    ATTAQUE_VITESSE(NomCarte.VITESSE, 4,TypeCarte.ATTAQUE, "attaque_vitesse.jpeg"),
    BORNE_25(NomCarte.VINGT_CINQ,10,TypeCarte.BORNE, "borne_25.jpeg"),
    BORNE_50(NomCarte.CINQUANTE, 10,TypeCarte.BORNE, "borne_50.jpeg"),
    BORNE_75(NomCarte.SOIXANTE_QUINZE, 10,TypeCarte.BORNE, "borne_75.jpeg"),
    BORNE_100(NomCarte.CENT, 12,TypeCarte.BORNE, "borne_100.jpeg"),
    BORNE_200(NomCarte.DEUX_CENTS, 4, TypeCarte.BORNE, "borne_200.jpeg"),
    BOTTE_ACCIDENT(NomCarte.AS_DU_VOLANT, 1,TypeCarte.BOTTE, "botte_accident.jpeg"),
    BOTTE_CREVAISON(NomCarte.INCREVABLE, 1,TypeCarte.BOTTE, "botte_crevaison.jpeg"),
    BOTTE_ESSENCE(NomCarte.CAMION_CITERNE, 1,TypeCarte.BOTTE, "botte_essence.jpeg"),
    BOTTE_VITESSE(NomCarte.VEHICULE_PRIORITAIRE, 1,TypeCarte.BOTTE, "botte_vitesse.jpeg"),
    PARADE_ACCIDENT(NomCarte.ACCIDENT, 6,TypeCarte.PARADE, "parade_accident.jpeg"),
    PARADE_CREVAISON(NomCarte.CREVAISON, 6,TypeCarte.PARADE, "parade_crevaison.jpeg"),
    PARADE_ESSENCE(NomCarte.ESSENCE, 6,TypeCarte.PARADE, "parade_essence.jpeg"),
    PARADE_FEU(NomCarte.FEU, 14,TypeCarte.PARADE, "parade_feu.jpeg"),
    PARADE_VITESSE(NomCarte.VITESSE, 6,TypeCarte.PARADE, "parade_vitesse.jpeg");

    public NomCarte nameCard;
    public int numberCard;
    public TypeCarte typeCarte;
    public String imageCard;

    EnumCard(NomCarte nameCard, int numberCard, TypeCarte typeCarte, String imageCard){
        this.nameCard = nameCard;
        this.numberCard = numberCard;
        this.typeCarte = typeCarte;
        this.imageCard = imageCard;
    }
}
