package ws.mahesh.travelmumbai.local;

/**
 * Created by Mahesh on 7/27/2014.
 */
public class LocalItem {
    String TRAIN_ID;
    String REACHES_DEST;
    String LEAVES_AT;
    String SOURCE;
    String DESTINATION;
    String CARS;
    String TYPE;

    public LocalItem(String TRAIN_ID, String LEAVES_AT, String REACHES_DEST, String SOURCE, String DESTINATION, String CARS, String TYPE) {
        this.TRAIN_ID = TRAIN_ID;
        this.REACHES_DEST = REACHES_DEST;
        this.LEAVES_AT = LEAVES_AT;
        this.SOURCE = SOURCE;
        this.DESTINATION = DESTINATION;
        this.CARS = CARS;
        this.TYPE = TYPE;
    }


    public String getTRAIN_ID() {
        return TRAIN_ID;
    }

    public String getREACHES_DEST() {
        return REACHES_DEST;
    }

    public String getLEAVES_AT() {
        return LEAVES_AT;
    }

    public String getSOURCE() {
        return SOURCE;
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public String getCARS() {
        return CARS;
    }

    public String getTYPE() {
        return TYPE;
    }


    @Override
    public String toString() {
        return ("Train id:" + TRAIN_ID + " SRC :" + SOURCE + " DEST : " + DESTINATION + " Leaves at:" + LEAVES_AT + " Arrives : " + REACHES_DEST + " Cars :" + CARS + " Type:" + TYPE);
    }
}
