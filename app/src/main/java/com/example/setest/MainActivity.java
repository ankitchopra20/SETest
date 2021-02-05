package com.example.setest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.singular.sdk.*;
import com.singular.sdk.Singular;
import com.singular.sdk.SingularConfig;
import com.singular.sdk.SingularLinkHandler;
import com.singular.sdk.SingularLinkParams;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initiateSingularSDK();

    }

    private void initiateSingularSDK() {
        SingularConfig config = new SingularConfig("realprodcorp1", "d38bfbce70b42a70fe920f425e73d123")
                .withCustomUserId("Ankit")
                .withSessionTimeoutInSec(120);
        config.withSingularLink(getIntent(), new SingularLinkHandler() {
            @Override
            public void onResolved(SingularLinkParams params) {
                // The deep link destination, as configured in the Manage Links page
                String deeplink = params.getDeeplink();
                Singular.event("isFromDeepLink");
                System.out.println(deeplink.toString());

                // The passthrough parameters added to the link, if any.
                String passthrough = params.getPassthrough();

                // Whether the link configured as a deferred deep link.
                boolean isDeferred = params.isDeferred();

                // Add deep link handling code here
            }
        });
        Singular.init(this, config);
    }

    public void sendMessage(View view) {
        Singular.event("BtnClick", "AnkitChopra");
    }
}