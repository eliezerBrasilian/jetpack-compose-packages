package com.frajola.patches.google_sign_in

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

val AppTag = "MyApp"

@Composable
fun App(){
    val clientId = "your_web_client_inside_google_cloud"

    val onClickGoogleSignIn = rememberGoogleSignUp(
        clientId = clientId,
        context = LocalContext.current,
        onSuccess = { result ->
            Log.d(AppTag, "id: ${result.id}" )
            Log.d(AppTag, "email: ${result.email}" )
            Log.d(AppTag, "name: ${result.displayName}" )
        }
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Enter with Google", modifier = Modifier.clickable {
            onClickGoogleSignIn()
        })
    }

}