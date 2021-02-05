package ar.com.meliandroidchallenge.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import ar.com.meliandroidchallenge.R;
import ar.com.meliandroidchallenge.view.home.MainActivity;
import ar.com.meliandroidchallenge.viewmodel.AccessViewModel;

import static ar.com.meliandroidchallenge.utils.Constants.USER_LOGGED_NAME;

public class SplashActivity extends AppCompatActivity {
    // Tag used for debugging
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final AccessViewModel accessViewModel = new ViewModelProvider(this).get(AccessViewModel.class);
        observeViewModel(accessViewModel);
    }

    /**
     * Checks if there is an active user session and observes the result.
     * Depending on the result, redirects to the corresponding activity
     *
     * @param accessViewModel {@link AccessViewModel} instance
     */
    private void observeViewModel(@NonNull AccessViewModel accessViewModel) {
        accessViewModel.validateUserSession();
        accessViewModel.getIsUserSessionActive().observe(this, user -> {
            accessViewModel.getIsUserSessionActive().removeObservers(SplashActivity.this);
            if (user != null && user) {
                //TODO: in the next version the String should be replaced by an User object
                redirectToHome("Juan");
            } else {
                redirectToAccess();
            }
        });
    }

    /**
     * Starts the {@link MainActivity} activity
     *
     * @param userName The name of the user logged
     */
    private void redirectToHome(String userName) {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        //TODO: in the next version the userName should be replaced by an User object
        intent.putExtra(USER_LOGGED_NAME, userName);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    /**
     * Starts the {AccessActivity} activity to signin or signup
     *
     */
    private void redirectToAccess() {
        //TODO: AccessActivity isn't yet implemented; instead, a Toast is displayed
        Toast.makeText(this, "Hola! Es tu primer acceso a la app, continuaremos con el login", Toast.LENGTH_LONG).show();
    }
}