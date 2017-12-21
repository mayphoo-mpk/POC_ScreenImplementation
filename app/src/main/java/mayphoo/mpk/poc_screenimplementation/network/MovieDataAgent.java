package mayphoo.mpk.poc_screenimplementation.network;

import android.content.Context;

/**
 * Created by User on 12/7/2017.
 */

public interface MovieDataAgent {

    void loadPopularMovies(String accessToken, int pageNo, Context context);
}
