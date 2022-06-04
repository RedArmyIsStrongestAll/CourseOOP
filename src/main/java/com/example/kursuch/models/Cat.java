package com.example.kursuch.models;

import com.example.kursuch.customType.color.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="cats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    /*SEQUENCE sequence_for_cats is been used for this TABLE */

    @Id
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="feline_name")
    private String nameFeline;

    @Column(name="parod")
    private String parod;

    @Column(name="color")
    @Type(type ="com.example.kursuch.customType.color.ColorEnumType")
    private Color color;

    public boolean isEmptyColor(){
        if (this.color != null){
            return false;
        }
        else {
            return true;
        }
    }

}
