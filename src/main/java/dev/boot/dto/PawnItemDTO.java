package dev.boot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.boot.domain.Branch;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter
@Setter
public class PawnItemDTO {

    private long id;
    private String personId;
    @PastOrPresent
    private LocalDate date;
    @JsonIgnore
    private Branch branch;
    @PositiveOrZero
    private double amountReceived;
    @PositiveOrZero
    private double amountInMonth;
    private boolean isConfiscated;
    private boolean isTaken;

    @Schema(hidden = true)
    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public void setConfiscated(boolean confiscated) {
        isConfiscated = confiscated;
    }

    @JsonIgnore
    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}
