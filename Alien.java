package spaceinvaders;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Alien {
    private Pane mainPane;
    private Image alienImage;
    private ImageView alienImageView;
    private Rectangle rect;
    public Alien (Pane mainPane) {
        this.mainPane = mainPane;
        this.alienImage = new javafx.scene.image.Image("https://www.freepngimg.com/thumb/categories/1163.png");
        //Setting the image view
        this.alienImageView = new ImageView(alienImage);
        this.rect = new Rectangle(460,200,50,50);
        //Setting the position of the image
        alienImageView.setX(460);
        alienImageView.setY(200);
        alienImageView.setFitHeight(50);
        alienImageView.setFitWidth(50);
        alienImageView.setPreserveRatio(true);
        this.mainPane.getChildren().add(alienImageView);
    }
    public void removeAlien() {
        this.mainPane.getChildren().remove(alienImageView);
        this.alienImageView=null;
        this.rect = null;
    }
    public Rectangle getAlien(){
        return this.rect;
    }
    public void setShipX(int x) {
        this.alienImageView.setX(x);
    }
    public void setShipY(int y) {
        this.alienImageView.setY(y);
    }
    public int getShipX() {
        return (int) this.alienImageView.getX();
    }
    public int getShipY() {
        return (int) this.alienImageView.getY();
    }
}
