package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All products (stateless EJB)
 *
 * @author hajo
 */

@Stateless
public class ProductCatalogue extends AbstractDAO<Product, Long> implements IProductCatalogue {
        // TODO 

    @PersistenceContext
    private EntityManager em;

    public ProductCatalogue() {
        super(Product.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    
   /* public List<Product> getAllProducts() {
        String jpql = "select * from Products";
        return em.createQuery(jpql, Product.class)
                .setParameter("product", product).getResultList();   
    }*/

    @Override
    public List<Product> getByName(String name) {
        String jpql = "select p from Product p where p.name=:name";
        return em.createQuery(jpql, Product.class).
                setParameter("name", name).getResultList();
    }
    

   

}
