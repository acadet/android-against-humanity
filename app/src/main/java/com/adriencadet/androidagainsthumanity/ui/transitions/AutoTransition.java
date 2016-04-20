package com.adriencadet.androidagainsthumanity.ui.transitions;

import android.transition.Scene;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.TransitionListener;

/**
 * AutoTransition
 * <p>
 */
public class AutoTransition implements ScreenTransition {
    @Override
    public void transition(ViewGroup root, View from, View to, TransitionListener transitionListener) {
        Scene toScene = new Scene(root, to);

        android.transition.AutoTransition transition = new android.transition.AutoTransition();

        TransitionManager.go(toScene, transition);
    }
}
