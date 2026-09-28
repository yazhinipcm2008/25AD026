package _ad026.project.services;

import _ad026.project.models.Expense;
import _ad026.project.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public Expense createExpense(Expense data) {
        return expenseRepository.save(data);
    }

    public List<Expense> getallExpense() {
        return expenseRepository.findAll();
    }

    public Expense updateExpense(Expense data) {
        return expenseRepository.save(data);
    }

    public Expense getbyid(long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
    }
}