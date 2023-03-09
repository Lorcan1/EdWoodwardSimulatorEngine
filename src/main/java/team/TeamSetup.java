package team;

import players.Defender;
import players.Forward;
import players.Goalkeeper;
import players.Midfielder;

public class TeamSetup {

    //Manchester City
    public Goalkeeper hG = new Goalkeeper("", "Ederson", "GK", 27, 18, 18);
    public Defender hD1 = new Defender("Ruben", "Dias", "CB", 24, 17, 17);
    public Defender hD2 = new Defender("Aymeric", "Laporte", "CB", 27, 16, 15);
    public Midfielder hM1 = new Midfielder("", "Rodri", "MC", 24, 14, 15, 15);
    public Midfielder hM2 = new Midfielder("Kevin", "De Bruyne", "MC", 29, 18, 16, 9);
    public Forward hF1 = new Forward("Erling", "Haaland", "ST", 20, 16, 16, 19);
    public Forward hF2 = new Forward("Riyad", "Mahrez", "AMR", 30, 17, 19, 15);

    //Tottenham Hotspurs
    public Goalkeeper aG = new Goalkeeper("Hugo","Lloris","GK", 34,15,14);
    public Defender aD1 = new Defender("Cristan","Romero","DC",23,17,17);
    public Defender aD2 = new Defender("Eric","Dier","DC",27,15,15);
    public Midfielder aM1 = new Midfielder("Pierre-Emile","Højbjerg","MC",25,15,14,16);
    public Midfielder aM2 = new Midfielder("Rodrigo","Bentancur","MC",23,14,15,16);
    public Forward aF1 = new Forward("Harry","Kane","ST",27,14,15,19);
    public Forward aF2 = new Forward("Heung-Min", "Son","AML",28,15,13,18);
}