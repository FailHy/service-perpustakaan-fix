package com.pail.peminjaman.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import com.pail.peminjaman.model.PeminjamanModel;
import com.pail.peminjaman.repository.PeminjamanRepository;
import com.pail.peminjaman.vo.Anggota;
import com.pail.peminjaman.vo.Buku;
import com.pail.peminjaman.vo.ResponseTemplate;

@Service
public class PeminjamanService {
    @Autowired
    private DiscoveryClient discoveryClient;

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
        
        ServiceInstance anggotaInstance = discoveryClient.getInstances("ANGGOTA").get(0);
        Anggota anggota = restTemplate.getForObject(
                anggotaInstance.getUri() + "/api/anggota/" + peminjaman.getAnggotaId(),
                Anggota.class
        );
        
        ServiceInstance bukuInstance = discoveryClient.getInstances("BUKU").get(0);
        Buku buku = restTemplate.getForObject(
            bukuInstance.getUri() + "/api/buku/" + peminjaman.getBukuId(),
        Buku.class
        );
        
        ResponseTemplate vo = new ResponseTemplate();
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);

        responseList.add(vo);
        return responseList;
    }
}