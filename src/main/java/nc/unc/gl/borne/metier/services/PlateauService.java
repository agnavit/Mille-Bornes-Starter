package nc.unc.gl.borne.metier.services;

import nc.unc.gl.borne.metier.classes.Joueur;
import nc.unc.gl.borne.metier.classes.Plateau;
import nc.unc.gl.borne.metier.classes.carte.Carte;
import nc.unc.gl.borne.metier.classes.carte.PileCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.NomCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypePile;

public class PlateauService {

    public Plateau ajouterCartePlateau(TypePile cle, Carte carte, Joueur joueur){
        joueur.getPlateau().getPile(cle).empiler(carte);
        return joueur.getPlateau();
    }

    public Carte enleverCartePlateau(TypePile cle, Joueur joueur){
        return joueur.getPlateau().getPile(cle).depiler();
    }

    public int getTaillePile(TypePile cle, Joueur joueur){
        return joueur.getPlateau().getPile(cle).getPileCarte().size();
    }

    public void enleverCartesAttaqueEtParadePile(TypePile cle, PileCarte defausse, Joueur joueur){
        // On enleve deux fois : pour enlever la carte attaque et la carte parade
        if(cle != TypePile.BATAILLE && cle !=TypePile.VITESSE){
            throw new IllegalArgumentException("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse");
        }
        for(int i=0; i<2; i++){
            defausse.empiler(joueur.getPlateau().getPile(cle).depiler());
        }
    }

    public boolean contains(Carte carteAChercher, Joueur joueur){
        TypePile typePile;
        if ((carteAChercher.getType() == TypeCarte.ATTAQUE || carteAChercher.getType() == TypeCarte.PARADE)
            && carteAChercher.getNom() == NomCarte.VITESSE) {
                typePile = TypePile.VITESSE;
        } else if(carteAChercher.getType() == TypeCarte.BORNE) {
            typePile = TypePile.BORNES;
        } else if(carteAChercher.getType() == TypeCarte.BOTTE) {
            typePile = TypePile.BOTTES;
        }else {
            typePile = TypePile.BATAILLE;
        }
        for (Carte carte : joueur.getPlateau().getPile(typePile).getPileCarte()) {
            if (carte.equals(carteAChercher)) {
                return true;
            }
        }
        return false;
    }
}
