package ca.alexcochrane.trailr.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

import ca.alexcochrane.trailr.R;
import ca.alexcochrane.trailr.model.Film;

public class FilmAdapter extends ArrayAdapter<Film> {

    private List<Film> objects;

    public FilmAdapter(Context context, int resource, List<Film> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {

        // inflate the inflater and get the layout
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        @SuppressWarnings("all")
        final View view = inflater.inflate(R.layout.fragment_film, parent, false);

        // set up all the pieces of the view
        ImageView thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView description = (TextView) view.findViewById(R.id.description);
        RatingBar rating = (RatingBar) view.findViewById(R.id.rating);

        final Film film = objects.get(position);

        ImageLoader.getInstance().displayImage(film.getThumbnail(),thumbnail);
        title.setText(film.getTitle());
        description.setText(film.getDescription());
        rating.setRating(film.getRating());

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                film.setRating(v);
                film.save(view.getContext());
            }
        });

        return view;
    }

}
