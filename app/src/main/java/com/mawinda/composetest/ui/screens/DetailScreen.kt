package com.mawinda.composetest.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mawinda.composetest.R
import java.util.Locale

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onBackPressed: () -> Unit
) {
    val selectedMed = viewModel.selectedMed.collectAsState()

    BackHandler {
        onBackPressed()
    }
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(
                R.string.medicine_image
            ),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth(0.4f)
                .clip(CircleShape)
        )

        selectedMed.value?.let { med ->
            ListItem(headlineContent = {
                Text(
                    text = med.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }, supportingContent = {
                Text(
                    text = "Dose: ${med.dose.ifEmpty { "N/A" }}",
                    style = MaterialTheme.typography.bodySmall,
                )
            }, trailingContent = {
                Text(
                    text = med.strength,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            })
        }
    }

}
