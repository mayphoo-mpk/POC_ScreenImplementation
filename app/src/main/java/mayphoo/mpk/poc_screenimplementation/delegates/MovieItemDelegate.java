package mayphoo.mpk.poc_screenimplementation.delegates;

import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;

/**
 * Created by User on 12/8/2017.
 */

public interface MovieItemDelegate {

    void onTapMovieOverview(MovieVO movie);
    void onTapMovie(MovieVO movie);
}
