package com.frajola.patches.google_sign_in

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

@Composable
fun rememberGoogleSignUp(
    clientId: String,
    context: Context,
    onSuccess: (result: GoogleSignInAccount) -> Unit,
    onError: () -> Unit
): () -> Unit {
    var showGoogleSignInPopUp by remember { mutableStateOf(false) }

    if (showGoogleSignInPopUp) {
        GoogleSignInPopUp(
            clientId = clientId,
            context = context,
            onSuccess = { result ->
                onSuccess(result)
                showGoogleSignInPopUp = false
            },
            onError = onError
        )
    }

    // Return a lambda to trigger the Google Sign-In process
    return {
        showGoogleSignInPopUp = true
    }
}