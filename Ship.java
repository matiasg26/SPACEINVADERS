package spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.FileInputStream;

public class Ship {
    private Pane mainPane;
    private Rectangle rect;
    private Image image;
    private ImageView imageView;
    public Ship(Pane mainPane) {
        this.mainPane = mainPane;
        this.image = new javafx.scene.image.Image("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/391aa854-ba50-427b-a8ab-68392d1af18f/dbzzd87-2c70f8ee-41ec-421e-b8f7-9ce9dfbd5a3d.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzM5MWFhODU0LWJhNTAtNDI3Yi1hOGFiLTY4MzkyZDFhZjE4ZlwvZGJ6emQ4Ny0yYzcwZjhlZS00MWVjLTQyMWUtYjhmNy05Y2U5ZGZiZDVhM2QucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.puxCmlinCrCWdSBk7Lo3k6yFh4rjlzctLf7WfyEZ_5w");

        //Setting the image view
        this.imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(450);
        imageView.setY(600);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        this.mainPane.getChildren().add(imageView);
    }
    public void setShipX(int x) {
        this.imageView.setX(x);
    }
    public void setShipY(int y) {
        this.imageView.setY(y);
    }
    public int getShipX() {
        return (int) this.imageView.getX();
    }
    public int getShipY() {
        return (int) this.imageView.getY();
    }
}
