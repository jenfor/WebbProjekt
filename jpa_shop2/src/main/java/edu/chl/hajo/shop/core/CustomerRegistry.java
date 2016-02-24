package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All customers 
 *
 * @author hajo
 */

@Stateless
public class CustomerRegistry  extends AbstractDAO<Customer, Long> implements ICustomerRegistry {

   // TODO also some coding

        @PersistenceContext
    private EntityManager em;

    public CustomerRegistry() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public List<Customer> getByName(String name) {
        String jpql = "select a from Customer a where a.name=:name";
        return em.createQuery(jpql, Customer.class).
                setParameter("name", name).getResultList();
    }
   


}
