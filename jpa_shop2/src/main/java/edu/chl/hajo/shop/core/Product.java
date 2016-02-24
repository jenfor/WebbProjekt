package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

/**
 A Product

 @author hajo
 */

@Entity
/*@Table(name = "PRODUCT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME", "PRICE"})
})*/
public class Product extends AbstractEntity {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private double price;

    public Product() {
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, double price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + name + ", price=" + price + '}';
    }
}
