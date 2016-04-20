package com.adriencadet.androidagainsthumanity.ui.activities;

import android.app.Activity;
import android.os.Bundle;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;

/**
 * BaseActivity
 * <p>
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
    }
}
