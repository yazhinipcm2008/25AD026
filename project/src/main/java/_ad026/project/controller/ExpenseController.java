package _ad026.project.controller;

import _ad026.project.models.Expense;
import _ad026.project.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // req body
    @PostMapping("/create")
    ResponseEntity<Expense> createExpense(@RequestBody Expense body) {

        return new ResponseEntity<>(
                expenseService.createExpense(body),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/getall")
    ResponseEntity<List<Expense>> getall() {

        return new ResponseEntity<>(
                expenseService.getallExpense(),
                HttpStatus.OK
        );
    }

    @PutMapping("/update")
    ResponseEntity<Expense> updateExpense(@RequestBody Expense data) {

        return new ResponseEntity<>(
                expenseService.updateExpense(data),
                HttpStatus.ACCEPTED
        );
    }

    // path variable
    @GetMapping("/getbyid/{id}")
    ResponseEntity<?> getbyId(@PathVariable long id) {

        try {

            Expense response = expenseService.getbyid(id);

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