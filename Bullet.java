package spaceinvaders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullet {
    private javafx.scene.shape.Rectangle bullet;
    private Pane mainPane;
    private Alien spaceInvader;
    public Bullet(int x, int y, Pane mainPane,Alien spaceInvader) {
        this.spaceInvader = spaceInvader;
        this.bullet = new Rectangle(x,y,5,20);
        this.bullet.setFill(Color.WHITE);
        this.mainPane = mainPane;
        this.mainPane.getChildren().add(bullet);
    }
    public void checkIntersections() {
        if(spaceInvader.getAlien().intersects(this.getBounds())){
            this.spaceInvader.removeAlien();
            this.removeBullet();
        }
    }
    public Bounds getBounds() {
        return bullet.getLayoutBounds();
    }
    public Rectangle getBullet() {
        return bullet;
    }
    public void removeBullet(){
        this.mainPane.getChildren().remove(bullet);
        this.bullet = null;
    }
    public int getBulletX(){
        return (int) this.bullet.getX();
    }
    public int getBulletY(){
            return (int) this.bullet.getY();
    }
    public void setBulletX(int x) {
        this.bullet.setX(x);
    }
    public void setBulletY(int y) {
            this.bullet.setY(y);
    }
}
