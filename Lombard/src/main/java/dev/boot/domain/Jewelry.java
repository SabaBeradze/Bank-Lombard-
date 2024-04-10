package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Validated

public class Jewelry extends PawnItem {
    @NotNull
    @NotBlank
    private String text;
    private String stones;

}
