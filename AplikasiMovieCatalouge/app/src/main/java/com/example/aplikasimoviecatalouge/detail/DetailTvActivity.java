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
import com.example.aplikasimoviecatalouge.sql.TvEntity;
import com.example.aplikasimoviecatalouge.sql.TvHelper;
import com.example.aplikasimoviecatalouge.tvshow.ModelTvShow;

public class DetailTvActivity extends AppCompatActivity {
    public static final String MODEL = "model";
    TvHelper tvHelper;
    ModelTvShow modelTvShow;
    boolean isFavorite = false;
    Menu menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv);

        modelTvShow = getIntent().getParcelableExtra(MODEL);
        final ProgressBar progressBar = findViewById(R.id.pb_detail_tv);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = findViewById(R.id.imageView_detail_tv);
                TextView textViewTitle = findViewById(R.id.textView_title_detail_tv);
                TextView textViewDesc = findViewById(R.id.textView_desc_detail_tv);
                textViewTitle.setText(modelTvShow.getTitleTv());
                textViewDesc.setText(modelTvShow.getDescTv());
                Glide.with(DetailTvActivity.this).load("https://image.tmdb.org/t/p/w185/" + modelTvShow.getPosterTv()).into(imageView);
                progressBar.setVisibility(View.INVISIBLE);

            }
        }, 500);
        tvHelper = new TvHelper(DetailTvActivity.this);
        tvHelper.cekFavorite(modelTvShow.getIdTv());

        if (tvHelper.cekFavorite(modelTvShow.getIdTv()) == 1) {
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
                    tvHelper.deleteData(Integer.parseInt(modelTvShow.getIdTv()));
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
            TvEntity tvEntity = new TvEntity();
            tvEntity.setIdTv((modelTvShow.getIdTv()));
            tvEntity.setTitleTv(modelTvShow.getTitleTv());
            Log.d("INIJUDULFAVTV",modelTvShow.getTitleTv());
            tvEntity.setPosterTv(modelTvShow.getPosterTv());
            tvEntity.setDescTv(modelTvShow.getDescTv());
            tvHelper.insertData(tvEntity);
            tvHelper.close();
            Toast.makeText(DetailTvActivity.this,"BISA",Toast.LENGTH_SHORT).show();
        }catch (SQLiteConstraintException s){
            Log.e("ININGGAKBISA",s.getLocalizedMessage());
        }

    }

    void setFavorite(){
        if (isFavorite){
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(DetailTvActivity.this,R.drawable.ic_turned_in_black_24dp));
        }else {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(DetailTvActivity.this,R.drawable.ic_turned_in_white_24dp));
        }
    }
}

