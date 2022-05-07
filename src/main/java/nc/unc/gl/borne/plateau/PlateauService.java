package nc.unc.gl.borne.plateau;

import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.carte.enumerations.TypePile;
import nc.unc.gl.borne.joueur.Joueur;

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

    public boolean contains(Carte carteAChercher, Joueur joueur, TypePile typePile){
        return joueur.getPlateau().getPile(typePile).contientCarte(carteAChercher.getNom(), carteAChercher.getType());
    }
}
