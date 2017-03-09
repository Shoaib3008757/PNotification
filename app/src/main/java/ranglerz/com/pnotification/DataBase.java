package ranglerz.com.pnotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User-10 on 21-Dec-16.
 */
public class DataBase extends SQLiteOpenHelper {

    Context mContext;
    private static final int Database_Version = 1;
    public static final String DATABASE_NAME = "message.db";
    private static final String TABLE_NAME = "TableMessage";
    private static final String Col_1 = "ID";
    private static final String Col_2 = "message";
    private static final String Col_3 = "image";

    String CREATE_TABLE_CALL = "CREATE TABLE " + TABLE_NAME + "("
            + Col_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Col_2 + " TEXT, "
            + Col_3 + " BLOB, "
            + "TAG TEXT" + ")";

    //constructor
    DataBase(Context context){
        super(context, DATABASE_NAME, null,  Database_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CALL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    //inserting values in database
    public long insertValuesInTable(DBHelper dbHelper){
        long c;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, dbHelper.getMessage());
        //inserting values in table
        c = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return c;

    }
}
