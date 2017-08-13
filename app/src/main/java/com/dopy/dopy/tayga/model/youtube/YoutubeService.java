package com.dopy.dopy.tayga.model.youtube;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dopy on 2017-08-12.
 */

public interface YoutubeService {
    @GET("search?")
    Call<YoutubeList> searchVideoList(@Query("part") String part,
                                      @Query("q") String keyword,
                                      @Query("key") String apiKey,
                                      @Query("maxResults") int count,
                                      @Query("type") String type);

}
