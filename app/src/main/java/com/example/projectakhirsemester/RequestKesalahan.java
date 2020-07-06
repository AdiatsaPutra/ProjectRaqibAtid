package com.example.projectakhirsemester;

import java.io.Serializable;

public class RequestKesalahan implements Serializable {

    private String judul;
    private String keterangan;
    private String tanggal;
    private String key;

    public RequestKesalahan() {
    }


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString(){
        return " " + judul + "\n" + " " + keterangan + "\n" + " " + tanggal;
    }

    public RequestKesalahan(String judul, String keterangan, String tanggal){
        this.judul = judul;
        this.keterangan = keterangan;
        this.tanggal = tanggal;
    }
}
