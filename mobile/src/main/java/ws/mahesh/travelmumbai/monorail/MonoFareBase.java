package ws.mahesh.travelmumbai.monorail;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class MonoFareBase {
    int FaresToken[][];
    int FaresCard[][];


    public MonoFareBase() {
        FaresToken = new int[MonoStations.COUNT][MonoStations.COUNT];
        FaresCard = new int[MonoStations.COUNT][MonoStations.COUNT];
        AssignFare();
    }

    public void AssignFare() {
        //For Token
        //Chembur
        FaresToken[0][0] = 0;
        FaresToken[0][1] = FaresToken[1][0] = 5;
        FaresToken[0][2] = FaresToken[2][0] = 5;
        FaresToken[0][3] = FaresToken[3][0] = 7;
        FaresToken[0][4] = FaresToken[4][0] = 7;
        FaresToken[0][5] = FaresToken[5][0] = 9;
        FaresToken[0][6] = FaresToken[6][0] = 11;
        // V.N.Purav/RC Marg Junction"
        FaresToken[1][1] = 0;
        FaresToken[1][2] = FaresToken[2][1] = 5;
        FaresToken[1][3] = FaresToken[3][1] = 5;
        FaresToken[1][4] = FaresToken[4][1] = 7;
        FaresToken[1][5] = FaresToken[5][1] = 9;
        FaresToken[1][6] = FaresToken[6][1] = 11;
        //Fertilizer Township
        FaresToken[2][2] = 0;
        FaresToken[2][3] = FaresToken[3][2] = 5;
        FaresToken[2][4] = FaresToken[4][2] = 5;
        FaresToken[2][5] = FaresToken[5][2] = 7;
        FaresToken[2][6] = FaresToken[6][2] = 9;
        //Bharat Petroleum
        FaresToken[3][3] = 0;
        FaresToken[3][4] = FaresToken[4][3] = 5;
        FaresToken[3][5] = FaresToken[5][3] = 7;
        FaresToken[3][6] = FaresToken[6][3] = 9;
        //Mysore Colony
        FaresToken[4][4] = 0;
        FaresToken[4][5] = FaresToken[5][4] = 5;
        FaresToken[4][6] = FaresToken[6][4] = 7;
        //Bhakti Park
        FaresToken[5][5] = 0;
        FaresToken[5][6] = FaresToken[6][5] = 5;
        //Wadala Depot
        FaresToken[6][6] = 0;

        //For Card
        //Chembur
        FaresCard[0][0] = 0;
        FaresCard[0][1] = FaresCard[1][0] = 5;
        FaresCard[0][2] = FaresCard[2][0] = 5;
        FaresCard[0][3] = FaresCard[3][0] = 7;
        FaresCard[0][4] = FaresCard[4][0] = 7;
        FaresCard[0][5] = FaresCard[5][0] = 9;
        FaresCard[0][6] = FaresCard[6][0] = 11;
        // V.N.Purav/RC Marg Junction
        FaresCard[1][1] = 0;
        FaresCard[1][2] = FaresCard[2][1] = 5;
        FaresCard[1][3] = FaresCard[3][1] = 5;
        FaresCard[1][4] = FaresCard[4][1] = 7;
        FaresCard[1][5] = FaresCard[5][1] = 9;
        FaresCard[1][6] = FaresCard[6][1] = 11;
        //Fertilizer Township
        FaresCard[2][2] = 0;
        FaresCard[2][3] = FaresCard[3][2] = 5;
        FaresCard[2][4] = FaresCard[4][2] = 5;
        FaresCard[2][5] = FaresCard[5][2] = 7;
        FaresCard[2][6] = FaresCard[6][2] = 9;
        //Bharat Petroleum
        FaresCard[3][3] = 0;
        FaresCard[3][4] = FaresCard[4][3] = 5;
        FaresCard[3][5] = FaresCard[5][3] = 7;
        FaresCard[3][6] = FaresCard[6][3] = 9;
        //Mysore Colony
        FaresCard[4][4] = 0;
        FaresCard[4][5] = FaresCard[5][4] = 5;
        FaresCard[4][6] = FaresCard[6][4] = 7;
        //Bhakti Park
        FaresCard[5][5] = 0;
        FaresCard[5][6] = FaresCard[6][5] = 5;
        //Wadala Depot
        FaresCard[6][6] = 0;
    }

    public String getStation(int id) {
        return MonoStations.STATIONS[id];
    }

    public int getTokenFare(int src, int dest) {
        return FaresToken[src][dest];
    }

    public int getCardFare(int src, int dest) {
        return FaresCard[src][dest];
    }

    public int getStationID(String stn) {
        for (int i = 0; i < MonoStations.COUNT; i++) {
            if (stn.equalsIgnoreCase(MonoStations.STATIONS[i]))
                return i;
        }
        return 99999;
    }

}
