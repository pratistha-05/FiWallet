package com.nursyah.finance.presentation.screens.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActionButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  text: String? = null,
  icon: ImageVector? = null
) {
  Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .size(width =120.dp, height = 40.dp)
      .border(
        width = 1.dp,
        color = Color.White,
        shape = RoundedCornerShape(20.dp)
      )
      .clickable ( onClick = onClick )

  ) {
    if(icon != null) {
      Icon(
        modifier = modifier
          .size(40.dp)
          .border(width = 1.dp,
            color = Color.White,
            shape = RoundedCornerShape(20.dp)),
        imageVector = icon,
        contentDescription = null,
        tint = Color.White
      )
    }
    if(text != null) {
      Text(
        modifier = Modifier.padding(8.dp),
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
      )
    }

  }
}
