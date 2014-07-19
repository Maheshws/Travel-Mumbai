package ws.mahesh.travelmumbai.metro;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class MetroFareBase {
    int FaresToken[][];
    int FaresCard[][];


    public MetroFareBase() {
        FaresToken = new int[MetroStations.COUNT][MetroStations.COUNT];
        FaresCard = new int[MetroStations.COUNT][MetroStations.COUNT];
        AssignFare();
    }

    public void AssignFare() {
        //For Token
        //Versova
        FaresToken[0][0] = 0;
        FaresToken[0][1] = FaresToken[1][0] = 10;
        FaresToken[0][2] = FaresToken[2][0] = 15;
        FaresToken[0][3] = FaresToken[3][0] = 15;
        FaresToken[0][4] = FaresToken[4][0] = 15;
        FaresToken[0][5] = FaresToken[5][0] = 20;
        FaresToken[0][6] = FaresToken[6][0] = 20;
        FaresToken[0][7] = FaresToken[7][0] = 20;
        FaresToken[0][8] = FaresToken[8][0] = 20;
        FaresToken[0][9] = FaresToken[9][0] = 20;
        FaresToken[0][10] = FaresToken[10][0] = 20;
        FaresToken[0][11] = FaresToken[11][0] = 20;
        // D N Nagar
        FaresToken[1][1] = 0;
        FaresToken[1][2] = FaresToken[2][1] = 10;
        FaresToken[1][3] = FaresToken[3][1] = 15;
        FaresToken[1][4] = FaresToken[4][1] = 15;
        FaresToken[1][5] = FaresToken[5][1] = 15;
        FaresToken[1][6] = FaresToken[6][1] = 20;
        FaresToken[1][7] = FaresToken[7][1] = 20;
        FaresToken[1][8] = FaresToken[8][1] = 20;
        FaresToken[1][9] = FaresToken[9][1] = 20;
        FaresToken[1][10] = FaresToken[10][1] = 20;
        FaresToken[1][11] = FaresToken[11][1] = 20;
        //Azad Nagar
        FaresToken[2][2] = 0;
        FaresToken[2][3] = FaresToken[3][2] = 10;
        FaresToken[2][4] = FaresToken[4][2] = 15;
        FaresToken[2][5] = FaresToken[5][2] = 15;
        FaresToken[2][6] = FaresToken[6][2] = 15;
        FaresToken[2][7] = FaresToken[7][2] = 15;
        FaresToken[2][8] = FaresToken[8][2] = 20;
        FaresToken[2][9] = FaresToken[9][2] = 20;
        FaresToken[2][10] = FaresToken[10][2] = 20;
        FaresToken[2][11] = FaresToken[11][2] = 20;
        //Andheri
        FaresToken[3][3] = 0;
        FaresToken[3][4] = FaresToken[4][3] = 10;
        FaresToken[3][5] = FaresToken[5][3] = 15;
        FaresToken[3][6] = FaresToken[6][3] = 15;
        FaresToken[3][7] = FaresToken[7][3] = 15;
        FaresToken[3][8] = FaresToken[8][3] = 15;
        FaresToken[3][9] = FaresToken[9][3] = 20;
        FaresToken[3][10] = FaresToken[10][3] = 20;
        FaresToken[3][11] = FaresToken[11][3] = 20;
        //WEH
        FaresToken[4][4] = 0;
        FaresToken[4][5] = FaresToken[5][4] = 10;
        FaresToken[4][6] = FaresToken[6][4] = 15;
        FaresToken[4][7] = FaresToken[7][4] = 15;
        FaresToken[4][8] = FaresToken[8][4] = 15;
        FaresToken[4][9] = FaresToken[9][4] = 15;
        FaresToken[4][10] = FaresToken[10][4] = 20;
        FaresToken[4][11] = FaresToken[11][4] = 20;
        //Chakala
        FaresToken[5][5] = 0;
        FaresToken[5][6] = FaresToken[6][5] = 10;
        FaresToken[5][7] = FaresToken[7][5] = 10;
        FaresToken[5][8] = FaresToken[8][5] = 15;
        FaresToken[5][9] = FaresToken[9][5] = 15;
        FaresToken[5][10] = FaresToken[10][5] = 15;
        FaresToken[5][11] = FaresToken[11][5] = 20;
        //Airport Road
        FaresToken[6][6] = 0;
        FaresToken[6][7] = FaresToken[7][6] = 10;
        FaresToken[6][8] = FaresToken[8][6] = 10;
        FaresToken[6][9] = FaresToken[9][6] = 15;
        FaresToken[6][10] = FaresToken[10][6] = 15;
        FaresToken[6][11] = FaresToken[11][6] = 15;
        // Marol Naka
        FaresToken[7][7] = 0;
        FaresToken[7][8] = FaresToken[8][7] = 10;
        FaresToken[7][9] = FaresToken[9][7] = 15;
        FaresToken[7][10] = FaresToken[10][7] = 15;
        FaresToken[7][11] = FaresToken[11][7] = 15;
        //Saki Naka
        FaresToken[8][8] = 0;
        FaresToken[8][9] = FaresToken[9][8] = 10;
        FaresToken[8][10] = FaresToken[10][8] = 10;
        FaresToken[8][11] = FaresToken[11][8] = 15;
        //Asalpha
        FaresToken[9][9] = 0;
        FaresToken[9][10] = FaresToken[10][9] = 10;
        FaresToken[9][11] = FaresToken[11][9] = 10;
        //Jagruti Nagar
        FaresToken[10][10] = 0;
        FaresToken[10][11] = FaresToken[11][10] = 10;
        //Ghatkopar
        FaresToken[11][11] = 0;

        //For Card
        //Versova
        FaresCard[0][0] = 0;
        FaresCard[0][1] = FaresCard[1][0] = 10;
        FaresCard[0][2] = FaresCard[2][0] = 15;
        FaresCard[0][3] = FaresCard[3][0] = 15;
        FaresCard[0][4] = FaresCard[4][0] = 15;
        FaresCard[0][5] = FaresCard[5][0] = 15;
        FaresCard[0][6] = FaresCard[6][0] = 15;
        FaresCard[0][7] = FaresCard[7][0] = 15;
        FaresCard[0][8] = FaresCard[8][0] = 15;
        FaresCard[0][9] = FaresCard[9][0] = 15;
        FaresCard[0][10] = FaresCard[10][0] = 15;
        FaresCard[0][11] = FaresCard[11][0] = 15;
        // D N Nagar
        FaresCard[1][1] = 0;
        FaresCard[1][2] = FaresCard[2][1] = 10;
        FaresCard[1][3] = FaresCard[3][1] = 15;
        FaresCard[1][4] = FaresCard[4][1] = 15;
        FaresCard[1][5] = FaresCard[5][1] = 15;
        FaresCard[1][6] = FaresCard[6][1] = 15;
        FaresCard[1][7] = FaresCard[7][1] = 15;
        FaresCard[1][8] = FaresCard[8][1] = 15;
        FaresCard[1][9] = FaresCard[9][1] = 15;
        FaresCard[1][10] = FaresCard[10][1] = 15;
        FaresCard[1][11] = FaresCard[11][1] = 15;
        //Azad Nagar
        FaresCard[2][2] = 0;
        FaresCard[2][3] = FaresCard[3][2] = 10;
        FaresCard[2][4] = FaresCard[4][2] = 15;
        FaresCard[2][5] = FaresCard[5][2] = 15;
        FaresCard[2][6] = FaresCard[6][2] = 15;
        FaresCard[2][7] = FaresCard[7][2] = 15;
        FaresCard[2][8] = FaresCard[8][2] = 15;
        FaresCard[2][9] = FaresCard[9][2] = 15;
        FaresCard[2][10] = FaresCard[10][2] = 15;
        FaresCard[2][11] = FaresCard[11][2] = 15;
        //Andheri
        FaresCard[3][3] = 0;
        FaresCard[3][4] = FaresCard[4][3] = 10;
        FaresCard[3][5] = FaresCard[5][3] = 15;
        FaresCard[3][6] = FaresCard[6][3] = 15;
        FaresCard[3][7] = FaresCard[7][3] = 15;
        FaresCard[3][8] = FaresCard[8][3] = 15;
        FaresCard[3][9] = FaresCard[9][3] = 15;
        FaresCard[3][10] = FaresCard[10][3] = 15;
        FaresCard[3][11] = FaresCard[11][3] = 15;
        //WEH
        FaresCard[4][4] = 0;
        FaresCard[4][5] = FaresCard[5][4] = 10;
        FaresCard[4][6] = FaresCard[6][4] = 15;
        FaresCard[4][7] = FaresCard[7][4] = 15;
        FaresCard[4][8] = FaresCard[8][4] = 15;
        FaresCard[4][9] = FaresCard[9][4] = 15;
        FaresCard[4][10] = FaresCard[10][4] = 15;
        FaresCard[4][11] = FaresCard[11][4] = 15;
        //Chakala
        FaresCard[5][5] = 0;
        FaresCard[5][6] = FaresCard[6][5] = 10;
        FaresCard[5][7] = FaresCard[7][5] = 10;
        FaresCard[5][8] = FaresCard[8][5] = 15;
        FaresCard[5][9] = FaresCard[9][5] = 15;
        FaresCard[5][10] = FaresCard[10][5] = 15;
        FaresCard[5][11] = FaresCard[11][5] = 15;
        //Airport Road
        FaresCard[6][6] = 0;
        FaresCard[6][7] = FaresCard[7][6] = 10;
        FaresCard[6][8] = FaresCard[8][6] = 10;
        FaresCard[6][9] = FaresCard[9][6] = 15;
        FaresCard[6][10] = FaresCard[10][6] = 15;
        FaresCard[6][11] = FaresCard[11][6] = 15;
        // Marol Naka
        FaresCard[7][7] = 0;
        FaresCard[7][8] = FaresCard[8][7] = 10;
        FaresCard[7][9] = FaresCard[9][7] = 15;
        FaresCard[7][10] = FaresCard[10][7] = 15;
        FaresCard[7][11] = FaresCard[11][7] = 15;
        //Saki Naka
        FaresCard[8][8] = 0;
        FaresCard[8][9] = FaresCard[9][8] = 10;
        FaresCard[8][10] = FaresCard[10][8] = 10;
        FaresCard[8][11] = FaresCard[11][8] = 15;
        //Asalpha
        FaresCard[9][9] = 0;
        FaresCard[9][10] = FaresCard[10][9] = 10;
        FaresCard[9][11] = FaresCard[11][9] = 10;
        //Jagruti Nagar
        FaresCard[10][10] = 0;
        FaresCard[10][11] = FaresCard[11][10] = 10;
        //Ghatkopar
        FaresCard[11][11] = 0;
    }

    public String getStation(int id) {
        return MetroStations.STATIONS[id];
    }

    public int getTokenFare(int src, int dest) {
        return FaresToken[src][dest];
    }

    public int getCardFare(int src, int dest) {
        return FaresCard[src][dest];
    }

    public int getStationID(String stn) {
        for (int i = 0; i < MetroStations.COUNT; i++) {
            if (stn.equalsIgnoreCase(MetroStations.STATIONS[i]))
                return i;
        }
        return 99999;
    }

}
