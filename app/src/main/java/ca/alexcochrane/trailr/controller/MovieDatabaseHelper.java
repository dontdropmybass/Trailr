package ca.alexcochrane.trailr.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ca.alexcochrane.trailr.model.Film;

public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "MovieDatabase.db";
    private static final int DB_VERSION = 1;
    private static final String DB_CREATE_ENTRIES =
                    "CREATE TABLE `films` (`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "`rating`	REAL NOT NULL DEFAULT 0," +
                    "`title`	TEXT NOT NULL," +
                    "`desc`	TEXT," +
                    "`thumbnail`	TEXT NOT NULL DEFAULT '?'," +
                    "`trailer`	TEXT NOT NULL DEFAULT '?'" +
                    ");";
    private static final String DB_DELETE_ENTRIES = "DROP TABLE IF EXISTS films";

    public MovieDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DB_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public static List<Film> loadAllFilms(SQLiteDatabase sqLiteDatabase) {
        List<Film> films = new ArrayList<>();
        String select = "SELECT * FROM films;";
        final Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            films.add(new Film(
                            cursor.getInt(0),
                            cursor.getFloat(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5)
                        )
            );
        }
        cursor.close();
        return films;
    }

    public static void seedDatabase(SQLiteDatabase sqLiteDatabase) {
        String[] seeds = {
                "INSERT INTO `films`('rating','thumbnail','trailer',`title`,`desc`) VALUES" +
                        "(4.0,"+
                        "'http://www.wtfgamersonly.com/wp-content/uploads/2014/09/Guardians-of-the-Galaxy-img.1.jpg',"+
                        "'http://videos.hd-trailers.net/guardians-of-the-galaxy-trailer-2-480p-HDTN.mp4',"+
                        "'Guardians of The Galaxy',"+
                        "'Brash space adventurer Peter Quill (Chris Pratt) finds himself the quarry of relentless bounty hunters after he steals an orb coveted by Ronan,"+
                        " a powerful villain. To evade Ronan, Quill is forced into an uneasy truce with four disparate misfits: gun-toting Rocket Raccoon, treelike-humanoid Groot, enigmatic Gamora, "+
                        "and vengeance-driven Drax the Destroyer. But when he discovers the orbs true power and the cosmic threat it poses, Quill must rally his ragtag group to save the universe.');",
                "INSERT INTO `films`('rating','thumbnail','trailer',`title`,`desc`) VALUES" +
                        "(3.8,"+
                        "'http://www.themarysue.com/wp-content/uploads/2015/05/Avengers-Age-of-Ultron.jpeg',"+
                        "'http://videos.hd-trailers.net/Avengers_2_trailer_3_51-1080p-HDTN.mp4',"+
                        "'Avengers: Age of Ultron',"+
                        "'When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and its up to Earths Mightiest Heroes to stop the villainous Ultron from enacting his terrible plans. ');",
                "INSERT INTO `films`('rating','thumbnail','trailer',`title`,`desc`) VALUES" +
                        "(4.2,"+
                        "'http://vignette2.wikia.nocookie.net/inglouriousbasterds/images/2/26/Inglourious_Basterds_red_drawing.jpg/revision/latest?cb=20140731212836',"+
                        "'http://www.imdb.com/video/imdb/vi3738173977/imdb/embed',"+
                        "'Inglorious Bastards',"+
                        "'It is the first year of Germanys occupation of France. Allied officer Lt. Aldo Raine (Brad Pitt) assembles a team of Jewish soldiers to commit violent acts of retribution against the Nazis,"+
                        "including the taking of their scalps. He and his men join forces with Bridget von Hammersmark, a German actress and undercover agent, to bring down the leaders of the Third Reich. Their fates "+
                        "converge with theater owner Shosanna Dreyfus, who seeks to avenge the Nazis execution of her family.');",
                "INSERT INTO `films`('rating','thumbnail','trailer',`title`,`desc`) VALUES" +
                        "(3.8,"+
                        "'https://events.ucsb.edu/wp-content/uploads/2014/12/fury-movie-wide.jpg',"+
                        "'http://videos.hd-trailers.net/Fury_Trailer2_51-1080p-HDTN.mp4',"+
                        "'Fury',"+
                        "'In April 1945, the Allies are making their final push in the European theater. A battle-hardened Army sergeant named Don \"Wardaddy\" Collier (Brad Pitt), leading "+
                        "a Sherman tank and a five-man crew, undertakes a deadly mission behind enemy lines. Hopelessly outnumbered, outgunned and saddled with an inexperienced soldier (Logan Lerman)"+
                        "in their midst, Wardaddy and his men face overwhelming odds as they move to strike at the heart of Nazi Germany.');",
                "INSERT INTO `films`('rating','thumbnail','trailer',`title`,`desc`) VALUES" +
                        "(4.0,"+
                        "'http://www.designbolts.com/wp-content/uploads/2016/04/Marvels-Captain-America-Civil-War-2016-Official-Wallpapers-HD-1.jpg',"+
                        "'http://videos.hd-trailers.net/Captain_America_Winter_Soldier_TLR-H-UK-5.1-1080p-HDTN.mp4',"+
                        "'Captain America: Civil War',"+
                        "'Political interference in the Avengers activities causes a rift between former allies Captain America and Iron Man. ');",
                "INSERT INTO `films`('rating','thumbnail','trailer',`title`,`desc`) VALUES" +
                        "(3.4,"+
                        "'http://fm.cnbc.com/applications/cnbc.com/resources/img/editorial/2015/04/17/102597285-Batman-vs-Superman.1910x1000.jpeg',"+
                        "'http://videos.hd-trailers.net/BatmanvSuperman_TLR-1_5.1-1080p-HDTN.mp4',"+
                        "'Batman vs. Superman',"+
                        "'Its been nearly two years since Supermans (Henry Cavill) colossal battle with Zod (Michael Shannon) devastated the city of Metropolis. The loss of life and collateral damage left many feeling angry and helpless, including crime-fighting billionaire Bruce Wayne (Ben Affleck). Convinced that Superman is now a threat to humanity, Batman embarks on a personal vendetta to end his reign on Earth, while the conniving Lex Luthor (Jesse Eisenberg) launches his own crusade against the Man of Steel.');"
        };
        try {
            for (String s : seeds) {
                sqLiteDatabase.execSQL(s);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
