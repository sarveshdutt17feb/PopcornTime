package com.sarvesh.bookmyshow.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id //pk attr for all the child classes of BaseModel
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increament
    private Long id;
    private Date createdAt;
    private Date lastmodifiedAt;
}
