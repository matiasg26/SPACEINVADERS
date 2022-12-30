package spaceinvaders;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.awt.*;

public class PaneOrganizer {
    private Pane mainPane;
    private BorderPane root;
    private javafx.scene.image.Image img;
    private Pane labelPane;
    private Game game;
    private Label gameStartLabel;
    public PaneOrganizer() {
        this.mainPane = new Pane();
        this.root = new BorderPane();
        this.game = new Game(mainPane);
        this.root.getChildren().addAll(mainPane,this.game.getLabelPane());
        img = new Image("https://img4.goodfon.com/wallpaper/nbig/c/82/kosmos-art-pikseli-8bit-planeta-zvezdy-retro-grafika.jpg");
        BackgroundImage bImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);

    }
    public Pane getRoot() {
        return this.root;
    }

}
