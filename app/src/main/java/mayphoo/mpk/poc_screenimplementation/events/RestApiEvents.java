package mayphoo.mpk.poc_screenimplementation.events;

import java.util.List;

import mayphoo.mpk.poc_screenimplementation.data.vo.MovieVO;

/**
 * Created by User on 12/7/2017.
 */

public class RestApiEvents {

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg){
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg(){
            return errorMsg;
        }
    }

    public static class EmptyResponseEvent {
        private String emptyMsg;

        public EmptyResponseEvent(String emptyMsg){
            this.emptyMsg = emptyMsg;
        }

        public String getEmptyMsg(){
            return emptyMsg;
        }
    }

    public static class PopularMoviesDataLoadedEvent {
        private int loadedPageIndex;
        private List<MovieVO> loadedMovies;

        public PopularMoviesDataLoadedEvent(int loadedPageIndex, List<MovieVO> loadedMovies) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadedMovies = loadedMovies;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<MovieVO> getLoadedMovies() {
            return loadedMovies;
        }

    }

}
