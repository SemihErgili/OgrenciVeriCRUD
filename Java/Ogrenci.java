package com.example.myapplication;

import java.io.Serializable;

public class Ogrenci implements Serializable {

        int id;
        String numara;
        String ad;
        String soyad;

    public Ogrenci(int id, String numara, String ad, String soyad) {
        this.id = id;
        this.numara = numara;
        this.ad = ad;
        this.soyad = soyad;
    }

    @Override
    public String toString() {
        return numara+"-"+ad+"  "+soyad;
    }
}
