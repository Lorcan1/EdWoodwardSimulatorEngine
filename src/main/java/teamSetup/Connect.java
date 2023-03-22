package teamSetup;

import players.OutfieldP;
import team.TeamSetup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Connect {

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:data/EdWoodwardDb.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from players");

                    while ( rs.next() ) {

                        System.out.print("Name: "+rs.getString(1)); //reads the first column

                        System.out.println(", postcode: "+rs.getString(2)); //reads the second column



                    }
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //connect();
        CrudPersister crudPersister = new CrudPersister("jdbc:sqlite:data/EdWoodwardDb.db");
        List<OutfieldP> players = crudPersister.returnPlayersFromClub("MCFC");
        TeamSetup teamSetup = new TeamSetup();
        List<OutfieldP> filteredPlayers = teamSetup.sortFirstEleven(players);

    }
}
