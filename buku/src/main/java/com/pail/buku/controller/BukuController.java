package com.pail.buku.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pail.buku.model.BukuModel;
import com.pail.buku.service.BukuService;

@RestController
@RequestMapping("/api/buku")
public class BukuController {
    @Autowired
    private BukuService bukuService;

    @GetMapping
    public List<BukuModel> getAllBuku() {
        return bukuService.getAllBuku();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BukuModel> getBukubyId(@PathVariable Long id) {
        BukuModel buku = bukuService.getBukuById(id);
        return buku != null ? ResponseEntity.ok(buku) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public BukuModel creteBuku(@RequestBody BukuModel buku) {
        return bukuService.createBuku(buku);
    }           

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuku(@PathVariable Long id) {
        bukuService.deleteBuku(id);
        return ResponseEntity.noContent().build();
    }
}
