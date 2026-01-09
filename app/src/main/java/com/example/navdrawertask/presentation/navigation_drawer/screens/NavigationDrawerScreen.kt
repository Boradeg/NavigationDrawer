package com.example.navdrawertask.presentation.navigation_drawer.screensimport


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.navdrawertask.R
import com.example.navdrawertask.presentation.navigation_drawer.model.DrawerUserUi
import com.example.navdrawertask.presentation.navigation_drawer.model.MenuItemUi
import com.example.navdrawertask.ui.theme.drawerBackground


@Composable
fun NavigationDrawerScreen(
    user: DrawerUserUi,
    appItems: List<MenuItemUi>,
    helpItems: List<MenuItemUi>,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val displayApps = if (expanded) appItems else appItems.take(4)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(drawerBackground)
            .padding(horizontal = 10.dp),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        item {
            DrawerTopHeader(
                countryLabel = stringResource(R.string.ind_in_en),
                onCountryClick = { },
                onSearchClick = { }
            )
        }
        item {
            Spacer(modifier = Modifier.height(8.dp))
            DrawerHeader(user = user)
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                DrawerItemCard(
                    title = stringResource(R.string.message),
                    icon = R.drawable.ic_msg,
                    modifier = Modifier.weight(1f)
                )
                DrawerItemCard(
                    title = stringResource(R.string.notifications),
                    icon = R.drawable.ic_notification,
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            SectionTitle(stringResource(R.string.apps))
        }

        items(
            items = displayApps.chunked(2),
            key = { row -> row.joinToString("-") { it.title } }) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    DrawerItemCard(
                        title = item.title,
                        icon = item.iconUrl ?: R.drawable.ic_msg,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            SeeMoreButton(
                expanded = expanded,
                onClick = { expanded = !expanded }
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
            SectionTitle(stringResource(R.string.help_more))
        }

        items(
            items = helpItems.chunked(2),
            key = { row -> row.joinToString("-") { it.title } }) { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { item ->
                    DrawerItemCard(
                        title = item.title,
                        icon = item.iconUrl ?: R.drawable.ic_msg,
                        modifier = Modifier.weight(1f)
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            RateUsButton(
                text = stringResource(R.string.rate_us),
                icon = R.drawable.ic_rate,
                onClick = {}
            )
            SignOutButton()
        }
    }
}


@Composable
fun DrawerTopHeader(
    countryLabel: String,
    onCountryClick: () -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.menu_),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        CountrySelectorChip(label = countryLabel, onClick = onCountryClick)
        Spacer(modifier = Modifier.width(8.dp))
        Surface(
            shape = CircleShape,
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            onClick = onSearchClick
        ) {
            Box(modifier = Modifier.size(35.dp), contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun DrawerHeader(user: DrawerUserUi) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = user.profileUrl,
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                placeholder = painterResource(R.drawable.default_profile),
                error = painterResource(R.drawable.default_profile)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = user.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Normal
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(R.string.edit_profile),
                style = MaterialTheme.typography.bodySmall,
                color = Color.Blue,
                modifier = Modifier.clickable { }
            )
        }
    }
}

@Composable
fun DrawerItemCard(
    title: String,
    icon: Any?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = icon ?: R.drawable.ic_msg,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                placeholder = painterResource(R.drawable.ic_msg),
                error = painterResource(R.drawable.ic_msg)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
fun CountrySelectorChip(label: String, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Outlined.Language,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = label, style = MaterialTheme.typography.labelMedium)
            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
        }
    }
}

@Composable
fun SeeMoreButton(expanded: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
    ) {
        Text(if (expanded) stringResource(R.string.see_less) else stringResource(R.string.see_more))
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelLarge,
        color = Color.Gray,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun RateUsButton(
    text: String,
    icon: Int,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
fun SignOutButton() {
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = stringResource(R.string.sign_out), color = MaterialTheme.colorScheme.error)
    }
}