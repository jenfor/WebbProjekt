package edu.chl.hajo.jsfs.ctrl;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.jsfs.view.AddProductBB;
import edu.chl.hajo.jsfs.view.EditProductBB;
import edu.chl.hajo.jsfs.view.DeleteProductBB;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 Used for Add, Edit Delete pages

 @author hajo
 */
@Named("productCtrl")
@RequestScoped
public class ProductCtrl {

    private static final Logger LOG = Logger.getLogger(ProductCtrl.class.getSimpleName());
    private IShop shop;
    private AddProductBB addBB;
    private EditProductBB editBB;
    private DeleteProductBB delBB;
    
   
    public void newProduct() {
        LOG.log(Level.INFO, "Backin bean " + addBB);
        Product p = new Product(addBB.getName(), Double.valueOf(addBB.getPrice()));
        shop.getProductCatalogue().create(p);
        List<Product> ps = shop.getProductCatalogue().getByName(addBB.getName());      
        LOG.log(Level.INFO, "New value " + ps.get(0));
    }

    public void updateProduct() {
       Product p = new Product(editBB.getId(), editBB.getName(), Double.valueOf(editBB.getPrice()));
       shop.getProductCatalogue().update(p);    
    }

    public void deleteProduct() {
       Long id = delBB.getId();
       shop.getProductCatalogue().delete(id);
    }

    @Inject
    public void setAddBB(AddProductBB addBB) {
        this.addBB = addBB;
    }
    
    @Inject
    public void setEditBB(EditProductBB editBB) {
        this.editBB = editBB;
    }
    
    @Inject
    public void setDelBB(DeleteProductBB delBB) {
        this.delBB = delBB;
    }

    @Inject
    public void setShop(SingletonShop ss) {
        this.shop = ss.getShop();
    }

}
