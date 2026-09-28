package _ad026.project.controller;

import _ad026.project.models.Budget;
import _ad026.project.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping("/create")
    ResponseEntity<Budget> createBudget(@RequestBody Budget body) {
        return new ResponseEntity<>(
                budgetService.createBudget(body),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/getall")
    ResponseEntity<List<Budget>> getall() {
        return new ResponseEntity<>(
                budgetService.getallBudget(),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    ResponseEntity<Budget> updateBudget(@RequestBody Budget data) {
        return new ResponseEntity<>(
                budgetService.updateBudget(data),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/getbyid/{id}")
    ResponseEntity<?> getbyId(@PathVariable long id) {
        try {
            Budget response = budgetService.getbyid(id);
            return new ResponseEntity<>(
                    response,
                    HttpStatus.OK
            );

        } catch (RuntimeException exception) {
            return new ResponseEntity<>(
                    "not found",
                    HttpStatus.NOT_FOUND
            );
        }
    }
}