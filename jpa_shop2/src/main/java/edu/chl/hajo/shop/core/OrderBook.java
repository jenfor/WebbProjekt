package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * All orders 
 *
 * @author hajo
 */

//@Stateless
public class OrderBook extends AbstractDAO<PurchaseOrder, Long> {
        // TODO 

    @PersistenceContext
    private EntityManager em;

    public OrderBook() {
        super(PurchaseOrder.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
