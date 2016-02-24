package edu.chl.hajo.shop.core;

import java.io.Serializable;

/**
 * An Address not an Entity
 * @author hajo
 */
public class Address implements Serializable {

    private String street;
    private int nbr;
    private String town;

    public Address() {
    }

    public Address(String street, int nbr, String town) {
        this.street = street;
        this.nbr = nbr;
        this.town = town;
    }

    public int getNbr() {
        return nbr;
    }

    public String getStreet() {
        return street;
    }

    public String getTown() {
        return town;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public void setTown(String town) {
        this.town = town;
    }
    
    @Override
    public String toString() {
        return "Address{" + "street=" + street + ", nbr=" + nbr + ", town=" + town + '}';
    }
    
    
}
