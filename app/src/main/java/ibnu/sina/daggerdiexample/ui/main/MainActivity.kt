package ibnu.sina.daggerdiexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import ibnu.sina.daggerdiexample.BaseActivity
import ibnu.sina.daggerdiexample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        return super.onOptionsItemSelected(item)
    }

    private fun init(){
        navController = Navigation.findNavController(this, R.id.main_container)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)

        // set burger transition on top left when open and close the drawer layout
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)

        supportFragmentManager.commit
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){

            R.id.nav_profile -> {
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()

                navController.navigate(R.id.profileFragment, null, navOptions)
            }
            R.id.nav_past -> {
                if (isValidDestination(R.id.postFragment)){
                    navController.navigate(R.id.postFragment)
                }

            }
        }

        p0.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)

        return false
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination !=  navController.currentDestination?.id
    }

    override fun onSupportNavigateUp(): Boolean {
        // set the respon back button on Toolbar
        return NavigationUI.navigateUp(navController, drawer_layout)
    }
}
