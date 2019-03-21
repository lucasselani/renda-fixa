package com.lambreta.rendafixa.core.view.activity

import androidx.annotation.IdRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

abstract class BaseNavigationActivity : BaseAdMobActivity() {

    private var navHostFragmentId: Int = -1
    private var drawerLayoutId: Int = -1
    private val drawerLayout: DrawerLayout by lazy {
        findViewById<DrawerLayout>(drawerLayoutId)
    }

    override fun onSupportNavigateUp(): Boolean =
        if(isNavHostSet()) {
            NavigationUI.navigateUp(Navigation.findNavController(this, navHostFragmentId), drawerLayout)
        } else {
            super.onSupportNavigateUp()
        }

    override fun onBackPressed() =
        if (isNavHostSet() && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }

    protected fun setupNavigation(@IdRes navHostFragmentId: Int,
                                  @IdRes drawerLayoutId: Int,
                                  navigationView: NavigationView) {
        this.navHostFragmentId = navHostFragmentId
        this.drawerLayoutId = drawerLayoutId

        val navController = Navigation.findNavController(this, navHostFragmentId)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            true
        }
        NavigationUI.setupWithNavController(navigationView, navController)
    }

    protected fun navigate(navDirections: NavDirections) =
        findNavController(android.R.id.content).navigate(navDirections)

    private fun isNavHostSet() = navHostFragmentId != -1 && drawerLayoutId != -1
}
