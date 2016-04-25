package com.adriencadet.droidagainsthumanity.ui.controllers;

import android.content.Context;

import com.adriencadet.droidagainsthumanity.AndroidAgainstHumanityApplication;
import com.adriencadet.droidagainsthumanity.R;
import com.adriencadet.droidagainsthumanity.bll.IConversationBLL;
import com.adriencadet.droidagainsthumanity.bll.IMessageBLL;
import com.adriencadet.droidagainsthumanity.bll.IUserBLL;
import com.adriencadet.droidagainsthumanity.ui.routers.IRouter;
import com.adriencadet.droidagainsthumanity.ui.screens.toast.AlertScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.toast.ConfirmScreen;
import com.adriencadet.droidagainsthumanity.ui.screens.toast.InformScreen;
import com.lyft.scoop.ViewController;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Subscriber;
import timber.log.Timber;

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
            alert(context.getString(R.string.unhandled_error));
            Timber.e(e, "Unhandled error");
        }
    }

    @Inject
    public Context context;

    @Inject
    @Named("main")
    public IRouter mainRouter;

    @Inject
    @Named("modal")
    public IRouter modalRouter;

    @Inject
    @Named("toast")
    public IRouter toastRouter;

    @Inject
    public IConversationBLL conversationBLL;

    @Inject
    public IMessageBLL messageBLL;

    @Inject
    public IUserBLL userBLL;

    @Override
    public void onAttach() {
        super.onAttach();

        AndroidAgainstHumanityApplication.getApplicationComponent().inject(this);
    }

    public void inform(String message) {
        toastRouter.goTo(new InformScreen(message));
    }

    public void confirm(String message) {
        toastRouter.goTo(new ConfirmScreen(message));
    }

    public void warn(String message) {
        //popupRouter.goTo(new WarnScreen(message));
    }

    public void alert(String message) {
        toastRouter.goTo(new AlertScreen(message));
    }
}
