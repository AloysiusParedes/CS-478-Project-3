package com.example.com.cs478proj3a3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SportsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        for(String s : intent.getCategories()){
            if(s.equals("edu.uic.cs478.project3.showToastBasketball")){
                Intent i = new Intent(context, BasketballActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
            else if(s.equals("edu.uic.cs478.project3.showToastBaseball")) {
                Intent i = new Intent(context, BaseballActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }//end onReceive(...)
}//end SportsReceiver
