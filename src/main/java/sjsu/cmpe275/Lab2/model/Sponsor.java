package sjsu.cmpe275.Lab2.model;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.Serializable;

/**
 * Created by kemy on 11/13/17.
 */
@Entity
@EnableAutoConfiguration
public class Sponsor implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    
    private String name;
    private String description;
    @Embedded
    private Address address;
    
    public Sponsor() {}
    
    public Sponsor(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
