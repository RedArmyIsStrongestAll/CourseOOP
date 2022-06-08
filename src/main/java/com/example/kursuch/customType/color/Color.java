package com.example.kursuch.customType.color;

import lombok.Getter;

public enum Color {


    RED ("красный"),

    ORANGE ("оранжевый"),

    YELLOW ("жёлтый"),

    GREEN ("зелёный"),

    BLUE ("синий"),

    PURPLE ("фиолетовый"),

    WHITE ("белый"),

    BLACK ("чёрный"),

    NO ("неизвестно");

    Color (String title){
        this.title = title;
    }

    @Getter
    private String title;

    public static Color [] arrColor = Color.values();


    public static Color fromString(String colorTitle){

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
