package com.example.gitsearch.Services;

import android.content.Intent;


import com.example.gitsearch.Commons.GlobalVariables;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;

public class PayPal_Service {

    public static PayPalConfiguration createPayPalConfig() {
        return new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(GlobalVariables.PAYPAL_CLIENT_ID);
    }

    public static void setExtra(Intent i , PayPalConfiguration pc ) {
        i.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, pc);
    }

    public static void processPaymentService(Intent intent, PayPalConfiguration config) {
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(GlobalVariables.Payment_Price)), "USD",
                "Premium payment", PayPalPayment.PAYMENT_INTENT_SALE);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
    }

    public static PaymentConfirmation getConfirmation(Intent data) {
        return  data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
    }
}
