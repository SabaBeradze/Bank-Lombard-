package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "ID_GEN3", sequenceName = "ID_SEQ3", allocationSize = 1)
@Validated

public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GEN3")
    private long id;
    @PastOrPresent
    private LocalDate dateOfPayment;
    private String personId;
    private long itemId;
    @Positive
    private double amount;


}
