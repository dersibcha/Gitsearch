package com.example.gitsearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gitsearch.Commons.GlobalVariables;
import com.example.gitsearch.Services.PayPal_Service;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;


public class PaypalActivity extends AppCompatActivity {

    private static PayPalConfiguration config = PayPal_Service.createPayPalConfig();

    Button btnPayNow;
    Button btnCancel;

    String amount = "";

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        //Start Paypal Service
        Intent intent = new Intent(this, PayPalService.class);
        PayPal_Service.setExtra(intent,config);
        startService(intent);

        btnPayNow = (Button) findViewById(R.id.btnPayNow);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });

        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent);
            }

        });

    }

    private void processPayment() {
        Intent intent = new Intent(this, PaymentActivity.class);
        PayPal_Service.processPaymentService(intent,config);
        startActivityForResult(intent, GlobalVariables.PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GlobalVariables.PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = PayPal_Service.getConfirmation(data);
                if (confirmation != null) {
                    try {
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", amount)
                        );
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
    }
}
