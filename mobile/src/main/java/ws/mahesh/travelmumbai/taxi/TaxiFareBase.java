package ws.mahesh.travelmumbai.taxi;

import java.text.DecimalFormat;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class TaxiFareBase {
    public double dist,fare,meter,night_fare;
    DecimalFormat df2 = new DecimalFormat("00.00");

    public void distanceBased(double tdist) {
        dist=Double.valueOf(df2.format(tdist));
        calculateReading();
        calculateFare();
    }
    public void readingBased(double tread) {
        double tempread=tread-1.0;
        tempread=(tempread*1.67)+TaxiBase.MIN_DISTANCE;
        distanceBased(tempread);
    }

    public void calculateReading() {
        double tempdist=dist-TaxiBase.MIN_DISTANCE;
        tempdist=tempdist/1.67;
        tempdist=tempdist+1;
        meter=Double.valueOf(df2.format(tempdist));
    }

    public void calculateFare() {
        fare=dist*TaxiBase.PER_KM;
        int tempfare=(int) Math.round(fare);
        night_fare=fare+(fare*TaxiBase.NIGHT_RATE_FACTOR);
    }


    public double getDistance() {
        return dist;
    }
    public int getFare() {
        return (int)Math.round(fare);
    }
    public int getNightFare() {
        return (int)Math.round(night_fare);
    }
    public double getReading() {
        return meter;
    }
}
