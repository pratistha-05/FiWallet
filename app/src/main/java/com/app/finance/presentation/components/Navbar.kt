package com.nursyah.finance.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nursyah.finance.R
import com.nursyah.finance.navigation.MainDestination

enum class ItemNav(
    val itemName: Int,
    val itemIcon: Int,
    val itemRoute: String
) {
    HOME(
        itemName = R.string.home,
        itemIcon = R.drawable.ic_dashboard,
        itemRoute = MainDestination.Home.route
    ),
    STATS(
        itemName = R.string.stats,
        itemIcon = R.drawable.ic_stats,
        itemRoute = MainDestination.Stats.route
    ),
    SETTINGS(
        itemName = R.string.settings,
        itemIcon = R.drawable.ic_close,
        itemRoute = MainDestination.SettingsRoute.Settings.route
    ),
    ACCOUNT(
        itemName = R.string.settings,
        itemIcon = R.drawable.ic_close,
        itemRoute = MainDestination.SettingsRoute.Settings.route
    ),


}

@Composable
fun Navbar(
    onClick: (String) -> Unit,
    value: String,
    navController: NavHostController,
) {
    val ctx = LocalContext.current
    NavigationBar {
        ItemNav.values().forEach {
            val itemName = ctx.getString(it.itemName)
            NavigationBarItem(
                selected = itemName == value,
                onClick = {
                    onClick(itemName)
                    if (navController.currentDestination?.route != it.itemRoute) {
                        navController.popBackStack()
                        navController.navigate(it.itemRoute)
                    }
                },
                label = { Text(text = itemName) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.itemIcon),
                        contentDescription = null,
                        tint = Color.White,
                    )
                }
            )
        }
    }
}