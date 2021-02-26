package com.iptech.bankingaccount.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.iptech.bankingaccount.domain.Transaction;

@Repository
public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>(100);

    public TransactionRepository () {
            super();
            populateData();
    }

    public List<Transaction> findAll () {
            return transactions
                            .stream()
                            .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private void populateData() {
            try (FileInputStream fileInputStream = new FileInputStream("transactions.txt");
                     ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
                    this.transactions = (List<Transaction>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
            }

    }

    public Transaction findById(Integer transactionId) {
            return transactions.get(transactionId);
    }

}
