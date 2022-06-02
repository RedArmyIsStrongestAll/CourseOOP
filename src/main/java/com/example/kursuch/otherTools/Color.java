package com.example.kursuch.otherTools;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Color {

    RED ("красный"),

    ORANGE ("оранжевый"),

    YELLOW ("жёлтый"),

    GREEN ("зелёный"),

    BLUE ("синий"),

    PURPLE ("фиолетовый"),

    WHITE ("белый"),
    BLACK ("чёрный"),

    NO ("");

    @Getter
    private String title;

    Color (String title){
        this.title = title;
    }

    public static Color fromString(String colorTitle){

        Color [] arrColor = Color.values();
        Color findColor = null;

        for(Color x : arrColor){
            if (colorTitle.equals(x.title)) {
                findColor = x;
            }
        }

        if (findColor == null){
            return Color.NO;
        }
        else{
            return findColor;
        }
    }
}
