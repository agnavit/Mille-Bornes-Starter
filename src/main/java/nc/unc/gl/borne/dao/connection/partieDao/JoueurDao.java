package nc.unc.gl.borne.dao.connection.partieDao;

import nc.unc.gl.borne.dao.connection.ConnectionHolder;
import nc.unc.gl.borne.joueur.Joueur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JoueurDao {

    public ArrayList<Joueur> findAll() {
        var connexion = ConnectionHolder.INSTANCE.getConnection();
        try (var statement = connexion.createStatement()) {
            try (var result = statement.executeQuery("select * from joueur;")) {
                return processResultSet(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Joueur> processResultSet(ResultSet result) throws SQLException {
        ArrayList<Joueur> listeJoueur = new ArrayList<>();
        while (result.next()) {
            Joueur joueur = new Joueur();
            var id = result.getInt(1);
            var pseudo = result.getString(2);
            var age = result.getInt(3);
            var idPartie = result.getInt(4);
            joueur.setId(id);
            joueur.setPseudo(pseudo);
            joueur.setAge(age);
            listeJoueur.add(joueur);
        }
        return listeJoueur;
    }
}
