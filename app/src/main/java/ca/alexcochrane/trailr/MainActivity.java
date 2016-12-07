package ca.alexcochrane.trailr;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import ca.alexcochrane.trailr.controller.MovieDatabaseHelper;
import ca.alexcochrane.trailr.model.Film;
import ca.alexcochrane.trailr.view.FilmFragment;

public class MainActivity extends AppCompatActivity implements FilmFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MovieDatabaseHelper.loadAllFilms(new MovieDatabaseHelper(this).getReadableDatabase()).size()<1) {
            MovieDatabaseHelper.seedDatabase(new MovieDatabaseHelper(this).getWritableDatabase());
        }
        setContentView(R.layout.activity_main);
        ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(this).build());
    }

    @Override
    public void onListFragmentInteraction(Film film) {

    }
}
