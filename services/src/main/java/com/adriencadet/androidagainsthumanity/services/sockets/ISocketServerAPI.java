package com.adriencadet.androidagainsthumanity.services.sockets;

import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * ISocketServerAPI
 * <p>
 */
interface ISocketServerAPI {
    @POST("/sessions")
    String createSession();

    @PUT("/sessions/{slug}")
    Void joinSession(@Path("slug") String slug);
}
