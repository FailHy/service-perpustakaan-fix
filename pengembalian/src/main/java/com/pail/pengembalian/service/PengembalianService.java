package com.pail.pengembalian.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pail.pengembalian.model.PengembalianModel;
import com.pail.pengembalian.repository.PengembalianRepository;
import com.pail.pengembalian.vo.Peminjaman;
import com.pail.pengembalian.vo.Anggota;
import com.pail.pengembalian.vo.Buku;
import com.pail.pengembalian.vo.ResponseTemplate;

@Service
public class PengembalianService {
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
    private PengembalianRepository pengembalianRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<PengembalianModel> getAllPengembalian() {
        return pengembalianRepository.findAll();
    }

    public PengembalianModel getPengembalianById(Long id) {
        return pengembalianRepository.findById(id).orElse(null);
    }

    public PengembalianModel createPengembalian(PengembalianModel pengembalian) {
        return pengembalianRepository.save(pengembalian);
    }

    public void deletePengembalian(Long id) {
        pengembalianRepository.deleteById(id);
    }
    public List<ResponseTemplate> getPengembalianWithPeminjamanById(Long id){
        List<ResponseTemplate> responseList = new ArrayList<>();
        PengembalianModel pengembalian = getPengembalianById(id);

        ServiceInstance peminjamanInstance = discoveryClient.getInstances("PEMINJAMAN").get(0);
        Peminjaman peminjaman = restTemplate.getForObject(
                peminjamanInstance.getUri() + "/api/peminjaman/" + pengembalian.getPeminjamanId(),
                Peminjaman.class
        );

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
        
        vo.setPengembalian(pengembalian);
        vo.setPeminjaman(peminjaman);
        vo.setAnggota(anggota);
        vo.setBuku(buku);
        
        responseList.add(vo);
        return responseList;
    }
}