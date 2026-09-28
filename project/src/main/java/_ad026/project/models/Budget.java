package _ad026.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Budget {
    @Id
    @GeneratedValue
    Long Id;
    double Amount;
    String Month;
    String Category;
    Long UserId;
}