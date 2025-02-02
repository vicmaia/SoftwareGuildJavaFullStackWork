package com.sg.blog.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ward
 */
public class Blog {

    private long blogId;
    @NotEmpty(message = "You must supply a value for First Name.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String firstName;
    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 50, message = "Last Name must be no more than 50 characters in length.")
    private String lastName;
    @NotEmpty(message = "You must supply a value for Company.")
    @Length(max = 50, message = "Company must be no more than 50 characters in length.")
    private String company;
    @NotEmpty(message = "You must supply a value for Phone.")
    @Length(max = 10, message = "Phone must be no more than 10 characters in length.")
    private String phone;
    @Email(message = "Please enter a valid email post.")
    @Length(max = 50, message = "Email must be no more than 50 characters in length.")
    private String email;

    public long getBlogId() {
        return blogId;
    }

    public void setBlogId(long blogId) {
        this.blogId = blogId;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.blogId ^ (this.blogId >>> 32));
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + Objects.hashCode(this.company);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.blogId != other.blogId) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.company, other.company)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

}
