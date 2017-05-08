package com.example.com.cs478proj3a2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ToastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        for(String s : intent.getCategories()){
            if(s.equals("edu.uic.cs478.project3.showToastBasketball"))
                Toast.makeText(context, "A2: National Basketball Association (NBA) Selected", Toast.LENGTH_LONG).show();
            else if(s.equals("edu.uic.cs478.project3.showToastBaseball"))
                Toast.makeText(context, "A2: Major League Baseball (MLB) Selected", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(context, "A2 Received Broadcast", Toast.LENGTH_LONG).show();
    }//end onReceive(...)
}//end ToastReceiver(...)
