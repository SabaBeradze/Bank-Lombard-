package dev.boot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@Validated

public class Technology extends PawnItem{

    @NotBlank
    @NotNull
    private String producerName;
    @NotBlank
    @NotNull
    private String typeOfTech;
    private String isDamage;
    @Basic
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TeachType teachType;
}
