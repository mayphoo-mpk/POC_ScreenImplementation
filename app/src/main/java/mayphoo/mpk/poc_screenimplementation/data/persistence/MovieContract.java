package mayphoo.mpk.poc_screenimplementation.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import mayphoo.mpk.poc_screenimplementation.POCMoviesApp;

/**
 * Created by User on 12/13/2017.
 */

public class MovieContract {

    public static final String CONTENT_AUTHORITY = POCMoviesApp.class.getPackage().getName();
    //mayphoo.mpk.poc_screenimplementation

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    //content://mayphoo.mpk.poc_screenimplementation

    public static final String PATH_POPULAR_MOVIES ="popular_movies";
    public static final String PATH_GENRE_IDS = "genre_ids";

    public static final class PopularMovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_POPULAR_MOVIES).build();
        //content://mayphoo.mpk.poc_screenimplementation/popular_movies

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POPULAR_MOVIES;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_POPULAR_MOVIES;

        public static final String TABLE_NAME = "popular_movies";

        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_ADULT = "adult";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static Uri buildPopularMovieUri(long id) {
            //content://mayphoo.mpk.poc_screenimplementation/popular_movies/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class GenreIdsEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE_IDS).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE_IDS;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE_IDS;

        public static final String TABLE_NAME = "genre_ids";

        public static final String COLUMN_MOVIE_TITLE = "movie_title";
        public static final String COLUMN_GENRE_ID = "genre_id";

        public static Uri buildGenreIdsUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }



}
