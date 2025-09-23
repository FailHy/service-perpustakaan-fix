package com.pail.peminjaman.vo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class Anggota {
    private Long id;
    private String nim;
    private String nama;
    private String alamat;
    @Enumerated(EnumType.STRING)
    private jekel jenis_kelamin;

    // inisialisasi jenis kelamin
    public enum jekel {L, P}

    public Anggota() {
    }

    public Anggota(Long id, String nim, String nama, String alamat, jekel jenis_kelamin) {
        this.id = id;
        this.nim = nim;
        this.nama = nama;
        this.alamat = alamat;
        this.jenis_kelamin = jenis_kelamin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public jekel getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(jekel jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
    
}
