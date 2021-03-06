package com.adriencadet.droidagainsthumanity.ui.screens.modal;

import com.adriencadet.droidagainsthumanity.ui.controllers.modal.NicknameModalController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;

/**
 * NicknameModalScreen
 * <p>
 */
@Controller(NicknameModalController.class)
@EnterTransition(UpwardSlideTransition.class)
@ExitTransition(DownwardSlideTransition.class)
public class NicknameModalScreen extends Screen {
}
