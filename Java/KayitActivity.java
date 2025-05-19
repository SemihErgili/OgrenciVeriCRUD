package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityKayitBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class KayitActivity extends AppCompatActivity {

    ActivityKayitBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityKayitBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        binding.btnKaydet.setOnClickListener(button->{
            SQLiteDatabase database = this.openOrCreateDatabase("okul",MODE_PRIVATE,null);
            String numara=binding.etNumara.getText().toString();
            String ad=binding.etAd.getText().toString();
            String soyad=binding.etSoyad.getText().toString();
            String eklemeSorgusu ="INSERT INTO ogrenciler (numara,ad,soyad) VALUES(?,?,?)";
            SQLiteStatement komut = database.compileStatement(eklemeSorgusu);

            komut.bindString(1,numara);
            komut.bindString(2,ad);
            komut.bindString(3,soyad);

            int eklenenKayitSayisi=(int)komut.executeInsert();
            if(eklenenKayitSayisi>0)
            {
                Toast.makeText(this,"Kayıt Eklendi",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}