package ranglerz.com.pnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationResult extends AppCompatActivity {

    private TextView txtRegId, txtMessage;
    DBHelper dbHelper;
    DataBase dataBase;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_result);

        init();
        checkingForDatabseRows();
        getMessageFromDB();




    }
    //initialization
    public void init(){
        txtMessage = (TextView)findViewById(R.id.message);
        dataBase = new DataBase(this);
        dbHelper = new DBHelper();
    }

    public void getMessageFromDB(){
        String query = "SELECT * FROM TableMessage WHERE ID="+count;
        SQLiteDatabase dbSelect = dataBase.getReadableDatabase();
        Cursor c = dbSelect.rawQuery(query, null);
        while (c.moveToNext()){
            String message = c.getString(c.getColumnIndex("message"));

            Log.d("NotificationResult: ", "Message To Receive " + message);

            txtMessage.setText(message);

        }
        dbSelect.close();

    }

    public void checkingForDatabseRows(){
        SQLiteDatabase dbCount = dataBase.getReadableDatabase();
        String query = "SELECT * FROM TableMessage";
        try{
            Cursor c = dbCount.rawQuery(query, null);
            int rowNumber = c.getCount();
            count = rowNumber;
            Log.d("NotificationResult: ", "Table Rows Count: " + count);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        dbCount.close();
    }


}