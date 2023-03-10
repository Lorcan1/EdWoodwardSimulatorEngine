import matchEngine.MatchEngine;
import team.TeamSetup;

public class SimulateGame {

    public void simulateGame() {
        TeamSetup teamSetup = new TeamSetup();
        MatchEngine matchEngine = new MatchEngine( teamSetup.manchesterCity,teamSetup.tottenhamHotspurs);
        matchEngine.runMatchEngine();
    }
    public static void main(String[] args) {
        SimulateGame simulateGame = new SimulateGame();
        simulateGame.simulateGame();

    }

    }
