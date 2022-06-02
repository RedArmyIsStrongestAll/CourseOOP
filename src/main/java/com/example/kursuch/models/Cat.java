package com.example.kursuch.models;

import com.example.kursuch.otherTools.Color;
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

    @Column(name="color")
    /*@Type()*/
    private Color color;

    public void setColor(String color) {
        this.color = Color.fromString(color);
    }

   /* public void setColor(Color color) {
        this.color = color;
    }*/

    @Column(name="parod")
    private String parod;

    public boolean isEmptyColor(){
        if (this.color != null){
            return false;
        }
        else {
            return true;
        }
    }

    public Cat (long id, String name, String nameFeline, String color, String parod){
        this.id = id;
        this.name = name;
        this.nameFeline = nameFeline;
        this.color = Color.fromString(color);
        this.parod = parod;
    }

}
