package ru.naumovweb.customersapi.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Simple domain object that represents customer.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@Entity
@Table(name = "customers")
@Data
public class Customer extends BaseModel {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Customer(){}
}
