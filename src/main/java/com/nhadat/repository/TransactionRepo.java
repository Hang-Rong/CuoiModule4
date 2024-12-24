package com.nhadat.repository;

import com.nhadat.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomer_TenKhachHang( String name);
    Optional<Transaction> findById(Long id);
    Optional<Transaction> findByMaGiaoDich(String maGiaoDich);
}
