package com.bastien.serpent.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private Integer classement;

    @Override
    public String toString() {
        return "Player{" +
                "id= " + id +
                ", name= " + name +
                ", rank= " + classement + "}";
    }
}
