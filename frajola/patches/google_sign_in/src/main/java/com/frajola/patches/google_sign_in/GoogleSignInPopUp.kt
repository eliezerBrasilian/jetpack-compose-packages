package com.frajola.patches.google_sign_in

import android.app.Activity
import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

@Composable
fun GoogleSignInPopUp(
    clientId: String,
    context: Context,
    onSuccess: (result: GoogleSignInAccount) -> Unit
) {
    val googleSignInClient = getGoogleLoginAuth(clientId, context)
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent = activityResult.data
                if (intent != null) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
                    val result = task.result
                    if (result != null) {
                        onSuccess(result)
                    }
                }
            }
        }
    LaunchedEffect(Unit) {
        startForResult.launch(googleSignInClient.signInIntent)
    }
}