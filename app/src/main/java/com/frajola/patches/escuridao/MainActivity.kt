package com.frajola.patches.escuridao

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frajola.BrazilianCurrencyVisualTransformation
import com.frajola.patches.escuridao.ui.theme.FrajolaPatchesTheme
import com.frajola.patches.google_sign_in.rememberGoogleSignUp
import com.frajola.patches.jetpack_compose_fresh_ads.loadInterstitial
import com.frajola.patches.jetpack_compose_fresh_ads.showInterstitial

val AppTag = "MyApp"
val interstitialId = "ca-app-pub-3940256099942544/1033173712"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
            loadInterstitial(this,interstitialId)

        super.onCreate(savedInstanceState)
        setContent {
            FrajolaPatchesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var valueInput by remember{
                        mutableStateOf("")
                    }

                    val onValueInputChange:(value:String)->Unit = { it ->
                        valueInput = it.filter { it.isDigit() }
                    }

                    val clientId = "your_web_client_inside_google_cloud"

                    val context = LocalContext.current

                    val onClickGoogleSignIn = rememberGoogleSignUp(
                        clientId = clientId,
                        context = LocalContext.current,
                        onSuccess = { result ->
                            Log.d(AppTag, "id: ${result.id}" )
                            Log.d(AppTag, "email: ${result.email}" )
                            Log.d(AppTag, "name: ${result.displayName}" )
                        },
                        onError = {
                            Toast.makeText(context, "Falha ao logar com Google ❌", Toast.LENGTH_SHORT).show()
                        }
                    )

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)){
                            Button(onClick = onClickGoogleSignIn) {
                                Text(text = "Enter with Google")
                            }

                            Button(onClick = {
                                showInterstitial(context, interstitialId)
                            }) {
                                Text(text = "Load Ads")
                            }
                        }
                    }


                }
            }
        }
    }
}


