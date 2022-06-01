package com.example.kursuch.models;

import com.example.kursuch.otherTools.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Color color;

    public boolean isEmptyColor(){
        if (this.color != null){
            return false;
        }
        else {
            return true;
        }
    }

    @Column(name="parod")
    private String parod;

}
