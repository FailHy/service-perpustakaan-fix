package com.pail.peminjaman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pail.peminjaman.model.PeminjamanModel;
import com.pail.peminjaman.service.PeminjamanService;
import com.pail.peminjaman.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanController {
   @Autowired
    private PeminjamanService peminjamanService;

    @GetMapping
    public List<PeminjamanModel> getAllPeminjaman() {
        return peminjamanService.getAllPeminjaman();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeminjamanModel> getPeminjamanById(@PathVariable Long id) {
        PeminjamanModel peminjaman = peminjamanService.getPeminjamanById(id);
        return peminjaman != null ? ResponseEntity.ok(peminjaman) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/anggota/{id}")
    public List<ResponseTemplate> getPeminjamanWithAnggotaById(@PathVariable Long id) {
        return peminjamanService.getPeminjamanWithAnggotaById(id);
    }

    @PostMapping
    public PeminjamanModel createPeminjaman(@RequestBody PeminjamanModel peminjaman) {
        return peminjamanService.createPeminjaman(peminjaman);   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeminjaman(@PathVariable Long id) {
        peminjamanService.deletePeminjaman(id); 
        return ResponseEntity.ok().build();
    }
}
