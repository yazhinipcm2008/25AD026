package _ad026.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Expense {
    @Id
    @GeneratedValue
    Long Id;
    double Amount;
    String Category;
    LocalDate Date;
    Long UserId;
}