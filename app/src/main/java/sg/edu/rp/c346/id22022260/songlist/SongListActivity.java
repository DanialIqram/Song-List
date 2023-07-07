package sg.edu.rp.c346.id22022260.songlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> songList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        lv = findViewById(R.id.lv);
        ArrayAdapter aaSongs = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songList);
        lv.setAdapter(aaSongs);

        DBHelper db = new DBHelper(SongListActivity.this);
        ArrayList<Song> songs = db.getSongs();
        db.close();

        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);

            int id = song.get_id();
            String title = song.getTitle();
            String singers = song.getSingers();
            int year = song.getYear();
            int stars = song.getStars();

            String txt = String.format("%d) Title: %s\n     Singers: %s\n     Year: %d\n     Stars: %d", id, title, singers, year, stars);
            songList.add(txt);
        }

        aaSongs.notifyDataSetChanged();
    }
}