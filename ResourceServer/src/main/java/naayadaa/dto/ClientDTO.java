package naayadaa.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by AnastasiiaDepenchuk on 27-Apr-18.
 */
public class ClientDTO {

    private Integer id;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Wrong first name")
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Wrong last name")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Wrong email")
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
