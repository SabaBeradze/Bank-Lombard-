package dev.boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
@Getter
@Setter
public class PaymentDTO {
    private long id;
    @PastOrPresent
    private LocalDate dateOfPayment;
    private String personId;
    private long itemId;
    @Positive
    private double amount;

    @Schema(hidden = true)
    public void setId(long id) {
        this.id = id;
    }

    @Schema(hidden = true)
    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
