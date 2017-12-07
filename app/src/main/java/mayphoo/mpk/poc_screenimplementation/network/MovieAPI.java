package mayphoo.mpk.poc_screenimplementation.network;

import mayphoo.mpk.poc_screenimplementation.network.responses.GetMovieResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 12/7/2017.
 */

public interface MovieAPI {

    @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    public Call<GetMovieResponse> loadPopularMovies(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);

}
