package ws.mahesh.travelmumbai.taxi;

/**
 * Created by Mahesh on 7/15/2014.
 */
public class TaxiListItem {
    public String READING;
    public String DISTANCE;
    public String DAY_FARE;
    public String NIGHT_FARE;

    public TaxiListItem(String READING, String DISTANCE, String DAY_FARE, String NIGHT_FARE) {
        super();
        this.READING = READING;
        this.DISTANCE = DISTANCE;
        this.DAY_FARE = DAY_FARE;
        this.NIGHT_FARE = NIGHT_FARE;
    }

    public String getREADING() {
        return READING;
    }

    public String getDISTANCE() {
        return DISTANCE;
    }

    public String getDAY_FARE() {
        return DAY_FARE;
    }

    public String getNIGHT_FARE() {
        return NIGHT_FARE;
    }
}
