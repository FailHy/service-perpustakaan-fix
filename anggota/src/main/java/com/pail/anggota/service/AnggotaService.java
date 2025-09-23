package com.pail.anggota.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pail.anggota.model.AnggotaModel;
import com.pail.anggota.repository.AnggotaRepository;

@Service
public class AnggotaService {
    @Autowired

    private AnggotaRepository anggotaRepository;

    public List<AnggotaModel> getAllAnggota() {
        return anggotaRepository.findAll();
    }

    public AnggotaModel getAnggotaById(Long id) {
        return anggotaRepository.findById(id).orElse(null);
    }

    public AnggotaModel createAnggota(AnggotaModel anggota) {
        return anggotaRepository.save(anggota);
    }

    public void deleteAnggota(Long id) {
        anggotaRepository.deleteById(id);
    }
}
