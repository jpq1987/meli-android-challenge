package ar.com.meliandroidchallenge.view.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import ar.com.meliandroidchallenge.R;
import ar.com.meliandroidchallenge.service.model.Item;
import ar.com.meliandroidchallenge.view.itemDetail.ItemDetailActivity;
import ar.com.meliandroidchallenge.viewmodel.SearchViewModel;

public class MainActivity extends AppCompatActivity implements ItemResultAdapter.OnItemResultRecyclerListener {
    // tag used for debugging
    private static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout drawerLayout;
    private SearchViewModel searchViewModel;
    private TextView welcomeTextView;
    private TextView errorTextView;
    private ProgressBar processingResultsProgressBar;
    private RecyclerView itemsRecyclerView;
    private ItemResultAdapter itemResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        renderUI();

        searchViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        observeViewModel(searchViewModel);
    }

    /**
     * Observes changes related with the {@link SearchView} and updates UI
     *
     * @param searchViewModel {@link SearchViewModel} instance
     */
    private void observeViewModel(@NonNull SearchViewModel searchViewModel) {
        // Observes changes in items and updates the RecyclerView
        searchViewModel.getItemsLiveData().observe(this, items -> {
            if (items != null) {
                Log.d(TAG, "items loaded "+items.size());
                itemResultAdapter.setResults(items);
            } else {
                itemResultAdapter.setResults(null);
                displayWelcomeUI(true);
            }
        });

        // Observes
        searchViewModel.getIsProcessing().observe(this, isProcessing -> {
            displayProcessingUI(isProcessing);
        });
    }

    private void displayProcessingUI(Boolean isProcessing) {
        if (isProcessing) {
            processingResultsProgressBar.setVisibility(View.VISIBLE);
        } else {
            processingResultsProgressBar.setVisibility(View.GONE);
            if (itemResultAdapter.getItemCount() > 0) {
                itemsRecyclerView.setVisibility(View.VISIBLE);
            } else{
                itemsRecyclerView.setVisibility(View.GONE);
                displayErrorUI(true);
            }
        }
    }


    /**
     * Displays the welcome message
     *
     */
    private void displayWelcomeUI(boolean display) {
        if (display) {
            welcomeTextView.setVisibility(View.VISIBLE);
        } else {
            welcomeTextView.setVisibility(View.GONE);
        }
    }

    /**
     * Displays the error message
     *
     */
    private void displayErrorUI(boolean display) {
        if (display) {
            errorTextView.setVisibility(View.VISIBLE);
        } else{
            errorTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);

        // Sets up SearchView
        final SearchView searchView = (SearchView) menu.findItem(R.id.search_product_view).getActionView();
        setupSearchView(searchView);

        return true;
    }

    /**
     * Sets up the UI
     */
    private void renderUI() {
        // Sets up the Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        // Sets up the navigation drawer
        drawerLayout = findViewById(R.id.drawer_home_layout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_home_view);
        navigationView.bringToFront();
        setupDrawerContent(navigationView);

        // Sets up the RecyclerView
        itemsRecyclerView = findViewById(R.id.recyclerview_item_results);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemResultAdapter = new ItemResultAdapter(this);
        itemsRecyclerView.setAdapter(itemResultAdapter);

        // Sets up the welcome TextView
        welcomeTextView = findViewById(R.id.textview_main_welcome);

        // Sets up the error TextView
        errorTextView = findViewById(R.id.textview_main_error);

        // Sets up the results ImageView
        processingResultsProgressBar = findViewById(R.id.progressbar_main_processing);
    }

    /**
     * Gets data from search view and query the MeLi api to get results
     *
     * @param searchView The {@link SearchView} to set up
     */
    private void setupSearchView(SearchView searchView) {
        ImageView clearButton = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        clearButton.setOnClickListener(v -> {
            // Cleans RecyclerView when clear the SearchView
            searchView.setQuery("",false);
            searchViewModel.cleanItemsResult();
            displayErrorUI(false);
        });
        searchView.setIconifiedByDefault(true);
        searchView.setIconified(false);
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                displayWelcomeUI(false);
                displayErrorUI(false);
                searchView.clearFocus();
                searchViewModel.getItemsByQuery(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO: suggestions list not yet implemented
                return false;
            }

        });
        searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewDetachedFromWindow(View arg0) {
                // Cleans RecyclerView when close the SearchView
                searchViewModel.cleanItemsResult();
                displayErrorUI(false);
            }

            @Override
            public void onViewAttachedToWindow(View arg0) {
            }
        });
    }

    /**
     * Sets up the DrawerLayout
     *
     * @param navigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                item -> {
                    // Handle navigation view item clicks here.
                    switch (item.getItemId()) {
                        default:
                            Toast.makeText(this,"Próximamente vas a poder acceder a esta funcionalidad",Toast.LENGTH_LONG).show();
                            break;
                    }
                    drawerLayout.closeDrawers();
                    return true;
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_home_layout);
        // Closes the DrawerLayout if it is open
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Loads more {@link Item} related with the last query
     *
     * @param position The last position of the RecyclerView
     */
    @Override
    public void onBottomReached(int position) {
        searchViewModel.getLoadMoreItems(position);
    }

    /**
     * Redirects to the {@link ItemDetailActivity} activity to view {@link Item} details
     *
     * @param position The position of the RecyclerView tapped
     */
    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(this, ItemDetailActivity.class);
        intent.putExtra("item", itemResultAdapter.getResult(position));
        startActivity(intent);
    }
}