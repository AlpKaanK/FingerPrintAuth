package com.camo.finger;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{



    private Context context;

    public FingerprintHandler(Context context){


        this.context = context;

    }
    public void start(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){

         // Crypto for letting scanner know added ones

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0, this, null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("Error"+ errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fail", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("part3" +helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Access Granted ", true);


    }
    private void update (String s, boolean b){
        ImageView imageView = (ImageView)((Activity)context).findViewById(R.id.fingerprintImage);
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.parag);

        textView.setText(s);

        if(b== false){
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));


        } else{
            textView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.ic_launcher2);


        }

    }

}
