package com.pail.anggota.controller;

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

import com.pail.anggota.model.AnggotaModel;
import com.pail.anggota.service.AnggotaService;

@RestController
@RequestMapping("/api/anggota")
public class AnggotaController {
    @Autowired
    private AnggotaService anggotaService;

    @GetMapping
    public List<AnggotaModel> getAllAnggota() {
        return anggotaService.getAllAnggota();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnggotaModel> getAnggotabyId(@PathVariable Long id) {
        AnggotaModel anggota = anggotaService.getAnggotaById(id);
        return anggota != null ? ResponseEntity.ok(anggota) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public AnggotaModel creteAnggota(@RequestBody AnggotaModel anggota) {
        return anggotaService.createAnggota(anggota);
    }           

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnggota(@PathVariable Long id) {
        anggotaService.deleteAnggota(id);
        return ResponseEntity.noContent().build();
    }
}
