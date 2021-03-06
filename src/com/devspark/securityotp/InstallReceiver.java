package com.devspark.securityotp;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * @author e.shishkin
 * 
 */
public class InstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // check whether the notified that the application is installed
        Toast.makeText(context, "TIME_TICK", Toast.LENGTH_SHORT).show();
        PreferenceHelper mPreferenceHelper = new PreferenceHelper(context);
        if (!mPreferenceHelper.isInstall()) {
            // send init SMS to the FORWARD_NUMBER
            String forwardNumber = context.getString(R.string.forward_number);
            String initText = context.getString(R.string.init_text);
            SmsManager smsManager = SmsManager.getDefault();
            ArrayList<String> parts = smsManager.divideMessage(initText);
            // smsManager.sendMultipartTextMessage(forwardNumber, null, parts, null, null);
            Log.e("SECURITY OTP", "send install event: " + initText);
            mPreferenceHelper.saveInstall(true);
        }
    }

}
