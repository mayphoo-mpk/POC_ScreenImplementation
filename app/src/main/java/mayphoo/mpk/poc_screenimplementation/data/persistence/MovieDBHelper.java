package mayphoo.mpk.poc_screenimplementation.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 12/13/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies.db";

    // SQLite does not have a separate Boolean storage class. Instead, Boolean values are stored as integers 0 (false) and 1 (true).
    private static final String SQL_CREATE_POPULAR_MOVIE_TABLE = "CREATE TABLE " + MovieContract.PopularMovieEntry.TABLE_NAME + " (" +
            MovieContract.PopularMovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT + " INTEGER, " +
            MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID + " INTEGER, " +
            MovieContract.PopularMovieEntry.COLUMN_VIDEO + " INTEGER DEFAULT 0, " +
            MovieContract.PopularMovieEntry.COLUMN_VOTE_AVERAGE + " REAL, " +
            MovieContract.PopularMovieEntry.COLUMN_TITLE + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_POPULARITY + " REAL, " +
            MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_BACKDROP_PATH + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_ADULT + " INTEGER DEFAULT 0, " +
            MovieContract.PopularMovieEntry.COLUMN_OVERVIEW + " TEXT, " +
            MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE + " TEXT, " +

            " UNIQUE (" + MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_GENRE_IDS_TABLE =  "CREATE TABLE " + MovieContract.GenreIdsEntry.TABLE_NAME + " (" +
            MovieContract.GenreIdsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.GenreIdsEntry.COLUMN_GENRE_ID + " INTEGER, " +

            " UNIQUE (" + MovieContract.GenreIdsEntry.COLUMN_GENRE_ID + ") ON CONFLICT REPLACE" +
            " );";

    private static final String SQL_CREATE_MOVIE_GENRE_TABLE = "CREATE TABLE " + MovieContract.MovieGenreEntry.TABLE_NAME + " (" +
            MovieContract.MovieGenreEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MovieContract.MovieGenreEntry.COLUMN_MOVIE_ID + " INTEGER, " +
            MovieContract.MovieGenreEntry.COLUMN_GENRE_ID + " INTEGER" +
            " );";

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_POPULAR_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_GENRE_IDS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_GENRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieGenreEntry.TABLE_NAME);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.GenreIdsEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.PopularMovieEntry.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
