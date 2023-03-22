package teamSetup;

import players.OutfieldP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudPersister {

    private Connection conn = null;

    public CrudPersister(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public List<OutfieldP> returnPlayersFromClub(String name) throws SQLException {
        String sqlQuery = "select * from players where club =" + "\"" + name +  "\"";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
       // stmt.setString(1,name);
        System.out.println(stmt);
        ResultSet rs = stmt.executeQuery();

        List<OutfieldP> players = new ArrayList<OutfieldP>();
        while ( rs.next() ) {
            OutfieldP player = new OutfieldP();
            player.setId(rs.getInt("ID"));
            player.setFirstName(rs.getString("FirstName"));
            player.setPosition(rs.getString("Position"));
            players.add(player);
        }

        return players;
    }

}