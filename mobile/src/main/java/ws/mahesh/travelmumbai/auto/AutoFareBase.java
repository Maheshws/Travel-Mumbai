package ws.mahesh.travelmumbai.auto;

import java.text.DecimalFormat;

/**
 * Created by Mahesh on 7/9/2014.
 */
public class AutoFareBase {
    public double dist,fare,meter,night_fare;
    DecimalFormat df2 = new DecimalFormat("00.00");

    public void distanceBased(double tdist) {
        if(tdist<AutoBase.MIN_DISTANCE)
            defaultValues();
        else {
            dist=calculateFdistance(tdist);
            calculateReading();
            calculateFare();
        }
    }

    private void defaultValues() {
        dist=AutoBase.MIN_DISTANCE;
        fare=AutoBase.MIN_FARE;
        meter=1.0;
        int tempfare=(int) Math.round(fare);
        night_fare=tempfare+(tempfare*AutoBase.NIGHT_RATE_FACTOR);
    }

    public void readingBased(double tread) {
        if(tread<1.0) {
            defaultValues();
        }
        else {
            double tempread=tread-1.0;
            tempread=(tempread*2)+AutoBase.MIN_DISTANCE;
            distanceBased(tempread);
        }

    }

    public void calculateReading() {
        double tempdist=dist-AutoBase.MIN_DISTANCE;
        tempdist=tempdist/2;
        tempdist=tempdist+1;
        meter=Double.valueOf(df2.format(tempdist));
    }

    public void calculateFare() {
        fare=dist*AutoBase.PER_KM;
        int tempfare=(int) Math.round(fare);
        night_fare=tempfare+(tempfare*AutoBase.NIGHT_RATE_FACTOR);
    }

    public double calculateFdistance(double tdist) {
        double ddist=tdist*10;
        int tempdist=(int) ddist;
        tempdist=(tempdist+1)/2;
        tempdist=tempdist*2;
        double mydist=(double) tempdist/10;
        if(tdist>mydist)
            mydist=mydist+0.2;
        mydist=Double.valueOf(df2.format(mydist));
        return mydist;
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
