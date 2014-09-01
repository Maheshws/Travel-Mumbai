package ws.mahesh.travelmumbai.local;

/**
 * Created by Mahesh on 7/30/2014.
 */
public class LocalViewItem {
    public String TIME;
    public String STATION;
    public String MAJOR;

    public LocalViewItem(String TIME, String STATION, String MAJOR) {
        this.TIME = TIME;
        this.STATION = STATION;
        this.MAJOR = MAJOR;
    }

    public String getTIME() {
        return TIME;
    }

    public String getSTATION() {
        return STATION;
    }

    public String getMAJOR() {
        return MAJOR;
    }
}
