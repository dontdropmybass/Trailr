package ca.alexcochrane.trailr.model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import ca.alexcochrane.trailr.controller.MovieDatabaseHelper;

public class Film {

    private int id;
    private float rating;
    private String title;
    private String description;
    private String thumbnail;
    private String trailer;

    private Film() {
        // Empty Default Constructor
    }

    public Film(int id, float rating, String title, String description, String thumbnail, String trailer) {
        this.id = id;
        this.rating = rating;
        this.title = title;
        this.description = description;
        this.thumbnail = thumbnail;
        this.trailer = trailer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void save(Context context) throws SQLException {
        SQLiteDatabase db = new MovieDatabaseHelper(context).getWritableDatabase();
        String query = "UPDATE `films` SET "
                + "rating = " + rating + ", "
                + "title = '" + title + "', "
                + "desc = '" + description + "', "
                + "thumbnail = '" + thumbnail + "', "
                + "trailer = '" + trailer + "' "
                + "WHERE id = " + id + ";";
        db.execSQL(query);
    }
}
