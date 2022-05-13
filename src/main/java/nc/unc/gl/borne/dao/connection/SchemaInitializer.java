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
    }

    private void createSchema() throws SQLException {
        try (var statement = connection.createStatement()) {
            statement.execute("CREATE TABLE joueur (" +
                //" IDJOUEUR NUMERIC(9,4) NOT NULL PRIMARY KEY, " +
                " PSEUDOJOUEUR VARCHAR NOT NULL, " +
                " AGEJOUEUR NUMERIC(9,4) NOT NULL, " +
                " IDPARTIE VARCHAR " +
                ")");
            /*statement.execute("CREATE TABLE Partie (" +
                " idPartie INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                " idJoueur1 INT," +
                " idJoueur2 INT" +
                ")");*/
        }
    }
}
