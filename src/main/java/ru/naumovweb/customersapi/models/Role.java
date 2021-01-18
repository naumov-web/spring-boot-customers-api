package ru.naumovweb.customersapi.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Simple domain object that represents role.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseModel {

    @Column(name = "name")
    private String name;

    public Role(){}

}
