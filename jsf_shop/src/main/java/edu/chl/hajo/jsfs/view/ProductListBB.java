package edu.chl.hajo.jsfs.view;

import edu.chl.hajo.jsfs.core.SingletonShop;
import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Product;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 Backing bean for the productList.xhtml

        *** NOTHING to do here ***


 @author hajo
 */
@Named("productList")
@ViewScoped
public class ProductListBB implements Serializable {

    private static final Logger LOG = Logger.getLogger(ProductListBB.class.getName());

    private transient IShop shop;
    private int currentPage;
    private int pageSize = 4;  // Items on a listing (hard coded :-(  )
    private int count;

    // Must have default ctor so use setter injection
    @Inject
    public void setShop(SingletonShop ss) {
        this.shop = ss.getShop();
    }

    public List<Product> findRange() {
        return shop.getProductCatalogue().findRange(currentPage * pageSize, pageSize);
    }

    @PostConstruct
    public void post() {
        count = shop.getProductCatalogue().count();
    }

    public void next() {
        if (currentPage < (count / pageSize)) {
            currentPage = currentPage + 1;
        }
    }

    public void prev() {
        if (currentPage > 0) {
            currentPage = currentPage - 1;
        }
    }

}
