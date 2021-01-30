package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "productLines")
@Data

public class ProductLine extends AbstractEntity{

    private String description;

    @OneToMany(mappedBy = "productLine")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Product> products = new HashSet<>();




}
