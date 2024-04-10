package dev.boot.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "ID_GEN2", sequenceName = "ID_SEQ2", allocationSize = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Validated

public class PawnItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GEN2")
    private long id;
    private String personId;
    @PastOrPresent
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
    @Positive
    private double amountReceived;
    @Positive
    private double amountInMonth;
    private boolean isConfiscated;
    private boolean isTaken;
    @PastOrPresent
    private LocalDate dateOfEvent;//this stores isConfiscated or isTaken exactly when

    public Account createAccount() {
        Account account = new Account();
        account.setEntity(this);
        account.setCurrentBalance(0.);
        account.setTotalDebt(amountReceived);
        return account;
    }

}
