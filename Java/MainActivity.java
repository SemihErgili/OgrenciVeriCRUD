package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase database;
    ActivityMainBinding binding;
    ArrayList<Ogrenci> ogrenciler=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        database = this.openOrCreateDatabase("okul",MODE_PRIVATE,null);
        String tabloOlusturmaSorgusu="CREATE TABLE IF NOT EXISTS ogrenciler(id INTEGER PRIMARY KEY," + "numara TEXT, ad TEXT, soyad TEXT)";
        database.execSQL(tabloOlusturmaSorgusu);
        listeyiDoldur();
        binding.yenikayit.setOnClickListener(button->{

            Intent intent = new Intent(this,KayitActivity.class);
            startActivity(intent);
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), GuncelleActivity.class);
                intent.putExtra("Öğrenci",ogrenciler.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        listeyiDoldur();
    }

    private void listeyiDoldur() {
        ogrenciler.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM ogrenciler",null);
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String numara = cursor.getString(1);
            String ad = cursor.getString(2);
            String soyad = cursor.getString(3);
            Ogrenci ogrenci = new Ogrenci(id,numara,ad,soyad);
            ogrenciler.add(ogrenci);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ogrenciler);
        binding.listview.setAdapter(adapter);


    }
}