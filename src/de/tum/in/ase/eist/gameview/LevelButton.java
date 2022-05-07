package de.tum.in.ase.eist.gameview;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelButton extends Button {

    public static Button createLevelButton(String text){
        Button b = new Button(text);
        b.setPrefSize(70, 46);
        b.setFont(new Font("Arial", 17));
        b.setStyle("-fx-background-color: #005293; ");
        b.setTextFill(Color.WHITE);
        return b;
    }
}
