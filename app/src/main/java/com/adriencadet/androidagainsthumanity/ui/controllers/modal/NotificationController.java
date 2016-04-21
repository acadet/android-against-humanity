package com.adriencadet.androidagainsthumanity.ui.controllers.modal;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.adriencadet.androidagainsthumanity.R;
import com.adriencadet.androidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.ConfirmScreen;
import com.adriencadet.androidagainsthumanity.ui.screens.modal.InitModalScreen;
import com.lyft.scoop.Screen;

import butterknife.Bind;

/**
 * NotificationController
 * <p>
 */
public class NotificationController extends BaseController {
    private Handler hidingHandler;

    @Bind(R.id.notification_content)
    TextView contentView;

    @Override
    protected int layoutId() {
        return R.layout.notification;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        Screen screen = Screen.fromController(this);
        int background;
        String message;

        if (screen instanceof ConfirmScreen) {
            message = ((ConfirmScreen) screen).message;
        } else {
            message = null;
        }

        //TODO: set background
        contentView.setText(message);

        hidingHandler = new Handler(Looper.getMainLooper());
        hidingHandler.postDelayed(
            () -> {
                modalRouter.resetTo(new InitModalScreen());
            },
            3000
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (hidingHandler != null) {
            hidingHandler.removeCallbacksAndMessages(null);
        }
    }
}
