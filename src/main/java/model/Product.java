package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product extends AbstractEntity{

    private String name;

    private String  scale;

    private String productDescription;

    private Integer quantityStock;

    private Integer  sellPrice;

    private Integer  isActive=1;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name="productLineId")
    private ProductLine productLine;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderProduct> orderProducts = new HashSet<>();















}
