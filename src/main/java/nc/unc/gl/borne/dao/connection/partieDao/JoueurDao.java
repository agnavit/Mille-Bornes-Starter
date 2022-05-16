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

    public ArrayList<Joueur> findJoueurWherePartieId(String idPartie) {
        var connexion = ConnectionHolder.INSTANCE.getConnection();
        try (var statement = connexion.createStatement()) {
            try (var result = statement.executeQuery("select * from joueur where IDPARTIE = " + idPartie + ";")) {
                return processResultSet(result);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertJoueur(String pseudoJoueur, int ageJoueur) {
        var connection = ConnectionHolder.INSTANCE.getConnection();
        try (var statement = connection.prepareStatement("INSERT INTO joueur ( PSEUDOJOUEUR, AGEJOUEUR, IDPARTIE) VALUES (?, ?, ?)")) {
            //statement.setString(1, "0");
            statement.setString(1, pseudoJoueur);
            statement.setInt(2, ageJoueur);
            statement.setString(3, null);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePartieJoueur(String idPartie, Joueur joueur) {
        var connection = ConnectionHolder.INSTANCE.getConnection();
        try (var statement = connection.prepareStatement("UPDATE JOUEUR SET IDPARTIE = ? WHERE PSEUDOJOUEUR = ?")) {
            statement.setString(1, idPartie);
            statement.setString(2, joueur.getPseudo());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Joueur> processResultSet(ResultSet result) throws SQLException {
        ArrayList<Joueur> listeJoueur = new ArrayList<>();
        while (result.next()) {
            Joueur joueur = new Joueur();
            //var id = result.getInt(1);
            var pseudo = result.getString(1);
            var age = result.getInt(2);
            var idPartie = result.getInt(3);
            joueur.setId(0);
            joueur.setPseudo(pseudo);
            joueur.setAge(age);
            listeJoueur.add(joueur);
        }
        return listeJoueur;
    }
}
