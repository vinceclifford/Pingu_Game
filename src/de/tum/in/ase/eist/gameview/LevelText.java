package de.tum.in.ase.eist.gameview;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class LevelText {

    public static Label createLevelText(String text){
        Label l = new Label(text);
        l.setWrapText(true);
        l.setFont(new Font("Arial",16));
        l.setPadding(new Insets(3,10,0,10));
        return l;
    }
}
