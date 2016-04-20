package com.adriencadet.androidagainsthumanity.ui.controllers;

import android.content.Context;

import com.adriencadet.androidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.androidagainsthumanity.bll.IConversationBLL;
import com.adriencadet.androidagainsthumanity.ui.routers.IRouter;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;

/**
 * BaseController
 * <p>
 */
public abstract class BaseController extends ViewController {

    public abstract class BaseSubscriber<T> extends Subscriber<T> {
        @Override
        public void onError(Throwable e) {
            /**
             if (e instanceof BLLErrors.NoConnection) {
             warn(context.getString(R.string.no_connection_error));
             } else if (e instanceof BLLErrors.ServiceError) {
             warn(context.getString(R.string.internal_server_error));
             } else {
             alert(e.getMessage());
             Timber.e(e, "Unhandled error");
             }
             */
        }
    }

    @Inject
    public Context context;

    @Inject
    @Named("main")
    public IRouter mainRouter;

    @Inject
    public IConversationBLL conversationBLL;

    @Override
    public void onAttach() {
        super.onAttach();

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
    }

    public void inform(String message) {
        //popupRouter.goTo(new InfoScreen(message));
    }

    public void confirm(String message) {
        // popupRouter.goTo(new ConfirmScreen(message));
    }

    public void warn(String message) {
        //popupRouter.goTo(new WarnScreen(message));
    }

    public void alert(String message) {
        //popupRouter.goTo(new AlertScreen(message));
    }
}
