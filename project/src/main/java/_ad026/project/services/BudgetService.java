package _ad026.project.services;

import _ad026.project.models.Budget;
import _ad026.project.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget createBudget(Budget body) {
        return budgetRepository.save(body);
    }

    public List<Budget> getallBudget() {
        return budgetRepository.findAll();
    }

    public Budget updateBudget(Budget data) {
        return budgetRepository.save(data);
    }

    public Budget getbyid(long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
    }
}