
package edu.chl.hajo.shop.persistence;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Base class for all entities, defines id, equal etc
 *   
 * The below implementations can be disputed
 * - Using surrogate or natural keys?
 * - No id before saved to database, problems with equals (storing in Sets)
 * - See https://community.jboss.org/wiki/EqualsAndHashCode
 * 
                  *** Nothing to do here ***

 * @author hajo
 */
@MappedSuperclass
@EqualsAndHashCode( of="id")
public abstract class AbstractEntity implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@Version
    //private Long version;
    
    // Must have default ctor (make it package private)
    protected AbstractEntity() {
    }

    protected AbstractEntity(Long id) {
        this.id = id;
    }

}
