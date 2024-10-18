package com.xanaxna.Rest.API.example.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                '}';
    }

    @Column(unique = true)
    private String name;

    private int age;


    private int weight;


    public Cat( String name, int age, int weight) {

        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public Cat() {
    }
}
