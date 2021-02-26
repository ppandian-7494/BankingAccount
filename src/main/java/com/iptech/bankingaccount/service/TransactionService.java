package com.iptech.bankingaccount.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iptech.bankingaccount.domain.Transaction;
import com.iptech.bankingaccount.repository.TransactionRepository;
import com.sun.el.stream.Optional;

@Service
public class TransactionService {
	 @Autowired
     private TransactionRepository transactionRepo;

     public List<Transaction> findAll() {
             List<Transaction> transactions =  transactionRepo.findAll();
             Collections.sort(transactions, (transaction1, transaction2) -> transaction1.getDate().compareTo(transaction2.getDate()));
             return transactions;

     }

     public Transaction findById(Long transactionId) {
             java.util.Optional<Transaction> transactionOpt = transactionRepo.findAll()
                               .stream()
                               .filter(transaction -> transaction.getId().equals(transactionId))
                               .findAny();
             return transactionOpt.orElse(new Transaction());
     }

}
