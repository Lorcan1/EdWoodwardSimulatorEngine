package teamSetup;

import players.Goalkeeper;
import players.OutfieldPlayer;
import players.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudPersister {

    private Connection conn = null;

    public CrudPersister(String url) throws SQLException {
        conn = DriverManager.getConnection(url);
    }

    public List<Player> returnOutfieldPlayersFromClub(String name) throws SQLException {
        String sqlQuery = "select * from Players where club =" + "\"" + name +  "\"";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        ResultSet rs = stmt.executeQuery();

        List<Player> players = new ArrayList<Player>();
        while ( rs.next() ) {
            OutfieldPlayer player = new OutfieldPlayer(rs.getInt("ID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Club"),rs.getString("Nation"),rs.getString("Position"),rs.getInt("Age"),rs.getInt("Overall"));
            players.add(player);
        }

        players = returnGoalKeepersFromCLub(name,players);

        return players;
    }

    public List<Player> returnGoalKeepersFromCLub(String name, List<Player> players) throws SQLException{
        String sqlQuery = "select * from Goalkeepers where club =" + "\"" + name +  "\"";
        PreparedStatement stmt = conn.prepareStatement(sqlQuery);
        ResultSet rs = stmt.executeQuery();
        while ( rs.next() ) {
            Goalkeeper goalkeeper = new Goalkeeper(rs.getInt("ID"),rs.getString("FirstName"),rs.getString("LastName"),rs.getString("Club"),rs.getString("Nation"),rs.getString("Position"),rs.getInt("Age"),rs.getInt("Overall"),rs.getDouble("Aerial Reach"),rs.getDouble("Command of Area"));
            players.add(goalkeeper);
        }

        return players;
    }

}