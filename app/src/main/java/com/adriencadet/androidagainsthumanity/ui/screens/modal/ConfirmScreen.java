package com.adriencadet.androidagainsthumanity.ui.screens.modal;

import com.adriencadet.androidagainsthumanity.ui.controllers.modal.NotificationController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;

/**
 * ConfirmScreen
 * <p>
 */
@Controller(NotificationController.class)
@EnterTransition(DownwardSlideTransition.class)
@ExitTransition(UpwardSlideTransition.class)
public class ConfirmScreen extends Screen {
    public String message;

    public ConfirmScreen(String message) {
        this.message = message;
    }
}
