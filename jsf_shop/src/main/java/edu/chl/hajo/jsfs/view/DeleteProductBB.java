
package edu.chl.hajo.jsfs.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
         Backing bean

         *** Nothing to do here ***
          
 * @author hajo
 */
@Named("deleteProduct")
@RequestScoped 
public class DeleteProductBB {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @Size(min = 4, max = 20, message = "{product.name}")
    private String name;
    @Getter
    @Setter
    // Hopeless to validate numbers (?!?!) because, user possibly enters non-digits
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "{product.price}")
    private String price;

    @Override
    public String toString() {
        return "DeleteProductBB{" + "name=" + name + ", price=" + price + '}';
    }  
}
