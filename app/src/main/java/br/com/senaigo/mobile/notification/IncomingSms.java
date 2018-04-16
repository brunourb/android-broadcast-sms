package br.com.senaigo.mobile.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


public class IncomingSms extends BroadcastReceiver {

	public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		teste();

		if (intent.getAction().equals(ACTION)){
			Bundle bundle = intent.getExtras();
			if (bundle != null){
				Object[] pdus = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[pdus.length];
				for (int i = 0; i < pdus.length; i++){
					messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i],"mensagem");
				}
				for (SmsMessage message : messages){

					String strMessageFrom = message.getDisplayOriginatingAddress(); //numero
					String strMessageBody = message.getDisplayMessageBody(); // mensagem

					Toast.makeText(context, "SMS Message received from:" +strMessageFrom, Toast.LENGTH_LONG).show();
					Toast.makeText(context, "SMS Message content" +strMessageBody, Toast.LENGTH_LONG).show();

				}
			}
		}
	}

	public void teste(){
		String teste = "Faça alguma coisa";
	}
}