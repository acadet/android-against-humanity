package com.adriencadet.androidagainsthumanity.ui.screens.main;

import com.adriencadet.androidagainsthumanity.ui.controllers.main.MainController;
import com.lyft.scoop.Controller;
import com.lyft.scoop.EnterTransition;
import com.lyft.scoop.ExitTransition;
import com.lyft.scoop.Screen;
import com.lyft.scoop.transitions.BackwardSlideTransition;
import com.lyft.scoop.transitions.ForwardSlideTransition;

/**
 * DetailsScreen
 * <p>
 */
@Controller(MainController.class)
@EnterTransition(ForwardSlideTransition.class)
@ExitTransition(BackwardSlideTransition.class)
public class DetailsScreen extends Screen {
}
