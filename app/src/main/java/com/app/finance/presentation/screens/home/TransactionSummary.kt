package com.app.finance.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nursyah.finance.R
import com.nursyah.finance.core.Utils
import com.nursyah.finance.presentation.components.MainViewModel
import com.nursyah.finance.presentation.theme.robotoFont

@Composable
fun TransactionSummary(
  viewModel: MainViewModel = hiltViewModel()
) {
  val totalData by viewModel.getDataToday().collectAsState(initial = emptyList())
  val spendingTotal = Utils.totalDataString(totalData, "Spending")
  val incomeTotal = Utils.totalDataString(totalData, "Income")

  Card(
    modifier = Modifier
      .height(500.dp)
      .fillMaxWidth(), shape = RoundedCornerShape(
      topStart = 30.dp, topEnd = 30.dp, bottomStart = 0.dp, bottomEnd = 0.dp
    ), colors = CardDefaults.cardColors(
      containerColor = Color.Transparent
    )
  ) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(
          brush = Brush.linearGradient(
            colors = listOf(
              MaterialTheme.colorScheme.onSecondary, MaterialTheme.colorScheme.scrim
            )
          )
        )
    ) {
      Column(modifier = Modifier.padding(20.dp)) {
        CalendarDateBox()
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Recent Transactions",
          fontSize = MaterialTheme.typography.titleLarge.fontSize,
          fontFamily = robotoFont
        )
        Text(text = "${stringResource(R.string.spending)}: $spendingTotal")
        Text(text = "${stringResource(R.string.income)}: $incomeTotal")

      }
    }
  }
}


@Composable
fun CalendarDateBox(){
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .border(1.dp, Color.White, RoundedCornerShape(20.dp))
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      IconButton(
        onClick = { /* Handle Previous action */ }
      ) {
        Icon(
          imageVector = Icons.Default.KeyboardArrowLeft,
          contentDescription = null,
          tint = Color.White
        )
      }
      Text(
        text = stringResource(R.string.today)
      )
      IconButton(
        onClick = { /* Handle Previous action */ }
      ) {
        Icon(
          imageVector = Icons.Default.KeyboardArrowRight,
          contentDescription = null,
          tint = Color.White
        )
      }
    }
  }
}




//            Column(modifier = Modifier.padding(20.dp)) {
//                Text(text = stringResource(R.string.today))
//                Spacer(modifier = Modifier.height(10.dp))
//                Text(text = "${stringResource(R.string.spending)}: $spendingTotal")
//                Text(text = "${stringResource(R.string.income)}: $incomeTotal")
//            }