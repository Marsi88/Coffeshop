package model;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "orderProducts")
@Data
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProductId;

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
