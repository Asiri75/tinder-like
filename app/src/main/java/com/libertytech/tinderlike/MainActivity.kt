package com.libertytech.tinderlike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.libertytech.tinderlike.ui.theme.TinderLikeTheme
import com.libertytech.tinderlike.usecases.GetPartenersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TinderLikeTheme {

    }
}
}

