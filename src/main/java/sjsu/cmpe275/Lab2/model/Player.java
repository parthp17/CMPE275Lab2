package sjsu.cmpe275.Lab2.model;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by kemy on 11/13/17.
 */
@Entity
@Table(name="player")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String description;
//    private Address address;
    private Sponsor sponsor;

    @OneToMany
    private List<Player> opponents;

    public Player() {}

    public Player(long id, String firstName, String lastName, String email, String description, Sponsor sponsor, List<Player> opponents) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.description = description;
        this.sponsor = sponsor;
        this.opponents = opponents;
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

    public List<Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }
}
