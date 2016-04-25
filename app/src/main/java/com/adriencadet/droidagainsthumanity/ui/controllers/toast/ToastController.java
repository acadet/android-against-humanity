package com.adriencadet.droidagainsthumanity.ui.controllers.toast;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.screens.toast.AlertScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.toast.ConfirmScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.toast.InformScreen;
import com.lyft.scoop.Screen;

import butterknife.Bind;
import timber.log.Timber;

/**
 * ToastController
 * <p>
 */
public class ToastController extends BaseController {
    private Handler autoHideHandler;

    @Bind(R.id.toast_content)
    TextView contentView;

    private void show(String message, int background) {
        contentView.setText(message);
        contentView.setBackgroundResource(background);

        if (autoHideHandler != null) {
            autoHideHandler.removeCallbacksAndMessages(null);
        }

        autoHideHandler = new Handler(Looper.getMainLooper());
        autoHideHandler.postDelayed(() -> {
            toastRouter.goBack();
        }, 3 * 1000);
    }

    @Override
    protected int layoutId() {
        return R.layout.toast;
    }

    @Override
    public void onAttach() {
        super.onAttach();

        Screen screen = Screen.fromController(this);
        int background;
        String message;

        if (screen instanceof ConfirmScreen) {
            message = ((ConfirmScreen) screen).message;
            background = R.color.confirm;
        } else if (screen instanceof InformScreen) {
            message = ((InformScreen) screen).message;
            background = R.color.inform;
        } else if (screen instanceof AlertScreen) {
            message = ((AlertScreen) screen).message;
            background = R.color.alert;
        } else {
            Timber.e("Unexpected screen");
            return;
        }

        show(message, background);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (autoHideHandler != null) {
            autoHideHandler.removeCallbacksAndMessages(null);
        }
    }
}
