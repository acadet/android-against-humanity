package com.adriencadet.droidagainsthumanity.services.sockets;

import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * ISocketServerAPI
 * <p>
 */
interface ISocketServerAPI {
    @POST("/sessions")
    CreateConversationObject createSession();

    @PUT("/sessions/{slug}")
    Void joinSession(@Path("slug") String slug);
}
