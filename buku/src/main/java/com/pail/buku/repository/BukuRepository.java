package com.pail.buku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pail.buku.model.BukuModel;
import org.springframework.stereotype.Repository;

@Repository
public interface BukuRepository extends JpaRepository<BukuModel, Object>{

}
