package ar.com.meliandroidchallenge.viewmodel;

import android.app.Application;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ar.com.meliandroidchallenge.service.repository.MeLiRepository;

public class AccessViewModel extends AndroidViewModel {
    private final String TAG = AccessViewModel.class.getSimpleName();

    private final MutableLiveData<Boolean> isUserSessionActive = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isProcessing = new MutableLiveData<>();

    public AccessViewModel(@NonNull Application application) {
        super(application);
        // Instance Database
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "ViewModel Destroyed");
    }

    /**
     * Exposes the MutableLiveData [isUserSessionActive] so the UI can observe it.
     */
    public MutableLiveData<Boolean> getIsUserSessionActive() {
        return isUserSessionActive;
    }

    /**
     * Exposes the MutableLiveData [isProcessing] so the UI can observe it.
     */
    public MutableLiveData<Boolean> getIsProcessing() {
        return isProcessing;
    }

    /**
     * Validates user session in local device
     *
     */
    public void validateUserSession() {
        isProcessing.setValue(true);
        // Do an asynchronous operation to validate user session
        new Handler().postDelayed(() -> {
            Log.d(TAG, "Session validated");
            isProcessing.postValue(false);
            isUserSessionActive.postValue(true);
        }, 3000);
        // TODO: in the next version the validation should be implemented
    }
}
