package _ad026.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
<<<<<<< HEAD
=======
import jakarta.persistence.GenerationType;
>>>>>>> ac60b32 (13:00)
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Expense {
<<<<<<< HEAD
    @Id
    @GeneratedValue
    Long Id;
    double Amount;
    String Category;
    LocalDate Date;
    Long UserId;
=======

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String category;

    private LocalDate date;

    private Long userId;
>>>>>>> ac60b32 (13:00)
}