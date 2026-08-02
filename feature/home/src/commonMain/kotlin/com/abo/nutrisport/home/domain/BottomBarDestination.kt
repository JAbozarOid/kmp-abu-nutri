package com.abo.nutrisport.home.domain

import com.abo.nutrisport.Resources
import com.abo.nutrisport.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

enum class BottomBarDestination(
    val screen: Screen,
    val icon: DrawableResource,
    val title: String
) {
    ProductsOverview(
        screen = Screen.ProductsOverview,
        icon = Resources.Icon.Home,
        title = "Abo Nutri Sport"
    ),
    Cart(
        screen = Screen.Cart,
        icon = Resources.Icon.ShoppingCart,
        title = "Cart"
    ),
    Categories(
        screen = Screen.Categories,
        icon = Resources.Icon.Categories,
        title = "Categories"
    )

}