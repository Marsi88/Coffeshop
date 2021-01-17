package model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productLines")
@Data

public class ProductLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductLines;

    private String description;

    @OneToMany(mappedBy = "productLine")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();




}
