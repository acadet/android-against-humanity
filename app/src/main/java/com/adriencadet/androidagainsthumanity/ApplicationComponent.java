package com.adriencadet.androidagainsthumanity;

import com.adriencadet.androidagainsthumanity.bll.BLLFactory;
import com.adriencadet.androidagainsthumanity.dao.DAOFactory;
import com.adriencadet.androidagainsthumanity.services.sockets.SocketServiceFactory;
import com.adriencadet.androidagainsthumanity.ui.activities.BaseActivity;
import com.adriencadet.androidagainsthumanity.ui.containers.BodyContainer;
import com.adriencadet.androidagainsthumanity.ui.containers.FloatingButtonContainer;
import com.adriencadet.androidagainsthumanity.ui.containers.MainContainer;
import com.adriencadet.androidagainsthumanity.ui.routers.MainRouter;
import com.adriencadet.androidagainsthumanity.ui.routers.RouterFactory;

import dagger.Component;

/**
 * ApplicationComponent
 * <p>
 */
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

    // Containers
    void inject(MainContainer mainContainer);

    void inject(BodyContainer bodyContainer);

    void inject(FloatingButtonContainer floatingButtonContainer);
}
