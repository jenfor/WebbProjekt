
package edu.chl.hajo.jsfs.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Named("editProduct")
@RequestScoped 
public class EditProductBB {

    
    @Getter
    @Setter
    private Long id;
    @Setter
    @Getter
    @Size(min = 4, max = 20, message = "{product.name}")
    private String name;
    @Setter
    @Getter
    // Hopeless to validate numbers (?!?!) because, user possibly enters non-digits
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "{product.price}")
    private String price;

    @Override
    public String toString() {
        return "EditProductBB{" + "name=" + name + ", price=" + price + '}';
    }  
}
