package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@Entity
@Validated
public class Account {
    @Id
    private long id;
    @Positive
    private double totalDebt;
    @PositiveOrZero
    private double currentBalance;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private PawnItem entity;

}
