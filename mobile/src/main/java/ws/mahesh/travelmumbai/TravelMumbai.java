package ws.mahesh.travelmumbai;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.PushService;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import ws.mahesh.travelmumbai.misc.MegaBlockInfoActivity;

import static android.provider.Settings.Secure;

/**
 * Created by Mahesh on 7/21/2014.
 */
public class TravelMumbai extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this,getString(R.string.parseAppID),getString(R.string.parseClientKey));
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        String  android_id = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);

        SharedPreferences pref = getSharedPreferences("user_settings", 0); // 0 - for private mode
        Boolean Subscriber=pref.getBoolean("MegaBlock",false);
        if(!Subscriber)
            PushService.setDefaultPushCallback(this, MegaBlockInfoActivity.class);
        else
            PushService.setDefaultPushCallback(this,null);

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("UniqueId",android_id);
        installation.put("IPAddress",getLocalIpAddress());
        installation.put("MACAddress",getMACAddress());
        installation.put("Email",getUsername());
        installation.saveEventually();

    }

    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {
            //Log.e("log_tag", "Error Obtaining IP Address " + ex.toString());
        }
        return "NA";
    }
    private String getMACAddress() {
        String macadd;
        try {
            //Get MAC Address
            WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            macadd = info.getMacAddress();
        } catch (Exception e1) {
            macadd = "NA";
           // Log.e("log_tag", "Error Optaining MAC ID " + e1.toString());
        }
        if(macadd==null)
            macadd="NA";
        return macadd;
    }

    private String getUsername(){
        AccountManager manager = AccountManager.get(this);
        Account[] accounts = manager.getAccountsByType("com.google");
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {

            possibleEmails.add(account.name);
        }

        if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
            return possibleEmails.get(0);
        }else
            return "NA";
    }
}
