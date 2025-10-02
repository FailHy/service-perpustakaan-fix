package com.pail.pengembalian.controller;

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

import com.pail.pengembalian.model.PengembalianModel;
import com.pail.pengembalian.service.PengembalianService;
import com.pail.pengembalian.vo.ResponseTemplate;

@RestController
@RequestMapping("/api/pengembalian")
public class PengembalianController {
   @Autowired
    private PengembalianService pengembalianService;

    @GetMapping
    public List<PengembalianModel> getAllPengembalian() {
        return pengembalianService.getAllPengembalian(); // pakai instance, bukan class
    }

    @GetMapping("/{id}")
    public ResponseEntity<PengembalianModel> getPengembalianById(@PathVariable Long id) {
        PengembalianModel pengembalian = pengembalianService.getPengembalianById(id); // perbaikan pemanggilan
        return pengembalian != null ? ResponseEntity.ok(pengembalian) : ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/peminjaman/{id}")
    public List<ResponseTemplate> getPengembalianWithPeminjamanById(@PathVariable Long id) {
        return pengembalianService.getPengembalianWithPeminjamanById(id);
    }

    @PostMapping
    public PengembalianModel createPengembalian(@RequestBody PengembalianModel pengembalian) {
        return pengembalianService.createPengembalian(pengembalian); // perbaikan
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeminjaman(@PathVariable Long id) {
        pengembalianService.deletePengembalian(id); // perbaikan
        return ResponseEntity.ok().build();
    }
}
