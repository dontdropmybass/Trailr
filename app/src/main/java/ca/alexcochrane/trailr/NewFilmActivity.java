package ca.alexcochrane.trailr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import ca.alexcochrane.trailr.model.Film;

public class NewFilmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_film);

        final EditText titleField = (EditText) findViewById(R.id.titleField);
        final EditText descriptionField = (EditText) findViewById(R.id.descriptionField);
        final EditText thumbnailField = (EditText) findViewById(R.id.thumbnailField);
        final EditText trailerField = (EditText) findViewById(R.id.trailerField);
        final RatingBar ratingField = (RatingBar) findViewById(R.id.ratingField);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button okButton = (Button) findViewById(R.id.okButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewFilmActivity.this, MainActivity.class));
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Film(
                        -1,
                        ratingField.getRating(),
                        titleField.getText().toString(),
                        descriptionField.getText().toString(),
                        thumbnailField.getText().toString(),
                        trailerField.getText().toString()
                ).saveNew(NewFilmActivity.this);

                startActivity(new Intent(NewFilmActivity.this, MainActivity.class));
            }
        });
    }
}
