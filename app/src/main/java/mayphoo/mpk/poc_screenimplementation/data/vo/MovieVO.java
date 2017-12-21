package mayphoo.mpk.poc_screenimplementation.data.vo;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mayphoo.mpk.poc_screenimplementation.data.persistence.MovieContract;

/**
 * Created by User on 12/7/2017.
 */

public class MovieVO {

    @SerializedName("vote_count")
    private long voteCount;

    @SerializedName("id")
    private long id;

    @SerializedName("video")
    private boolean video;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("title")
    private String title;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    public long getVoteCount() {
        return voteCount;
    }

    public long getId() {
        return id;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    //Change object format to content values format
    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT, voteCount);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID, id);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_VIDEO, video);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_VOTE_AVERAGE, voteAverage);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_TITLE, title);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_POPULARITY, popularity);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH, posterPath);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE, originalLanguage);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE, originalTitle);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_BACKDROP_PATH, backdropPath);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_ADULT, adult);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_OVERVIEW, overview);
        contentValues.put(MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE, releaseDate);

        return contentValues;
    }

    public static MovieVO parseFromCursor(Cursor cursor) {
        MovieVO movie = new MovieVO();
        movie.voteCount = cursor.getLong(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VOTE_COUNT));
        movie.id = cursor.getLong(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_MOVIE_ID));
        if(cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VIDEO)) > 0){
            movie.video = true;
        } else {
            movie.video = false;
        }
        movie.voteAverage = cursor.getDouble(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_VOTE_AVERAGE));
        movie.title = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_TITLE));
        movie.popularity = cursor.getDouble(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_POPULARITY));
        movie.posterPath = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_POSTER_PATH));
        movie.originalLanguage = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_LANGUAGE));
        movie.originalTitle = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ORIGINAL_TITLE));
        movie.backdropPath = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_BACKDROP_PATH));
        if(cursor.getInt(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_ADULT)) > 0){
            movie.adult = true;
        } else {
            movie.adult = false;
        }
        movie.overview = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_OVERVIEW));
        movie.releaseDate = cursor.getString(cursor.getColumnIndex(MovieContract.PopularMovieEntry.COLUMN_RELEASE_DATE));

        return movie;
    }

}
