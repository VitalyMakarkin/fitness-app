package com.example.fitness.core.design.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopNavigationBar(
    modifier: Modifier = Modifier,
    title: String = "",
    onBackClick: () -> Unit,
    menuVisibility: Boolean = false,
    onMenuClick: () -> Unit = {}
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
    ) {
        IconButton(onClick = { onBackClick() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            modifier = modifier
        )
        val menuAlpha = if (menuVisibility) 1F else 0F
        IconButton(
            onClick = { onMenuClick() },
            modifier = modifier
                .alpha(menuAlpha)
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Menu"
            )
        }
    }
}
