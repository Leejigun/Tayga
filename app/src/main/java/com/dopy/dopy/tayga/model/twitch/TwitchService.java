package com.dopy.dopy.tayga.model.twitch;

import com.dopy.dopy.tayga.model.game.GameItemList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Dopy on 2017-08-14.
 */

public interface TwitchService {
    @Headers("Client-ID:2ern1d9404oilcymc4z8xioyaxf14w")
    @GET("streams/?")
    Call<TwitchStreamList> searchStreamList(@Query("language") String langeuage,
                                            @Query("limit") int limit,
                                            @Query("offset") int offset);

    @Headers("Client-ID:2ern1d9404oilcymc4z8xioyaxf14w")
    @GET("games/top?limit=50")
    Call<GameItemList> searchGameList();


}
