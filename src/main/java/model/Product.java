package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import model.OrderProduct;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
public class Product extends AbstractEntity {

    private String name;

    private String scale;

    private String productDescription;

    private Integer quantityStock;

    private Integer sellPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @JoinColumn(name = "productLineId")
    private ProductLine productLine;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<OrderProduct> orderProducts = new HashSet<>();

    public String printToConsole() {
        return "Emri produktit: " + this.name
                + "\n Description: " + this.productDescription
                + "\n Menyra Matjes :" + this.scale
                + "\n  Cmimi : " + this.sellPrice
                + "\n Sasia ne stok : " + this.quantityStock;
    }

}
