package ru.naumovweb.customersapi.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.naumovweb.customersapi.enums.StatusesEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Base class with properties 'id', 'createdAt', 'updatedAt', 'status'.
 * Used as a base class for all objects that requires this property.
 *
 * @author Naumov Konstantin
 * @version 1.0
 */

@MappedSuperclass
@Data
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created_at")
    private Date created_at;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updated_at;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusesEnum status;
}
