package com.chikeandroid.tutsplust_telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * Created by Chike on 2/4/2017.
 */

public class PhoneCallStateReceiver extends BroadcastReceiver {

    private TelephonyManager mTelephonyManager;
    public static boolean isListening = false;

    @Override
    public void onReceive(final Context context, Intent intent) {

        // for outgoing call
        String outgoingPhoneNo = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER).toString();
        // prevent outgoing call
        setResultData(null);

        Toast.makeText(context, "ON_RECEIVE", Toast.LENGTH_SHORT).show();

        mTelephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

        PhoneStateListener mPhoneStateListener = new PhoneStateListener() {

            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                super.onCallStateChanged(state, incomingNumber);

                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE:
                        Toast.makeText(context, "CALL_STATE_IDLE1", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_RINGING:
                        Toast.makeText(context, "CALL_STATE_RINGING", Toast.LENGTH_SHORT).show();
                        break;
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                        Toast.makeText(context, "CALL_STATE_OFFHOOK", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        if(!isListening) {
            mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
            isListening = true;
        }
    }
}
