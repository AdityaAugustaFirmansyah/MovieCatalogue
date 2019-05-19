package com.example.aplikasimoviecatalouge.detail;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aplikasimoviecatalouge.R;
import com.example.aplikasimoviecatalouge.movie.ModelMovie;
import com.example.aplikasimoviecatalouge.sql.MovieEntity;
import com.example.aplikasimoviecatalouge.sql.MovieHelper;




public class DetailFilmActivity extends AppCompatActivity {

    public static final String MODEL = "model";
    public static final String DESC = "desc";
    ModelMovie modelMovie;
    MovieHelper movieHelper;
    boolean isFavorite = false;
    String desc;
    Menu menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);
        final ProgressBar progressBar = findViewById(R.id.pb_detail);
        progressBar.setVisibility(View.VISIBLE);
        modelMovie = getIntent().getParcelableExtra(MODEL);
        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = findViewById(R.id.imageView_detail);
                TextView textViewTitle = findViewById(R.id.textView_title_detail);
                TextView textViewDesc = findViewById(R.id.textView_desc_detail);
                desc = getIntent().getStringExtra(DESC);
                textViewTitle.setText(modelMovie.getTitle());
                textViewDesc.setText(desc);
                Glide.with(DetailFilmActivity.this).load("https://image.tmdb.org/t/p/w185/" + modelMovie.getPostterMovie()).into(imageView);
                progressBar.setVisibility(View.INVISIBLE);

            }
        }, 500);
        movieHelper = new MovieHelper(DetailFilmActivity.this);
        movieHelper.cekFavorite(modelMovie.getIdMovie());
        Log.d("INIBISATIDAK", String.valueOf(movieHelper.cekFavorite(modelMovie.getIdMovie())));
        if (movieHelper.cekFavorite(modelMovie.getIdMovie()) == 1) {
            isFavorite = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        menuItem = menu;
        setFavorite();
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.favorite:
                if (isFavorite){
                    movieHelper.deleteData(Integer.parseInt(modelMovie.getIdMovie()));
                }else {
                    addFavorite();
                }
                isFavorite = !isFavorite;
                setFavorite();
        }
        return super.onOptionsItemSelected(item);
    }

    void addFavorite(){
        try {
            MovieEntity movieEntity = new MovieEntity();
            movieEntity.setId((modelMovie.getIdMovie()));
            movieEntity.setName(modelMovie.getTitle());
            Log.d("INIJUDULFAV",modelMovie.getTitle());
            movieEntity.setPoster_path(modelMovie.getPostterMovie());
            movieEntity.setOverview(desc);
            movieHelper.insertData(movieEntity);
            movieHelper.close();
            Toast.makeText(DetailFilmActivity.this,"BISA",Toast.LENGTH_SHORT).show();
        }catch (SQLiteConstraintException s){
            Log.e("ININGGAKBISA",s.getLocalizedMessage());
        }

    }

    void setFavorite(){
        if (isFavorite){
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(DetailFilmActivity.this,R.drawable.ic_turned_in_black_24dp));
        }else {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(DetailFilmActivity.this,R.drawable.ic_turned_in_white_24dp));
        }
    }
}
