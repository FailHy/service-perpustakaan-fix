package com.pail.peminjaman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pail.peminjaman.model.PeminjamanModel;
import com.pail.peminjaman.repository.PeminjamanRepository;
import com.pail.peminjaman.vo.Anggota;
import com.pail.peminjaman.vo.Buku;
import com.pail.peminjaman.vo.ResponseTemplate;

@Service
public class PeminjamanService {
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<PeminjamanModel> getAllPeminjaman() {
        return peminjamanRepository.findAll();
    }

    public PeminjamanModel getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id).orElse(null);
    }

    public PeminjamanModel createPeminjaman(PeminjamanModel peminjaman) {
        return peminjamanRepository.save(peminjaman);
    }

    public void deletePeminjaman(Long id) {
        peminjamanRepository.deleteById(id);
    }
    public List<ResponseTemplate> getPeminjamanWithAnggotaById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        PeminjamanModel peminjaman = getPeminjamanById(id);
        Anggota anggota = restTemplate.getForObject("http://localhost:8081/api/anggota/"
                + peminjaman.getAnggotaId(), Anggota.class);
        Buku buku = restTemplate.getForObject("http://localhost:8082/api/buku/"
                + peminjaman.getBukuId(), Buku.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);
        responseList.add(vo);
        return responseList;
    }
}
