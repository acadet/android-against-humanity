package com.adriencadet.droidagainsthumanity.ui.routers;

/**
 * IObservableRouter
 * <p>
 */
public interface IObservableRouter {
    void observe(IRouterScoopChangedObserver observer);

    void stopObserving(IRouterScoopChangedObserver observer);
}
