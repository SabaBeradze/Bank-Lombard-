package dev.boot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Validated

public class Car extends PawnItem{
    @NotBlank
    @NotNull
    private String model;
    private long releaseYear;
    @Positive
    private long odometry;


}
