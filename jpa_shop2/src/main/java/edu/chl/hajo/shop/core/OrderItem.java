package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 A single row in an Order

 @author hajo
 */

//@Entity
//@Table(name = "ORDERITEM", uniqueConstraints = {
 //   @UniqueConstraint(columnNames = {"PRODUCT", "QUANTITY"})
//})
public class OrderItem extends AbstractEntity {

    @Setter
    @Getter

    private Product product;
    @Setter
    @Getter

    private int quantity;

    public OrderItem() {
    }

    public OrderItem(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public OrderItem(Long id, Product product, int quantity) {
        super(id);
        this.quantity = quantity;
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "product=" + product + ", quantity=" + quantity + '}';
    }
}
