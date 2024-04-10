package dev.boot.dto;


import dev.boot.domain.PawnItem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Validated
public class BranchDTO {
    private long id;
    @NotBlank
    private String name;
    private Set<@Valid PawnItem> items;

    @Schema(hidden = true)
    public void setId(Long id) {
        this.id = id;
    }


    @Schema(hidden = true)
    public void setItems(Set<PawnItem> items) {
        this.items = items;
    }


}
