package ws.mahesh.travelmumbai.local;

/**
 * Created by Mahesh on 7/25/2014.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static String DB_COLUMN_STATION_NAME;
    private static String DB_NAME;
    private static String DB_NAME_PATH;
    private static String DB_PATH = "/data/data/ws.mahesh.travelmumbai/databases/";
    private static String DB_TABLE_CRDOWNTIMETABLE;
    private static String DB_TABLE_CRDOWNTRAINS;
    private static String DB_TABLE_CRSTATIONS;
    private static String DB_TABLE_CRUPTIMETABLE;
    private static String DB_TABLE_CRUPTRAINS;
    private static String DB_TABLE_HRDOWNTIMETABLE;
    private static String DB_TABLE_HRDOWNTRAINS;
    private static String DB_TABLE_HRSTATIONS;
    private static String DB_TABLE_HRUPTIMETABLE;
    private static String DB_TABLE_HRUPTRAINS;
    private static String DB_TABLE_STATIONS;
    private static String DB_TABLE_TIMETABLE;
    private static String DB_TABLE_TRAINS;
    private static String DB_TABLE_WRDOWNTIMETABLE;
    private static String DB_TABLE_WRDOWNTRAINS;
    private static String DB_TABLE_WRSTATIONS;
    private static String DB_TABLE_WRUPTIMETABLE;
    private static String DB_TABLE_WRUPTRAINS;
    private static int DB_VERSION;
    private static String cars;
    private static String dename = "Ends";
    private static String destinationtimetablequery;
    private static String fullstationcode;
    private static int dr1;
    private static int dr2;
    private static int dr3;
    private static int dr4;
    private static String seltrain;
    private static String speed;
    private static String sqliteds;
    private static String sqlitess;
    private static int sr1;
    private static int sr2;
    private static int sr3;
    private static int sr4;
    private static String start;
    private static String dest;
    private static String stname;
    private final Context myContext;
    private SQLiteDatabase myDataBase;

    DateFormat f1 = new SimpleDateFormat("H:mm");
    DateFormat f2 = new SimpleDateFormat("h:mm a");

    static {
        DB_NAME = "timetable.sqlite";
        DB_NAME_PATH = "local/timetable.sqlite";
        DB_TABLE_STATIONS = "crstations";
        DB_TABLE_CRSTATIONS = "crstations";
        DB_TABLE_WRSTATIONS = "wrstations";
        DB_TABLE_HRSTATIONS = "hrstations";
        DB_COLUMN_STATION_NAME = "name";
        DB_VERSION = 1;
        DB_TABLE_TRAINS = "cruptrains";
        DB_TABLE_CRUPTRAINS = "cruptrains";
        DB_TABLE_WRUPTRAINS = "wruptrains";
        DB_TABLE_HRUPTRAINS = "hruptrains";
        DB_TABLE_CRDOWNTRAINS = "crdowntrains";
        DB_TABLE_WRDOWNTRAINS = "wrdowntrains";
        DB_TABLE_HRDOWNTRAINS = "hrdowntrains";
        DB_TABLE_TIMETABLE = "cruptimetable";
        DB_TABLE_CRUPTIMETABLE = "cruptimetable";
        DB_TABLE_WRUPTIMETABLE = "wruptimetable";
        DB_TABLE_HRUPTIMETABLE = "hruptimetable";
        DB_TABLE_CRDOWNTIMETABLE = "crdowntimetable";
        DB_TABLE_WRDOWNTIMETABLE = "wrdowntimetable";
        DB_TABLE_HRDOWNTIMETABLE = "hrdowntimetable";
        cars = "null";
        speed = "null";
        start = "null";
        dest = "null";
        sqlitess = "null";
        sqliteds = "null";
        destinationtimetablequery = "null";
        fullstationcode="null";
        seltrain = "null";
        stname = "Starts";
    }

    public DatabaseAdapter(Context paramContext) {
        super(paramContext, DB_NAME, null, DB_VERSION);
        this.myContext = paramContext;
    }

    private boolean checkDataBase() {
        SQLiteDatabase localSQLiteDatabase1 = null;

        try {
            DB_PATH = this.myContext.getDatabasePath(DB_NAME).getPath();
            Log.i(getClass().getSimpleName(), "Checking DB existence at " + DB_PATH);
            SQLiteDatabase localSQLiteDatabase2 = SQLiteDatabase.openDatabase(DB_PATH, null, 1);
            localSQLiteDatabase1 = localSQLiteDatabase2;
        } catch (SQLiteException localSQLiteException) {
            localSQLiteDatabase1 = null;
        } catch (NullPointerException e) {

        }
        if (localSQLiteDatabase1 != null) {
            localSQLiteDatabase1.close();
        }
        return localSQLiteDatabase1 != null;
    }

    private void copyDataBase()
            throws IOException {
        Log.i(getClass().getSimpleName(), "Inside copyDatabase");
        InputStream localInputStream = this.myContext.getAssets().open(DB_NAME_PATH);
        FileOutputStream localFileOutputStream = new FileOutputStream(DB_PATH);
        byte[] arrayOfByte = new byte[1024];
        for (; ; ) {
            int i = localInputStream.read(arrayOfByte);
            if (i <= 0) {
                localFileOutputStream.flush();
                localFileOutputStream.close();
                localInputStream.close();
                Log.i(getClass().getSimpleName(), "Copy Database completed");
                Log.i(getClass().getSimpleName(), "DB copied at " + DB_PATH);
                return;
            }
            localFileOutputStream.write(arrayOfByte, 0, i);
        }
    }

    public void close() {
        try {
            Log.i(getClass().getSimpleName(), "Inside close, DB being closed");
            if (this.myDataBase != null) {
                this.myDataBase.close();
            }
            super.close();
            Log.i(getClass().getSimpleName(), "Database Closed");
            return;
        } finally {
        }
    }

    public void createDataBase()
            throws IOException {
        Log.i(getClass().getSimpleName(), "Check if DB already exists");
        if (checkDataBase()) {
            Log.i(getClass().getSimpleName(), "DB Already exists");
            Log.i(getClass().getSimpleName(), "Get Writable Database called to check if DBVERSION has increased and if DB needs copying");
            getWritableDatabase();
            return;
        }
        Log.i(getClass().getSimpleName(), "DB Does not Exist, to be created");
        Log.i(getClass().getSimpleName(), "Get Writable Database being called to create empty DB file");
        getWritableDatabase();
        try {
            Log.i(getClass().getSimpleName(), "Copy Database being called from createDB");
            copyDataBase();
            return;
        } catch (IOException localIOException) {
            throw new Error("Error copying database");
        }
    }

    public void createTempTimetableTable() {
        for (; ; ) {
            try {
                if (Base.trainLine.equals("CR")) {
                    DB_TABLE_STATIONS = DB_TABLE_CRSTATIONS;
                }
                if (Base.trainLine.equals("WR")) {
                    DB_TABLE_STATIONS = DB_TABLE_WRSTATIONS;
                }
                if (Base.trainLine.equals("HR")) {
                    DB_TABLE_STATIONS = DB_TABLE_HRSTATIONS;
                }
                Cursor localCursor1 = this.myDataBase.rawQuery("SELECT * FROM " + DB_TABLE_STATIONS + " WHERE name = " + "\"" + Base.Sourcevaltxt + "\"" + " LIMIT 1", null);
                if (localCursor1 != null) {
                    if (localCursor1.moveToFirst()) {
                        Base.Sourcevalcode = localCursor1.getString(localCursor1.getColumnIndex("code"));
                        sr1 = localCursor1.getInt(localCursor1.getColumnIndex("r1"));
                        sr2 = localCursor1.getInt(localCursor1.getColumnIndex("r2"));
                        sr3 = localCursor1.getInt(localCursor1.getColumnIndex("r3"));
                        sr4 = localCursor1.getInt(localCursor1.getColumnIndex("r4"));
                    }
                    localCursor1.close();
                }
                Cursor localCursor2 = this.myDataBase.rawQuery("SELECT * FROM " + DB_TABLE_STATIONS + " WHERE name = " + "\"" + Base.Destinationvaltxt + "\"" + " LIMIT 1", null);
                if (localCursor2 != null) {
                    if (localCursor2.moveToFirst()) {
                        Base.Destinationvalcode = localCursor2.getString(localCursor2.getColumnIndex("code"));
                        dr1 = localCursor2.getInt(localCursor2.getColumnIndex("r1"));
                        dr2 = localCursor2.getInt(localCursor2.getColumnIndex("r2"));
                        dr3 = localCursor2.getInt(localCursor2.getColumnIndex("r3"));
                        dr4 = localCursor2.getInt(localCursor2.getColumnIndex("r4"));
                    }
                    localCursor2.close();
                }
                Base.updown = "blank";
                if ((sr1 != 0) && (dr1 != 0)) {
                    if (sr1 > dr1) {
                        Base.updown = "U";
                    } else
                        Base.updown = "D";
                } else if ((sr2 != 0) && (dr2 != 0)) {
                    if (sr2 <= dr2) {
                        Base.updown = "D";
                    } else
                        Base.updown = "U";
                } else if ((sr3 != 0) && (dr3 != 0)) {
                    if (sr3 <= dr3) {
                        Base.updown = "D";
                    } else
                        Base.updown = "U";
                } else if ((sr4 != 0) && (dr4 != 0)) {
                    if (sr4 <= dr4) {
                        Base.updown = "D";
                    } else
                        Base.updown = "U";
                }
                if (Base.trainLine.equals("CR")) {
                    if (Base.updown.equals("U")) {
                        DB_TABLE_TIMETABLE = DB_TABLE_CRUPTIMETABLE;
                        DB_TABLE_TRAINS = DB_TABLE_CRUPTRAINS;
                    }
                    if (Base.updown.equals("D")) {
                        DB_TABLE_TIMETABLE = DB_TABLE_CRDOWNTIMETABLE;
                        DB_TABLE_TRAINS = DB_TABLE_CRDOWNTRAINS;
                    }
                }
                if (Base.trainLine.equals("WR")) {
                    if (Base.updown.equals("U")) {
                        DB_TABLE_TIMETABLE = DB_TABLE_WRUPTIMETABLE;
                        DB_TABLE_TRAINS = DB_TABLE_WRUPTRAINS;
                    }
                    if (Base.updown.equals("D")) {
                        DB_TABLE_TIMETABLE = DB_TABLE_WRDOWNTIMETABLE;
                        DB_TABLE_TRAINS = DB_TABLE_WRDOWNTRAINS;
                    }
                }
                if (Base.trainLine.equals("HR")) {
                    if (Base.updown.equals("U")) {
                        DB_TABLE_TIMETABLE = DB_TABLE_HRUPTIMETABLE;
                        DB_TABLE_TRAINS = DB_TABLE_HRUPTRAINS;
                    }
                    if (Base.updown.equals("D")) {
                        DB_TABLE_TIMETABLE = DB_TABLE_HRDOWNTIMETABLE;
                        DB_TABLE_TRAINS = DB_TABLE_HRDOWNTRAINS;
                    }
                }
                Base.time_in_minutes_max = 120 + Base.time_in_minutes;
                this.myDataBase.execSQL("CREATE TEMPORARY TABLE sourcetimetable (_id INTEGER,trainkey TEXT,stkey TEXT,time TEXT,timemin INTEGER)");
                sqlitess = "INSERT INTO sourcetimetable SELECT _id, trainkey, stkey, time, timemin FROM " + DB_TABLE_TIMETABLE + " WHERE stkey = " + "\"" + Base.Sourcevalcode + "\"" + " AND CAST(timemin AS INTEGER) >= " + Base.time_in_minutes + " AND CAST(timemin AS INTEGER) < " + Base.time_in_minutes_max + " ORDER BY CAST(timemin AS INTEGER)";
                this.myDataBase.execSQL(sqlitess);
                if (Base.time_in_minutes_max > 1439) {
                    Base.time_in_minutes_max = -1439 + Base.time_in_minutes_max;
                    sqlitess = "INSERT INTO sourcetimetable SELECT _id, trainkey, stkey, time, timemin FROM " + DB_TABLE_TIMETABLE + " WHERE stkey = " + "\"" + Base.Sourcevalcode + "\"" + " AND CAST(timemin AS INTEGER) < " + Base.time_in_minutes_max + " ORDER BY CAST(timemin AS INTEGER)";
                    this.myDataBase.execSQL(sqlitess);
                }
                Log.i(getClass().getSimpleName(), "Source selection executed");
                this.myDataBase.execSQL("CREATE TEMPORARY TABLE destinationtimetable (_id INTEGER,trainkey TEXT,stkey TEXT,time TEXT,timemin INTEGER)");
                sqliteds = "INSERT INTO destinationtimetable SELECT _id, trainkey, stkey, time, timemin FROM " + DB_TABLE_TIMETABLE + " WHERE stkey = " + "\"" + Base.Destinationvalcode + "\"";
                this.myDataBase.execSQL(sqliteds);
                Log.i(getClass().getSimpleName(), "Destination selection executed");
                return;

            } catch (SQLiteException localSQLiteException) {
                Log.e(getClass().getSimpleName(), "Failed in SQLite Queries");
                return;
            }
        }
    }

    public void deleteTempTimetableTableCloseDB() {
        if (this.myDataBase != null) {
            this.myDataBase.close();
        }
    }

    public String[] getAllStations() {
        if (Base.trainLine.equals("CR")) {
            DB_TABLE_STATIONS = DB_TABLE_CRSTATIONS;
        }
        if (Base.trainLine.equals("WR")) {
            DB_TABLE_STATIONS = DB_TABLE_WRSTATIONS;
        }
        if (Base.trainLine.equals("HR")) {
            DB_TABLE_STATIONS = DB_TABLE_HRSTATIONS;
        }
        Cursor localCursor = this.myDataBase.rawQuery("SELECT * FROM " + DB_TABLE_STATIONS, null);
        if (localCursor.getCount() > 0) {
            String[] arrayOfString = new String[localCursor.getCount()];
            for (int i = 0; ; i++) {
                if (!localCursor.moveToNext()) {
                    if (localCursor != null) {
                        localCursor.close();
                    }
                    return arrayOfString;
                }
                arrayOfString[i] = localCursor.getString(localCursor.getColumnIndex(DB_COLUMN_STATION_NAME));
            }
        }
        if (localCursor != null) {
            localCursor.close();
        }
        return new String[0];
    }

    public ArrayList<HashMap<String, String>> getRouteDetails() {
        if (Base.trainLine.equals("CR")) {
            DB_TABLE_STATIONS = DB_TABLE_CRSTATIONS;
        }
        if (Base.trainLine.equals("WR")) {
            DB_TABLE_STATIONS = DB_TABLE_WRSTATIONS;
        }
        if (Base.trainLine.equals("HR")) {
            DB_TABLE_STATIONS = DB_TABLE_HRSTATIONS;
        }
        ArrayList localArrayList = new ArrayList();
        HashMap localHashMap1 = new HashMap();
        String str1 = "SELECT " + Base.route + ",name,latitude,longitude from " + DB_TABLE_STATIONS + " WHERE " + Base.route + " <> '' ORDER BY CAST(" + Base.route + " AS INTEGER)";
        localHashMap1.put("1", Base.trainLine);
        localHashMap1.put("2", "Route: " + Base.routename);
        localHashMap1.put("3", "");
        localHashMap1.put("4", "");
        localArrayList.add(localHashMap1);
        Cursor localCursor = this.myDataBase.rawQuery(str1, null);
        if (localCursor != null) {
            if (localCursor.moveToFirst()) {
                do {
                    String str2 = localCursor.getString(localCursor.getColumnIndex(Base.route));
                    String str3 = localCursor.getString(localCursor.getColumnIndex("name"));
                    String str4 = localCursor.getString(localCursor.getColumnIndex("latitude"));
                    String str5 = localCursor.getString(localCursor.getColumnIndex("longitude"));
                    HashMap localHashMap2 = new HashMap();
                    localHashMap2.put("1", str2);
                    localHashMap2.put("2", str3);
                    localHashMap2.put("3", str4);
                    localHashMap2.put("4", str5);
                    localArrayList.add(localHashMap2);
                } while (localCursor.moveToNext());
            }
            localCursor.close();
        }
        return localArrayList;
    }

    public List<LocalItem> getTimeTable() {
        if (Base.trainLine.equals("CR")) {
            DB_TABLE_STATIONS = DB_TABLE_CRSTATIONS;
        }
        if (Base.trainLine.equals("WR")) {
            DB_TABLE_STATIONS = DB_TABLE_WRSTATIONS;
        }
        if (Base.trainLine.equals("HR")) {
            DB_TABLE_STATIONS = DB_TABLE_HRSTATIONS;
        }
        List localArrayList = new ArrayList();
        Cursor localCursor1 = this.myDataBase.rawQuery("SELECT * FROM sourcetimetable ", null);
        if (localCursor1 != null) {
            if (!localCursor1.moveToFirst()) {
                Log.i(getClass().getSimpleName(), "No First Source record");
                   return null;
            }
            Log.i(getClass().getSimpleName(), "First Source record called");
            do {
                String str1 = localCursor1.getString(localCursor1.getColumnIndex("trainkey"));
                localCursor1.getInt(localCursor1.getColumnIndex("timemin"));
                seltrain = "SELECT * FROM " + DB_TABLE_TRAINS + " WHERE CAST (trainkey AS TEXT) = " + "\"" + str1 + "\"" + " LIMIT 1";
                Cursor localCursor2 = this.myDataBase.rawQuery(seltrain, null);
                if (localCursor2.moveToFirst()) {
                    cars = localCursor2.getString(localCursor2.getColumnIndex("cars"));
                    speed = localCursor2.getString(localCursor2.getColumnIndex("speed"));
                    start = localCursor2.getString(localCursor2.getColumnIndex("startst"));
                    dest = localCursor2.getString(localCursor2.getColumnIndex("destst"));
                }
                if (localCursor2 != null) {
                    localCursor2.close();
                }
                fullstationcode = "SELECT * FROM "+DB_TABLE_STATIONS+" WHERE code = \"" + start + "\"";
                Cursor localCursorsrc = this.myDataBase.rawQuery(fullstationcode, null);
                if (localCursorsrc.moveToFirst()) {
                    start=localCursorsrc.getString(localCursorsrc.getColumnIndex("name"));
                }
                localCursorsrc.close();
                fullstationcode = "SELECT * FROM "+DB_TABLE_STATIONS+" WHERE code = \"" + dest + "\"";
                Cursor localCursordest = this.myDataBase.rawQuery(fullstationcode, null);
                if (localCursordest.moveToFirst()) {
                    dest=localCursordest.getString(localCursordest.getColumnIndex("name"));
                }
                localCursordest.close();
                destinationtimetablequery = "SELECT * FROM destinationtimetable WHERE trainkey = \"" + str1 + "\"";
                Cursor localCursor3 = this.myDataBase.rawQuery(destinationtimetablequery, null);
                if (localCursor3 != null) {
                    if (localCursor3.moveToFirst()) {

                        localCursor3.getString(localCursor3.getColumnIndex("trainkey"));
                        localCursor3.getInt(localCursor3.getColumnIndex("timemin"));
                        String str2 = localCursor1.getString(localCursor1.getColumnIndex("time"));
                        String str3 = localCursor3.getString(localCursor3.getColumnIndex("time"));
                        fullstationcode = "SELECT * FROM "+DB_TABLE_STATIONS+" WHERE code = \"" + start + "\"";
                        try {
                            localArrayList.add(new LocalItem(str1, f2.format(f1.parse(str2)), f2.format(f1.parse(str3)), start, dest, cars, speed));
                        } catch (ParseException e) {
                            localArrayList.add(new LocalItem(str1, str2,str3, start, dest, cars, speed));
                        }
                        if (localCursor3.moveToNext()) {
                            Log.i(getClass().getSimpleName(), "No First Source record");
                        }
                    }
                    localCursor3.close();
                }
            } while (localCursor1.moveToNext());
            localCursor1.close();
        }
        return localArrayList;
    }

    public ArrayList<HashMap<String, String>> getTrainDetails() {
        ArrayList localArrayList = new ArrayList();
        HashMap localHashMap1 = new HashMap();
        String str1 = "SELECT * FROM " + DB_TABLE_STATIONS + " WHERE CODE = " + "\"" + Base.startstat + "\"";
        Cursor localCursor1 = this.myDataBase.rawQuery(str1, null);
        if (localCursor1 != null) {
            if (localCursor1.moveToFirst()) {
                stname = localCursor1.getString(localCursor1.getColumnIndex("name"));
            }
            localCursor1.close();
        }
        String str2 = "SELECT * FROM " + DB_TABLE_STATIONS + " WHERE CODE = " + "\"" + Base.deststat + "\"";
        Cursor localCursor2 = this.myDataBase.rawQuery(str2, null);
        if (localCursor2 != null) {
            if (localCursor2.moveToFirst()) {
                dename = localCursor2.getString(localCursor2.getColumnIndex("name"));
            }
            localCursor2.close();
        }
        String str3 = stname + " => " + dename + " " + Base.detmes;
        Base.textctrip = str3;
        localHashMap1.put("1", "");
        localHashMap1.put("2", str3);
        localHashMap1.put("3", "");
        localArrayList.add(localHashMap1);
        String str4 = "SELECT " + DB_TABLE_TIMETABLE + ".time, " + DB_TABLE_STATIONS + ".name, " + DB_TABLE_STATIONS + ".latitude, " + DB_TABLE_STATIONS + ".longitude FROM  " + DB_TABLE_TIMETABLE + ", " + DB_TABLE_STATIONS + " WHERE " + DB_TABLE_TIMETABLE + ".trainkey = " + "\"" + Base.trainkeydd + "\"" + " AND " + DB_TABLE_TIMETABLE + ".stkey = " + DB_TABLE_STATIONS + ".code";
        Cursor localCursor3 = this.myDataBase.rawQuery(str4, null);
        if (localCursor3 != null) {
            if (localCursor3.moveToFirst()) {
                do {
                    String str5 = localCursor3.getString(localCursor3.getColumnIndex("time"));
                    String str6 = localCursor3.getString(localCursor3.getColumnIndex("name"));
                    String str7 = localCursor3.getString(localCursor3.getColumnIndex("latitude"));
                    String str8 = localCursor3.getString(localCursor3.getColumnIndex("longitude"));
                    HashMap localHashMap2 = new HashMap();
                    localHashMap2.put("1", str5);
                    localHashMap2.put("2", str6);
                    localHashMap2.put("3", str7);
                    localHashMap2.put("4", str8);
                    localArrayList.add(localHashMap2);
                } while (localCursor3.moveToNext());
            }
            localCursor3.close();
        }
        return localArrayList;
    }


    public void onCreate(SQLiteDatabase paramSQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {
        Log.i(getClass().getSimpleName(), "Inside OnUpgrade");
        try {
            Log.i(DatabaseAdapter.class.getName(), "Upgrading database from version " + paramInt1 + " to " + paramInt2);
            Log.i(getClass().getSimpleName(), "Copy Database Method being called from onUpgrade");
            copyDataBase();
            return;
        } catch (IOException localIOException) {
            throw new Error("Error copying database");
        }
    }

    public void openDataBase() throws SQLException, IOException {
        if (!checkDataBase()) {
            createDataBase();
        }
        Log.i(getClass().getSimpleName(), "Inside openDatabse DB being opened");
        DB_PATH = this.myContext.getDatabasePath(DB_NAME).getPath();
        String str = DB_PATH;
        this.myDataBase = SQLiteDatabase.openDatabase(str, null, 0);
        Log.i(getClass().getSimpleName(), "DB opened at " + str);
    }


    public void searchStationNearby() {
        this.myDataBase.execSQL("CREATE TEMPORARY TABLE nearstation (name,diffsum)");
        String str1 = "SELECT * FROM " + DB_TABLE_STATIONS;
        Cursor localCursor1 = this.myDataBase.rawQuery(str1, null);
        if (localCursor1 != null) {
            if (localCursor1.moveToFirst()) {
                do {
                    String str2 = localCursor1.getString(localCursor1.getColumnIndex("name"));
                    double d = Math.abs(localCursor1.getDouble(localCursor1.getColumnIndex("latitude")) - Base.lastKnownLat) + Math.abs(localCursor1.getDouble(localCursor1.getColumnIndex("longitude")) - Base.lastKnownLon);
                    String str3 = "INSERT into nearstation(name,diffsum) VALUES('" + str2 + "'" + "," + "'" + d + "'" + ")";
                    this.myDataBase.execSQL(str3);
                } while (localCursor1.moveToNext());
            }
            localCursor1.close();
        }
        Cursor localCursor2 = this.myDataBase.rawQuery("SELECT * FROM nearstation ORDER by diffsum", null);
        if (localCursor2.moveToFirst()) {
            Base.nearbystation = localCursor2.getString(localCursor2.getColumnIndex("name"));
        }
        if (localCursor2 != null) {
            localCursor2.close();
        }
    }
}
