package com.example.com.cs478proj3a1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class BroadcastSender extends AppCompatActivity {
    private static final String INTENT_ACTION = "edu.uic.cs478.project3.sportsBroadcast";
    private static final String TOAST_BASKETBALL = "edu.uic.cs478.project3.showToastBasketball";
    private static final String TOAST_BASEBALL = "edu.uic.cs478.project3.showToastBaseball";
    private static final String APP_PERMISSION = "edu.uic.cs478.project3";
    private static final int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_sender);
    }//end onCreate(...)

    @Override
    public void onResume() {
        super.onResume();
        //request permission when app is launched and if permission is not yet granted
        if (ContextCompat.checkSelfPermission(this, APP_PERMISSION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{APP_PERMISSION}, 0);
    }//end onResume()

    //method gets called after user chooses whether to grant application permission or not
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Application won't work without permission, my friend", Toast.LENGTH_LONG).show();
        }
    }//end onRequestPermissionsResult(...)

    //handles the click event for basketball button to send broadcast
    public void sendBasketballBroadcast(View view) {
        if(checkPermissions())
            broadcastOut(INTENT_ACTION, TOAST_BASKETBALL);
        else
            //ActivityCompat.requestPermissions(this, new String[]{APP_PERMISSION}, PERMISSION_REQUEST_CODE);
            Toast.makeText(this, "You denied permission, restart app to re-request permission", Toast.LENGTH_SHORT).show();
    }//end sendBasketballBroadcast(...)

    //handles the click event for baseball button to send broadcast
    public void sendBaseballBroadcast(View view) {
        if(checkPermissions())
            broadcastOut(INTENT_ACTION, TOAST_BASEBALL);
        else
            //ActivityCompat.requestPermissions(this, new String[]{APP_PERMISSION}, PERMISSION_REQUEST_CODE);
            Toast.makeText(this, "You denied permission, restart app to re-request permission", Toast.LENGTH_SHORT).show();
    }//end sendBaseballBroadcast(...)

    //checks to see if application has been granted the correct permission
    public boolean checkPermissions(){
        return (ContextCompat.checkSelfPermission(this, APP_PERMISSION) == PackageManager.PERMISSION_GRANTED);
    }//end checkPermissions()

    //sends out an ordered broadcast with a specified action and category
    public void broadcastOut(String action, String category){
        Intent i = new Intent(action);
        i.addCategory(category);
        i.setFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendOrderedBroadcast(i, null);
    }//end broadcastOut(...)
}//end BroadcastSender class