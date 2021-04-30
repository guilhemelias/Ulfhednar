package iut.cours.lifehealth.model.intent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import iut.cours.lifehealth.model.Manager;

public class MyReceiver extends BroadcastReceiver {
    Manager manager;
    public MyReceiver(Manager manager){
        this.manager= manager;
    }

    @Override
    public void onReceive(Context arg0, Intent arg1){
        String test = arg1.getAction().toString();
        if(arg1.getAction().toString()=="iut.cours.lifeHealth.Pedometer.MY_ACTION"){
                manager.getProfile().getSteps().add1Step();


        }
    }
}
