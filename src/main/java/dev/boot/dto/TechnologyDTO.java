package dev.boot.dto;

import dev.boot.domain.TeachType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
public class TechnologyDTO  extends PawnItemDTO {
    private String producerName;
    private String typeOfTech;
    private String isDamage;
    private TeachType teachType;



}
