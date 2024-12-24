package com.nhadat.service.impl;

import com.nhadat.model.Transaction;
import com.nhadat.repository.TransactionRepo;
import com.nhadat.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepo tp;

    @Override
    public Iterable<Transaction> findAll() {
        return tp.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return tp.findById(id);
    }

    @Override
    public void save(Transaction transaction) {
        tp.save(transaction);

    }

    @Override
    public void remove(Long id) {
        tp.deleteById(id);
    }

    public void saveTransaction(Transaction transaction) {
        tp.save(transaction);
    }

    public List<Transaction> searchTransactions(String search) {
        return tp.findByCustomer_TenKhachHang(search);
    }

    public List<Transaction> getAllTransactions() {
        return tp.findAll();
    }

    public Optional<Transaction> findByMaGiaoDich(String maGiaoDich) {
        return tp.findByMaGiaoDich(maGiaoDich);
    }

    public void delete(Transaction transaction) {
        tp.delete(transaction);
    }
}
