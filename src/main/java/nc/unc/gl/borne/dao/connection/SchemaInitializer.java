package nc.unc.gl.borne.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

public class SchemaInitializer {

    private final Connection connection;

    SchemaInitializer(Connection connection) {
        this.connection = connection;
    }

    public void initialize() throws SQLException {
        createSchema();
        insertJoueur("JoueurTest", 21);
    }

    private void createSchema() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE joueur (" +
                " IDJOUEUR NUMERIC(9,4) NOT NULL PRIMARY KEY, " +
                " PSEUDOJOUEUR VARCHAR NOT NULL, " +
                " AGEJOUEUR NUMERIC(9,4) NOT NULL, " +
                " IDPARTIE NUMERIC(9,4) NOT NULL " +
                ")");
            /*statement.execute("CREATE TABLE Partie (" +
                " idPartie INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                " pseudoJoueurGagnant VARCHAR NOT NULL " +
                ")");*/
        }
    }

    private void insertJoueur(String pseudoJoueur, int ageJoueur) throws SQLException {
        try (var statement = connection.prepareStatement("INSERT INTO joueur (IDJOUEUR, PSEUDOJOUEUR, AGEJOUEUR, IDPARTIE) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, "0");
            statement.setString(2, pseudoJoueur);
            statement.setInt(3, ageJoueur);
            statement.setInt(4, 1);
            statement.execute();
        }
    }
}
