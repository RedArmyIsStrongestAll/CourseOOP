package com.example.kursuch.models;

import com.example.kursuch.customType.color.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name="cats")
@Data
@NoArgsConstructor
public class Cat {

    @Id
    @SequenceGenerator(name = "cats", sequenceName = "sequence_for_cats", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cats")
    private long id;

    @Column(name="name")
    @Type(type = "com.example.kursuch.customType.StringFillingNullType")
    private String name;

    @Column(name="feline_name")
    @Type(type = "com.example.kursuch.customType.StringFillingNullType")
    private String nameFeline;

    @Column(name="parod")
    @Type(type = "com.example.kursuch.customType.StringFillingNullType")
    private String parod;

    @Column(name="color")
    @Type(type = "com.example.kursuch.customType.color.ColorType")
    private Color color;

    public Cat(long id, String name, String nameFeline, Color color, String parod){
        this.name = name;
        this.nameFeline = nameFeline;
        this.color = color;
        this.parod = parod;
    }
}
