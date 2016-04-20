package com.adriencadet.androidagainsthumanity.ui.activities;

import android.os.Bundle;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.screens.ConversationListScreen;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!mainRouter.hasActiveScreen()) {
            mainRouter.goTo(new ConversationListScreen());
        }
    }
}
