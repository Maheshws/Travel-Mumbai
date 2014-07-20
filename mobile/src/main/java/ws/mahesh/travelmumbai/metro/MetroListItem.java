package ws.mahesh.travelmumbai.metro;

/**
 * Created by Mahesh on 7/20/2014.
 */
public class MetroListItem {
    public String DESTINATION;
    public String TOKEN;
    public String CARD;

    public MetroListItem(String DESTINATION, String TOKEN, String CARD) {
        super();
        this.DESTINATION = DESTINATION;
        this.TOKEN = TOKEN;
        this.CARD = CARD;
    }

    public String getDESTINATION() {
        return DESTINATION;
    }

    public String getTOKEN() {
        return TOKEN;
    }

    public String getCARD() {
        return CARD;
    }
}
