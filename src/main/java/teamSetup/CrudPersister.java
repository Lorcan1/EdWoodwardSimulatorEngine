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

    public List<OutfieldP> addPlayerstoList(String name) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("select * from players");
       // stmt.setString(1,name);

        ResultSet rs = stmt.executeQuery();

        List<OutfieldP> players = new ArrayList<OutfieldP>();
        while ( rs.next() ) {
            OutfieldP player = new OutfieldP();
            player.setId(rs.getInt("ID"));
            player.setFirstName(rs.getString("FirstName"));
            players.add(player);
        }

        return players;
    }

}