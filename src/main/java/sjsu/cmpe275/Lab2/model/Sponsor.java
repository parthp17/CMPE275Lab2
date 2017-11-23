package sjsu.cmpe275.Lab2.model;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.Serializable;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
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
