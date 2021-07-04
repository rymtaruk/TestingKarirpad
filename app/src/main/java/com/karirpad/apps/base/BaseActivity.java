package com.karirpad.apps.base;

import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.karirpad.apps.R;
import com.karirpad.apps.di.utils.Injector;
import com.karirpad.apps.di.utils.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created By reynard on 7/3/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private ViewModelProvider viewModelProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Injector.getInstance(this)
                .androidInjector()
                .inject(this);
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.color.White);
    }

    protected synchronized ViewModelProvider getViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(this, viewModelFactory);
        }
        return viewModelProvider;
    }
}
