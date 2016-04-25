package com.adriencadet.droidagainsthumanity.ui.screens.modal;

import com.adriencadet.droidagainsthumanity.ui.controllers.modal.JoinConversationModalController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.DownwardSlideTransition;
import com.lyft.scoop.transitions.UpwardSlideTransition;

/**
 * JoinConversationModalScreen
 * <p>
 */
@Controller(JoinConversationModalController.class)
@EnterTransition(UpwardSlideTransition.class)
@ExitTransition(DownwardSlideTransition.class)
public class JoinConversationModalScreen extends Screen {
}
