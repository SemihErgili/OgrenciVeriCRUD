package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityGuncelleBinding;
import com.example.myapplication.databinding.ActivityMainBinding;

public class GuncelleActivity extends AppCompatActivity {

    ActivityGuncelleBinding binding;
    Ogrenci gelenOgrenci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityGuncelleBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        SQLiteDatabase database = openOrCreateDatabase("okul",MODE_PRIVATE,null);
        setContentView(view);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("Öğrenci") != null) {

            gelenOgrenci = (Ogrenci) intent.getSerializableExtra("Öğrenci");
            binding.etNumara.setText(gelenOgrenci.numara);
            binding.etAd.setText(gelenOgrenci.ad);
            binding.etSoyad.setText(gelenOgrenci.soyad);
        }
        binding.btnSil.setOnClickListener(button->{
            String silmeSorgu = "DELETE FROM ogrenciler WHERE id=?";
            SQLiteStatement komut = database.compileStatement(silmeSorgu);
            komut.bindLong(1,gelenOgrenci.id);
            int silinenKayitSayisi=komut.executeUpdateDelete();
            if (silinenKayitSayisi>0){
                Toast.makeText(this,"Kayıt Silindi",Toast.LENGTH_LONG).show();
                finish();
            }


        });

        binding.btnGuncelle.setOnClickListener(button->{
            String guncellemeSorgusu ="UPDATE ogrenciler SET numara=?, ad=?, soyad=? WHERE id=?";
            String numara=binding.etNumara.getText().toString();
            String ad=binding.etAd.getText().toString();
            String soyad=binding.etSoyad.getText().toString();
            SQLiteStatement komut = database.compileStatement(guncellemeSorgusu);
            komut.bindString(1,numara);
            komut.bindString(2,ad);
            komut.bindString(3,soyad);
            komut.bindLong(4,gelenOgrenci.id);
            int guncellenenKayitSayisi = komut.executeUpdateDelete();
            if (guncellenenKayitSayisi>0){
                Toast.makeText(this,"Kayıt Güncellendi",Toast.LENGTH_LONG).show();
                finish();
            }


        });

    }
}