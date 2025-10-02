package com.pail.buku.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pail.buku.model.BukuModel;
import com.pail.buku.repository.BukuRepository;

@Service
public class BukuService {
    @Autowired

    private BukuRepository BukuRepository;

    public List<BukuModel> getAllBuku() {
        return BukuRepository.findAll();
    }

    public BukuModel getBukuById(Long id) {
        return BukuRepository.findById(id).orElse(null);
    }

    public BukuModel createBuku(BukuModel buku) {
        return BukuRepository.save(buku);
    }

    public void deleteBuku(Long id) {
        BukuRepository.deleteById(id);
    }
}
