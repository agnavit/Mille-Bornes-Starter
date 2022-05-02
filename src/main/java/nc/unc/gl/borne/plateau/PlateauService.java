package nc.unc.gl.borne.plateau;

import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.concurrent.atomic.AtomicInteger;

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

    public boolean hasFeuVert(Joueur joueur) {
        AtomicInteger i = new AtomicInteger();
        i.set(0);
        joueur.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().forEach( carte -> {
            if (carte.getNom() == NomCarte.FEU && carte.getType() == TypeCarte.PARADE) {
                i.set(1);
            }
        });
        return i.get() == 1;
    }

    public boolean hasBotteVehiculePrio(Joueur joueur) {
        AtomicInteger i = new AtomicInteger();
        i.set(0);
        joueur.getPlateau().getPile(TypePile.BOTTES).getPileCarte().forEach( carte -> {
            if (carte.getNom() == NomCarte.VEHICULE_PRIORITAIRE) {
                i.set(1);
            }
        });
        return i.get() == 1;
    }
}
