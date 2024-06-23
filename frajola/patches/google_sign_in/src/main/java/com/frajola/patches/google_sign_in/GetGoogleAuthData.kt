package com.frajola.patches.google_sign_in

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

fun getGoogleLoginAuth(webClient: String, context:Context): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestIdToken(webClient)
        .requestId()
        .requestProfile()
        .build()
    return GoogleSignIn.getClient(context, gso)
}