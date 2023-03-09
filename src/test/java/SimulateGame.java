import matchEngine.MatchEngine;
import team.TeamSetup;

public class SimulateGame {

    public void simulateGame() {
        TeamSetup teamSetup = new TeamSetup();
        MatchEngine matchEngine = new MatchEngine(teamSetup.hG, teamSetup.aG,teamSetup.hD1,teamSetup.hD2,teamSetup.aD1,teamSetup.aD2,teamSetup.hM1,teamSetup.hM2,teamSetup.aM1,teamSetup.aM2,teamSetup.hF1,teamSetup.hF2,teamSetup.aF1,teamSetup.aF2);
        matchEngine.runMatchEngine();
    }
    public static void main(String[] args) {
        SimulateGame simulateGame = new SimulateGame();
        simulateGame.simulateGame();

    }

    }
