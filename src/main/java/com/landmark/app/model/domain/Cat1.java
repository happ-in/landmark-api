package com.landmark.app.model.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "CAT1")
public class Cat1 {

    @Id
    private String code;

    private String name;

}
