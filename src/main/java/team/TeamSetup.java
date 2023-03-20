package team;

import players.*;

public class TeamSetup {

    //Manchester City
    private Goalkeeper mcG = new Goalkeeper("", "Ederson", "MCFC", "Brazil","GK", 27, 89, 18, 20,18);
    private Defender mcD1 = new Defender("Ruben", "Dias", "MCFC", "Portugal","CB", 24, 88, 13, 14, 12,11, 17, 17);
    private Defender mcD2 = new Defender("Aymeric", "Laporte", "MCFC", "Spain", "CB", 27, 86, 15,16, 13,11,15, 16);
    private Fullback mcDr = new Fullback("Kyle","Walker","MCFC","England","DR",31,85,13,13,13,11,13,12);
    private Fullback mcDL = new Fullback("Joao","Cancelo","MCFC","Portugal","DL",27,86,14,13,16,14,13,9);
    private DefensiveMidfielder mcDM = new DefensiveMidfielder("", "Rodri","MCFC", "Spain", "MC", 24, 87, 14, 15, 15,13,13,6,17);
    private Midfielder mcM1 = new Midfielder("Ilkay", "Gündogan", "MC", "Germany", "MCFC", 30, 85, 16, 16, 17, 13,14,11);
    private Midfielder mcM2 = new Midfielder("Kevin", "De Bruyne", "MC", "Belgium", "MCFC", 29, 91, 18, 15, 16, 9,15,19);
    private Forward mcF1 = new Forward("Erling", "Haaland", "MCFC", "Norway", "ST", 20, 88,13, 16, 9, 19,17, 16, 19);
    private Forward mcF2 = new Forward("Riyad", "Mahrez", "MCFC", "Algeria", "AMR", 30, 85,16, 15, 16, 15, 17, 19, 15);

    public Team manchesterCity = new Team("Manchester City","MCFC",mcG,mcD1,mcD2,mcDL,mcDr, mcDM, mcM1,mcM2,mcF1,mcF2);

    //Tottenham Hotspurs
    private Goalkeeper thG = new Goalkeeper("Hugo","Lloris", "Spurs", "France","GK", 34, 86,15,7,14);
    private Defender thD1 = new Defender("Cristan","Romero", "Spurs", "Argentina","DC",23, 83,12,13, 9, 6, 17,17);
    private Defender thD2 = new Defender("Eric","Dier", "Spurs", "England","DC",27, 80,13,13, 11, 13,15,15);
    private Fullback thDl = new Fullback("Ivan","Perišic","Spurs","Croatia", "DL",32,84,13,12,12,13,9,8);
    private Fullback thDr = new Fullback("Pedro","Porro","Spurs","Spain","DR",21,81,13,12,16,15,13,8);
    private DefensiveMidfielder thDM = new DefensiveMidfielder("Pierre-Emile","Højbjerg", "Spurs", "Denmark","MC",25, 84,15, 14,14,16,11, 8,14);
    private Midfielder thM1 = new Midfielder("Oliver","Skipp", "Spurs", "England","MC",20, 77,15, 16,12,14,10,12);
    private Midfielder thM2 = new Midfielder("Rodrigo","Bentancur", "Spurs", "Uruguay","MC",23, 81,14, 11,15,16,14,12);
    private Forward thF1 = new Forward("Harry","Kane", "Spurs", "England","ST",27, 89, 18,18, 16, 17,14,15,19);
    private Forward thF2 = new Forward("Heung-Min", "Son", "Spurs", "South Korea","AML",28, 88, 12,17, 13, 18,15,13,18);

    public Team tottenhamHotspurs = new Team("Tottenham","Spurs",thG,thD1,thD2, thDl, thDr, thDM, thM1,thM2,thF1,thF2);

    public Midfielder[] addMarkers(Midfielder midfielders[], Fullback fullbacks[],Forward wingers[]){
        midfielders[0].setMarker(midfielders[2]);
        midfielders[1].setMarker(midfielders[3]);
        midfielders[2].setMarker(midfielders[0]);
        midfielders[3].setMarker(midfielders[1]);

        return midfielders;
    }
}