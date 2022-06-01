package com.example.kursuch.otherTools;

import lombok.Getter;

import java.util.Optional;

public enum Color {
    RED ("красный"),
    ORANGE ("красный"),
    YELLOW ("красный"),
    GREEN ("красный"),
    BLUE ("красный"),
    PURPLE ("красный"),
    WHITE ("красный"),
    BLACK ("красный");

    @Getter
    private String title;

    Color (String title){
        this.title = title;
    }
}
