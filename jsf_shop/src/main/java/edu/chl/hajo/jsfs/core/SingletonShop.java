
package edu.chl.hajo.jsfs.core;

import edu.chl.hajo.shop.core.IShop;
import edu.chl.hajo.shop.core.Shop;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import lombok.Getter;


/**
 * A wrapper for the shop (will live in application scope)
 * @author hajo

   *** Nothing to do here *** 
 */
/*@ApplicationScoped 
public class SingletonShop implements Serializable {

    private final IShop shop = Shop.newInstance();

    public IShop getShop() {
        return shop;
    }
    
   

}*/
 @ApplicationScoped 
    public class SingletonShop { 
        @Getter 
        private IShop shop; 
        
        
        @PostConstruct 
        private void init() { 
            shop = Shop.newInstance(); 
        } 
    }
