package ru.naumovweb.customersapi.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Simple domain object that represents user.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@Entity
@Table(name = "users")
@Data
public class User extends BaseModel {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

}
