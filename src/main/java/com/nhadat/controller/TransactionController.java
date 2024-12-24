package com.nhadat.controller;


import com.nhadat.model.Transaction;
import com.nhadat.service.ICustomerService;
import com.nhadat.service.impl.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/new")
    public String showCreateTransactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("services", new String[]{"Đất", "Nhà và đất"});
        return "transaction/create";
    }

    @PostMapping("/new")
    public String createTransaction(@Valid @ModelAttribute Transaction transaction, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("services", new String[]{"Đất", "Nhà và đất"});
            return "transaction/create";
        }


        if (transaction.getNgayGiaoDich().isBefore(LocalDate.now())) {
            result.rejectValue("ngayGiaoDich", "ngayGiaoDich.invalid", "Ngày giao dịch phải lớn hơn ngày hiện tại");
            model.addAttribute("customers", customerService.findAll());
            model.addAttribute("services", new String[]{"Đất", "Nhà và đất"});
            return "transaction/create";
        }

        transactionService.saveTransaction(transaction);
        return "transaction/create";
    }

    @GetMapping("/search")
    public String searchTransactions(@RequestParam("search") String search, Model model) {
        List<Transaction> transactions = transactionService.searchTransactions(search);
        model.addAttribute("transactions", transactions);
        return "transaction/list";
    }

    @GetMapping("/list")
    public String listTransactions(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        return "transaction/list";
    }

    @GetMapping("/detail/{maGiaoDich}")
    public String viewTransactionDetails(@PathVariable("maGiaoDich") String maGiaoDich, Model model) {
        Optional<Transaction> transaction = transactionService.findByMaGiaoDich(maGiaoDich);
        model.addAttribute("transaction", transaction.get());
        return "transaction/detail";
    }


    @GetMapping("/delete/{maGiaoDich}")
    public String deleteTransaction(@PathVariable ("maGiaoDich") String maGiaoDich, Model model){
        Optional<Transaction> transaction = transactionService.findByMaGiaoDich(maGiaoDich);
        transactionService.delete(transaction.get());
        return "redirect:/transactions/list";
    }
}

