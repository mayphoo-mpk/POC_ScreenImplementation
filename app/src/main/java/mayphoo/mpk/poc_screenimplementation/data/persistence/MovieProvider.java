package mayphoo.mpk.poc_screenimplementation.data.persistence;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by User on 12/22/2017.
 */

public class MovieProvider extends ContentProvider {

    public static final int POPULAR_MOVIES = 100;
    public static final int GENRE_IDS = 200;
    public static final int MOVIE_GENRE = 300;

    private static final UriMatcher sUriMATCHER = buildUriMatcher();

    private MovieDBHelper mDBHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);    //NO_MATCH -> initial value
        // add uri value to support
        uriMatcher.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_POPULAR_MOVIES, POPULAR_MOVIES);
        uriMatcher.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_GENRE_IDS, GENRE_IDS);
        uriMatcher.addURI(MovieContract.CONTENT_AUTHORITY, MovieContract.PATH_MOVIE_GENRE, MOVIE_GENRE);

        return uriMatcher;
    }

    private String getTableName(Uri uri){
        switch (sUriMATCHER.match(uri)){
            case POPULAR_MOVIES:
                return MovieContract.PopularMovieEntry.TABLE_NAME;
            case GENRE_IDS:
                return MovieContract.GenreIdsEntry.TABLE_NAME;
            case MOVIE_GENRE:
                return MovieContract.MovieGenreEntry.TABLE_NAME;
        }
        return null;
    }

    public Uri getContentUri(Uri uri){
        switch (sUriMATCHER.match(uri)){
            case POPULAR_MOVIES:
                return MovieContract.PopularMovieEntry.CONTENT_URI;
            case GENRE_IDS:
                return MovieContract.GenreIdsEntry.CONTENT_URI;
            case MOVIE_GENRE:
                return MovieContract.MovieGenreEntry.CONTENT_URI;
        }
        return null;
    }

    //setup persistence layer
    @Override
    public boolean onCreate() {
        mDBHelper = new MovieDBHelper(getContext());
        return true;
    }

    //query is retrieve
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor = mDBHelper.getReadableDatabase().query(getTableName(uri),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        Context context = getContext();
        if(context != null) {
            //when changes uri(eg: insert, update, delete), notify cursor.
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMATCHER.match(uri)){
            case POPULAR_MOVIES:
                return MovieContract.PopularMovieEntry.DIR_TYPE;
            case GENRE_IDS:
                return MovieContract.GenreIdsEntry.ITEM_TYPE;
            case MOVIE_GENRE:
                return MovieContract.MovieGenreEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    //Parameter: contentValues is insert objects datas (only primitive data type)
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, contentValues);
        if(_id > 0){
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if(getContext() != null){
                //notify uri when change in database (insert)
                getContext().getContentResolver().notifyChange(uri, null);
            }

            return insertedUri;
        }

        return null;
    }

    // insert data collection
    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if(context != null){
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowDeleted;
        String tableName = getTableName(uri);

        rowDeleted = db.delete(tableName, selection, selectionArgs);
        Context context = getContext();
        if(context != null && rowDeleted > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = getContext();
        if(context != null && rowUpdated > 0){
            context.getContentResolver().notifyChange(uri, null);
        }

        return rowUpdated;
    }
}
