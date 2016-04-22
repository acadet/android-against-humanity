package com.adriencadet.androidagainsthumanity.ui.transitions;

import android.transition.Scene;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;

import com.lyft.scoop.ScreenTransition;
import com.lyft.scoop.TransitionListener;

/**
 * FABTransition
 * <p>
 */
public class FABTransition implements ScreenTransition {

    @Override
    public void transition(ViewGroup root, View from, View to, TransitionListener transitionListener) {
        Scene toScene = new Scene(root, to);

        TransitionSet transitionSet = new TransitionSet();

        transitionSet
            .setOrdering(TransitionSet.ORDERING_TOGETHER)
            .addTransition(new Slide());

        transitionSet.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transitionListener.onTransitionCompleted();
            }

            @Override
            public void onTransitionCancel(Transition transition) {
                transitionListener.onTransitionCompleted();
            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

        TransitionManager.go(toScene, transitionSet);
    }
}
