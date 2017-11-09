package mayphoo.mpk.poc_screenimplementation.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import mayphoo.mpk.poc_screenimplementation.R;
import mayphoo.mpk.poc_screenimplementation.fragments.MovieListFragment;

/**
 * Created by User on 11/9/2017.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    @BindView(R.id.fl_movies)
    FrameLayout flMovies;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Movie Shelf");
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("NOW ON CINEMA");

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("UPCOMING");

        TabLayout.Tab thirdTab = tabLayout.newTab();
        thirdTab.setText("MOST POPULAR");

        tabLayout.addTab(firstTab);
        tabLayout.addTab(secondTab);
        tabLayout.addTab(thirdTab);

        if(tabLayout.getSelectedTabPosition() == 0) {
            MovieListFragment movieListFragment = MovieListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_movies, movieListFragment)
                    .commit();

        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0:
                        fragment = MovieListFragment.newInstance();
                        break;
                    case 1:
                        fragment = MovieListFragment.newInstance();
                        break;
                    case 2:
                        fragment = MovieListFragment.newInstance();
                        break;
                    default:
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fl_movies, fragment)
                        .commit();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
