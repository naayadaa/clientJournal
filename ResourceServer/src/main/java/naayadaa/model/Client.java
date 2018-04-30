package naayadaa.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstname", columnDefinition = "VARCHAR(255)", nullable = false)
    private String firstName;

    @Column(name = "lastname", columnDefinition = "VARCHAR(255)", nullable = false)
    private String lastName;

    @Column(name = "email", columnDefinition = "VARCHAR(255)", nullable = false, unique = true)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
