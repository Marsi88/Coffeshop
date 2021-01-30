package model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "orderProducts")
@Data
public class OrderProduct extends AbstractEntity{

    private Integer quantity;

    private Integer price;

    @ManyToOne (fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name="productId")
    private Product product;

    @ManyToOne (fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JoinColumn(name="orderId")
    private Order order;







}
