package dev.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO extends PawnItemDTO {
    private String model;
    private long releaseYear;
    private long odometry;




}
