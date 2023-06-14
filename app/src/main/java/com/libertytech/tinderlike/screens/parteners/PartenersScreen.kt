package com.libertytech.tinderlike.screens.parteners
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun PartenairesScreen(viewModel: PartenairesViewModel = viewModel()) {
    val partenaires = viewModel.partenairesState.collectAsState(initial = emptyList()).value

    LaunchedEffect(Unit) {
        viewModel.fetchPartenaires()
    }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (partenaires.isNotEmpty()) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Partenaires",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(partenaires) { partenaire ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = 4.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = partenaire.name,
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = partenaire.description,
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        }
                    }
                }
            }
        } else {

            CircularProgressIndicator()
        }
    }
}
