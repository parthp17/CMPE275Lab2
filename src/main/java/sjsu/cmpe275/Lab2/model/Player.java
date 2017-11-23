package sjsu.cmpe275.Lab2.model;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */


@Entity
@EnableAutoConfiguration
@Table(name="player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(nullable=false)
    private String firstName;
    @Column(nullable=false)
    private String lastName;
    @Column(unique=true, nullable=false)
    private String email;
    private String description;
    @Embedded
    private Address address;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="sponsorId")
    private Sponsor sponsor;
    
    @ManyToMany(fetch = FetchType.EAGER,cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        })
    @JoinTable(name = "opponents",
    joinColumns = {@JoinColumn(name = "player1", referencedColumnName = "id", nullable = false)},
    inverseJoinColumns = {@JoinColumn(name = "player2", referencedColumnName = "id",nullable = false)})
    @JsonIgnoreProperties("opponents")
    private Set<Player> opponents;

	public Player() {
		// TODO Auto-generated constructor stub
	}
    
    public Player(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Required
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Required
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    @Required
    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Set<Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(Set<Player> opponents) {
        this.opponents = opponents;
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
