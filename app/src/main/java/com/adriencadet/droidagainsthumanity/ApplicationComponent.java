package com.adriencadet.droidagainsthumanity;

import com.adriencadet.droidagainsthumanity.bll.BLLFactory;
import com.adriencadet.droidagainsthumanity.dao.DAOFactory;
import com.adriencadet.droidagainsthumanity.services.sockets.SocketServiceFactory;
import com.adriencadet.droidagainsthumanity.ui.activities.BaseActivity;
import com.adriencadet.droidagainsthumanity.ui.adapters.ConversationListAdapter;
import com.adriencadet.droidagainsthumanity.ui.containers.BodyContainer;
import com.adriencadet.droidagainsthumanity.ui.containers.FloatingButtonContainer;
import com.adriencadet.droidagainsthumanity.ui.containers.MainContainer;
import com.adriencadet.droidagainsthumanity.ui.containers.ModalContainer;
import com.adriencadet.droidagainsthumanity.ui.containers.ToastContainer;
import com.adriencadet.droidagainsthumanity.ui.controllers.BaseController;
import com.adriencadet.droidagainsthumanity.ui.routers.MainRouter;
import com.adriencadet.droidagainsthumanity.ui.routers.RouterFactory;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent
 * <p>
 */
@Singleton
@Component(modules = {
    SocketServiceFactory.class,
    DAOFactory.class,
    BLLFactory.class,
    ApplicationModule.class,
    RouterFactory.class
})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    // Routers
    void inject(MainRouter mainRouter);

    // Controllers
    void inject(BaseController baseController);

    // Adapters
    void inject(ConversationListAdapter conversationListAdapter);

    // Containers
    void inject(MainContainer mainContainer);

    void inject(BodyContainer bodyContainer);

    void inject(FloatingButtonContainer floatingButtonContainer);

    void inject(ModalContainer modalContainer);

    void inject(ToastContainer toastContainer);
}
