package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.core.Shop;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testing the persistence layer
 *
 * NOTE NOTE NOTE: JavaDB (Derby) must be running (not using an embedded
 * database) GlassFish not needed using Arquillian
 *
 * @author hajo
 */
@RunWith(Arquillian.class)
public class TestShopPersistence {

    @Inject
    Shop shop;

    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "shop.war")
                // Add all classes
                .addPackage("edu.chl.hajo.shop.core")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

    }

    @Before  // Run before each test
    public void before() throws Exception {
        clearAll();
    }
    

    @Test
    public void testPersistAProduct() throws Exception {
        Product p = new Product("aaa", 999);
        shop.getProductCatalogue().create(p);
        List<Product> ps = shop.getProductCatalogue().findAll();
        assertTrue(ps.size() > 0);
        assertTrue(ps.get(0).getName().equals(p.getName()));
    }
    
    @Test
    public void testDeleteProduct() throws Exception {
        Product product1 = new Product("product", 111);
        shop.getProductCatalogue().create(product1);
        
        shop.getProductCatalogue().delete(product1.getId());
        assertTrue( shop.getProductCatalogue().findAll().isEmpty());
    }
    
    @Test
    public void testUpdateProduct() throws Exception {
        Product product1 = new Product("product", 111);
        shop.getProductCatalogue().create(product1);

        product1.setName("update");
        shop.getProductCatalogue().update(product1);

        Product book2 = shop.getProductCatalogue().find(product1.getId());
        assertTrue(book2.getName().equals(product1.getName()));
    }
    
    
    @Test
    public void find() throws Exception {
        Product p = new Product("aaa", 999);
        shop.getProductCatalogue().create(p);
        Product found = shop.getProductCatalogue().find(p.getId());
        assertTrue(found.getId().equals(p.getId()));
    }
    @Test
    public void findAll() throws Exception {
        List<Product> createdProducts = new ArrayList<>();
        String[] names = "aaa, bbb, ccc, ddd, eee, fff, ggg, hhh".split(",");
        for (String s : names) {
            Product product = new Product(s, 10);
            createdProducts.add(product);
            shop.getProductCatalogue().create(product);
        }
        
        List<Product> foundProducts = shop.getProductCatalogue().findAll();
        
        for (Product p : foundProducts) {
            assertTrue(createdProducts.contains(p));
        }
    }

    
    @Test
    public void testProductGetByName() throws Exception {
        Product p = new Product("aaa", 999);
        shop.getProductCatalogue().create(p);
        List<Product> ps = shop.getProductCatalogue().getByName("aaa");
        assertTrue(ps.size() > 0);
        assertTrue(ps.get(0).getName().equals(p.getName()));
    }
    
    @Test
    public void testFindRange() throws Exception {
        String[] names = "aaa, bbb, ccc, ddd, eee, fff, ggg, hhh".split(",");
        for (String s : names) {
            Product product = new Product(s, 5);
            shop.getProductCatalogue().create(product);
        }
        List<Product> products = shop.getProductCatalogue().findAll();
        assertTrue(products.size() == names.length);

        products = shop.getProductCatalogue().findRange(2, 2);
        assertTrue(products.get(0).getName().equals(names[2]));

    }

    @Test
    public void testCount() throws Exception {
        String[] names = "aaa, bbb, ccc, ddd, eee, fff, ggg, hhh".split(",");
        for (String s : names) {
            Product product = new Product(s, 10);
            shop.getProductCatalogue().create(product);
        }
        int count = shop.getProductCatalogue().count();
        assertTrue(count == names.length);
    }
    

    // Need a standalone em to remove testdata between tests
    // No em accessible from interfaces
    @PersistenceContext(unitName = "jpa_shop_pu")
    @Produces
    @Default
    EntityManager em;

    // Order matters
    private void clearAll() throws Exception {  
        utx.begin();  
        em.joinTransaction();
//      em.createQuery("delete from PurchaseOrder").executeUpdate();
//      em.createQuery("delete from Customer").executeUpdate();
        em.createQuery("delete from Product").executeUpdate();
        utx.commit();
    }

}
