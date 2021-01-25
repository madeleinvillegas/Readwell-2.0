package ph.edu.dlsu.readwell20;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Stack;

import ph.edu.dlsu.readwell20.ui.search.SearchFragment;

public class MainActivity extends AppCompatActivity {
    public static int lastTab = 0;
    public static Stack<Book> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_cart)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        NavGraph graph = navController.getNavInflater().inflate(R.navigation.mobile_navigation);
        if (lastTab == 0) graph.setStartDestination(R.id.navigation_home);
        else if (lastTab == 1) graph.setStartDestination(R.id.navigation_search);
        else if (lastTab == 2) graph.setStartDestination(R.id.navigation_cart);
        navController.setGraph(graph);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Button is Disabled", Toast.LENGTH_SHORT).show();
    }
}