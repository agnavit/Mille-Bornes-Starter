package nc.unc.gl.borne.carte;

/**
 * Enumération des différents types de carte dans le jeu
 */
public enum TypeCarte {
    /**
     * Carte qui attaque un ou des adversaires
     */
    ATTAQUE,

    /**
     * Carte qui permet d'avancer
     */
    BORNE,

    /**
     * Carte d'immunité contre les attaques qui lui sont associées
     */
    BOTTE,

    /**
     * Carte qui répare l'effet d'une carte attaque
     */
    PARADE;
}
