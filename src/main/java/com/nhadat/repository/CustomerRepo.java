package com.nhadat.repository;

import com.nhadat.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findByTenKhachHangContaining(String tenKhachHang);
}
