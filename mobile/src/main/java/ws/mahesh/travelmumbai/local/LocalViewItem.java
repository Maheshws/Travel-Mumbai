package ws.mahesh.travelmumbai.local;

/**
 * Created by Mahesh on 7/30/2014.
 */
public class LocalViewItem {
    public String TIME;
    public String STATION;

    public LocalViewItem(String TIME, String STATION) {
        this.TIME = TIME;
        this.STATION = STATION;
    }

    public String getTIME() {
        return TIME;
    }

    public String getSTATION() {
        return STATION;
    }
}
