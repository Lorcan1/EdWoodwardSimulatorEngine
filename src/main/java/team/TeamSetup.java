package team;

import players.Defender;
import players.Forward;
import players.Goalkeeper;
import players.Midfielder;

public class TeamSetup {

    //Manchester City
    private Goalkeeper mcG = new Goalkeeper("", "Ederson", "GK", 27, 89, 18, 18);
    private Defender mcD1 = new Defender("Ruben", "Dias", "CB", 24, 88, 17, 17,13);
    private Defender mcD2 = new Defender("Aymeric", "Laporte", "CB", 27, 86, 16, 15,15);
    private Midfielder mcM1 = new Midfielder("", "Rodri", "MC", 24, 87, 14, 15, 15,13);
    private Midfielder mcM2 = new Midfielder("Kevin", "De Bruyne", "MC", 29, 91, 18, 16, 9,15);
    private Forward mcF1 = new Forward("Erling", "Haaland", "ST", 20, 88, 16, 16, 19);
    private Forward mcF2 = new Forward("Riyad", "Mahrez", "AMR", 30, 85, 17, 19, 15);

    public Team manchesterCity = new Team("Manchester City","MCFC",mcG,mcD1,mcD2,mcM1,mcM2,mcF1,mcF2);

    //Tottenham Hotspurs
    private Goalkeeper thG = new Goalkeeper("Hugo","Lloris","GK", 34, 86,15,14);
    private Defender thD1 = new Defender("Cristan","Romero","DC",23, 83,17,17,12);
    private Defender thD2 = new Defender("Eric","Dier","DC",27, 80,15,15,13);
    private Midfielder thM1 = new Midfielder("Pierre-Emile","Højbjerg","MC",25, 84,15,14,16,11);
    private Midfielder thM2 = new Midfielder("Rodrigo","Bentancur","MC",23, 81,14,15,16,14);
    private Forward thF1 = new Forward("Harry","Kane","ST",27, 89,14,15,19);
    private Forward thF2 = new Forward("Heung-Min", "Son","AML",28, 88,15,13,18);

    public Team tottenhamHotspurs = new Team("Tottenham","Spurs",thG,thD1,thD2,thM1,thM2,thF1,thF2);
}