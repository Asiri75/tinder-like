package com.libertytech.tinderlike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.GetPartenairesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val getPartenaires = GetPartenairesUseCase()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.IO) {
            getPartenaires.invoke()
        }

    }





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TinderLikeTheme {

    }
}
}

