package com.adriencadet.androidagainsthumanity.ui.activities;

import android.os.Bundle;
import android.view.ViewGroup;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.screens.ConversationListScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.InitModalScreen;
import com.lyft.scoop.Scoop;

public class MainActivity extends BaseActivity {
    private Scoop rootScoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootScoop = new Scoop.Builder("root").build();
        rootScoop.inflate(R.layout.root, (ViewGroup) findViewById(R.id.main_layout), true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        
        modalRouter.resetTo(new InitModalScreen());
        if (!mainRouter.hasActiveScreen()) {
            mainRouter.goTo(new ConversationListScreen());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rootScoop.destroy();
    }

    @Override
    public void onBackPressed() {
        if (modalRouter.hasActiveScreen()) {
            modalRouter.goBack();
            return;
        }
        if (!mainRouter.goBack()) {
            modalRouter.resetTo(new InitModalScreen());
            super.onBackPressed();
        }
    }
}
