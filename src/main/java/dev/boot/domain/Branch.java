package dev.boot.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(uniqueConstraints =@UniqueConstraint(columnNames = "name"))
@SequenceGenerator(name = "ID_GEN11", sequenceName = "ID_SEQ11", allocationSize = 1)
@NoArgsConstructor
@Validated

public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_GEN11")
    private  long id;
    @NotBlank
    private  String name;
    @OneToMany(mappedBy = "branch")
    private Set<@NotNull PawnItem> items=new HashSet<>();


}
