package com.kpiega.cojemy.ui.main

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.kpiega.cojemy.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("LinkTest", "Firebase start link !!!!")

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(intent)
                .addOnSuccessListener(this, { pendingDynamicLinkData ->
                    if (pendingDynamicLinkData != null) {

                        Log.i("LinkTest", "addOnSuccessListener")

                        val deepLink: Uri? = pendingDynamicLinkData.link
                        val minimumVersion = pendingDynamicLinkData.minimumAppVersion
                        val intent = pendingDynamicLinkData.getUpdateAppIntent(this)

                        Log.i("LinkTest", "deepLink: ${deepLink ?: "deeplink is null ;("}")
                        Log.i("LinkTest", "minimumVersion: ${minimumVersion ?: "minimumVersion is null ;("}")
                        Log.i("LinkTest", "intent: ${intent?.toString() ?: "intent is null ;("}")

                    }
                })
                .addOnFailureListener(this, { e ->
                    Log.i("LinkTest", "addOnFailureListener")
                    Log.i("LinkTest", "${e.message ?: "Error listener!"}")
                })
                .addOnCompleteListener(this, { pendingDynamicLinkData ->

                    Log.i("LinkTest", "addOnCompleteListener ")

                    if (pendingDynamicLinkData != null) {
                        val result = pendingDynamicLinkData.result

                        var deepLink: Uri?  = null
                        if(result != null)
                            deepLink = result.link

                        val statusComplete = pendingDynamicLinkData.isComplete
                        val statusSuccess = pendingDynamicLinkData.isSuccessful
                        val minimumVersion = pendingDynamicLinkData.exception

                        Log.i("LinkTest", "deepLink: ${deepLink ?: "deeplink is null ;("}")
                        Log.i("LinkTest", "statusComplete: ${statusComplete ?: "statusComplete is null ;("}")
                        Log.i("LinkTest", "statusSuccess: ${statusSuccess ?: "statusSuccess is null ;("}")
                        Log.i("LinkTest", "minimumVersion: ${minimumVersion ?: "minimumVersion is null ;("}")

                    }

                })
    }

}
