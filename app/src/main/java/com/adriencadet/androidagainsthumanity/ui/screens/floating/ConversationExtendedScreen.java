package com.adriencadet.androidagainsthumanity.ui.screens.floating;

import com.adriencadet.androidagainsthumanity.ui.controllers.floating.ConversationExtendedController;
import com.adriencadet.androidagainsthumanity.ui.transitions.AutoTransition;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;

/**
 * ConversationExtendedScreen
 * <p>
 */
@Controller(ConversationExtendedController.class)
@EnterTransition(AutoTransition.class)
@ExitTransition(AutoTransition.class)
public class ConversationExtendedScreen extends Screen {
}
