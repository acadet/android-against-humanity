package com.adriencadet.androidagainsthumanity;

import com.adriencadet.androidagainsthumanity.bll.BLLFactory;
import com.adriencadet.androidagainsthumanity.dao.DAOFactory;
import com.adriencadet.androidagainsthumanity.services.sockets.SocketServiceFactory;
import com.adriencadet.androidagainsthumanity.ui.activities.BaseActivity;

import dagger.Component;

/**
 * ApplicationComponent
 * <p>
 */
@Component(modules = {
    SocketServiceFactory.class,
    DAOFactory.class,
    BLLFactory.class,
    ApplicationModule.class
})
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
}
