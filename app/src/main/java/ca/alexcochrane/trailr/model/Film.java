package ca.alexcochrane.trailr.model;

import android.content.ContentValues;
import android.content.Context;
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

    public boolean save(Context context) {
        SQLiteDatabase db = new MovieDatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("rating", rating);
        values.put("title", title);
        values.put("desc", description);
        values.put("thumbnail", thumbnail);
        values.put("trailer", trailer);

        return db.update("films", values, "id = "+id, null) > 0;
    }

    public boolean saveNew(Context context) {
        SQLiteDatabase db = new MovieDatabaseHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("rating", rating);
        values.put("title", title);
        values.put("desc", description);
        values.put("thumbnail", thumbnail);
        values.put("trailer", trailer);

        this.id = (int) db.insert("films", null, values);

        return this.id != -1;
    }

    public boolean delete(Context context) {
        SQLiteDatabase db = new MovieDatabaseHelper(context).getWritableDatabase();
        return db.delete("films","id = "+id,null) > 0;
    }


}
