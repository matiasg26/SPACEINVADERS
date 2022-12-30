package spaceinvaders;

import doodlejump.Platform;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static doodlejump.Constants.REBOUND_VELOCITY;

public class Game {
    private Ship mainShip;
    private Pane mainPane;
    private Timeline moveTimeline;
    private Bullet bullet;
    private Timeline shootTimeline;
    private Timeline gameTimeline;
    private int counter;
    private Pane labelPane;
    private Label gameStartLabel;
    private Timeline labelTimeline;
    private Timeline leftLabelTimeline;
    private Timeline rightLabelTimeline;
    private Alien spaceInvader;
    public Boolean trigger;
    public Game(Pane mainPane) {
        counter= 0;
        trigger = false;
        this.labelPane = new Pane();
        this.startGame();
        this.moveTimeline = new Timeline();
        this.shootTimeline = new Timeline();
        this.gameTimeline = new Timeline();
        this.mainPane = mainPane;
        this.spaceInvader = new Alien(mainPane);
        this.mainShip = new Ship(mainPane);
        this.mainPane.setOnKeyPressed((KeyEvent e) -> this.handleKeyPress(e));
        this.mainPane.setFocusTraversable(true);
    }

    /*public Bounds getBounds() {
        return this.spaceInvader.getAlien().getLayoutBounds();
    }*/
    public Pane getLabelPane() {
        return this.labelPane;
    }
    private void startGame() {
        this.gameStartLabel = new Label();
        this.gameStartLabel.setText("Press SPACE to start!");
        this.gameStartLabel.setTextFill(Color.PINK);
        this.gameStartLabel.setLayoutX(220);
        this.gameStartLabel.setLayoutY(300);
        this.setUpLabel();
        this.labelPane.getChildren().add(gameStartLabel);
        this.labelTimeline();
    }
    private void stopTimelines() {
        this.labelPane.getChildren().remove(gameStartLabel);
        this.gameStartLabel=null;
        this.rightLabelTimeline.stop();
        this.leftLabelTimeline.stop();
        this.labelTimeline.stop();
    }
    private void rightLabelTimeline(){
        KeyFrame gf = new KeyFrame(Duration.seconds(.025), (f) -> {
            gameStartLabel.setLayoutX(gameStartLabel.getLayoutX()+10);
            if (gameStartLabel.getLayoutX() == 420) {
                this.leftLabelTimeline();
                this.rightLabelTimeline.stop();
            }
        });
        this.rightLabelTimeline = new Timeline(gf);
        this.rightLabelTimeline.setCycleCount(Animation.INDEFINITE);
        this.rightLabelTimeline.play();
    }

    private void leftLabelTimeline() {
        KeyFrame gf = new KeyFrame(Duration.seconds(.025), (f) -> {
            gameStartLabel.setLayoutX(gameStartLabel.getLayoutX()-10);
            if (gameStartLabel.getLayoutX() == 20) {
                this.rightLabelTimeline();
                this.leftLabelTimeline.stop();
            }
        });
        this.leftLabelTimeline = new Timeline(gf);
        this.leftLabelTimeline.setCycleCount(Animation.INDEFINITE);
        this.leftLabelTimeline.play();
    }
    private void labelTimeline() {
        KeyFrame gf = new KeyFrame(Duration.seconds(.025), (f) -> {
            gameStartLabel.setLayoutX(gameStartLabel.getLayoutX()+10);
            if(gameStartLabel.getLayoutX() == 420){
                this.leftLabelTimeline();
                labelTimeline.stop();
            }
        });
        this.labelTimeline = new Timeline(gf);
        this.labelTimeline.setCycleCount(Animation.INDEFINITE);
        this.labelTimeline.play();
    }
    private void setUpLabel() {

        this.gameStartLabel.setStyle("-fx-font: italic bold 50px arial, serif;-fx-text-alignment: center;-fx-text-fill: white;");

        javafx.scene.paint.Color[] colors = new javafx.scene.paint.Color[]{javafx.scene.paint.Color.web("#E00009"), javafx.scene.paint.Color.web("#E47C00"), javafx.scene.paint.Color.web("#ECEF02"),
                javafx.scene.paint.Color.web("#65F400"), javafx.scene.paint.Color.web("#51B5FF")};
        DropShadow shadow = new DropShadow(BlurType.GAUSSIAN, javafx.scene.paint.Color.web("#E02EF3"),
                0, 10, 2, 2);
        for (Color color : colors) {
            DropShadow temp = new DropShadow(BlurType.GAUSSIAN, color, 0, 10, 2, 2);
            temp.setInput(shadow);
            shadow = temp;
        }
        this.gameStartLabel.setEffect(shadow);
    }

    public void handleKeyPress(KeyEvent e) {
        KeyCode keyPressed = e.getCode();
        switch (keyPressed) {
            case RIGHT:
                if(trigger) {
                    this.moveTimeline(1);
                }
               break;
            case LEFT:
                if(trigger) {
                    this.moveTimeline(-1);
                }
                break;
            case SPACE:
                trigger = true;
                this.stopTimelines();
                counter = counter + 1;
                if(counter == 1) {
                    this.bullet = new Bullet(this.mainShip.getShipX()+49,this.mainShip.getShipY(), mainPane,spaceInvader);
                    this.shootTimeline();
                }
                    if (bullet.getBullet() == null) {
                        System.out.println("RAH");
                        this.shootTimeline.stop();
                        this.bullet = new Bullet(this.mainShip.getShipX() + 49, this.mainShip.getShipY(), mainPane, spaceInvader);
                        this.shootTimeline();
                }
                this.gameTimeline();
        }
        e.consume();
    }
    public void gameTimeline() {
        KeyFrame gf = new KeyFrame(Duration.seconds(.025), (f) -> {
            if(spaceInvader.getAlien() != null) {
                this.bullet.checkIntersections();
            }
            if(bullet.getBullet() != null) {
                if (bullet.getBulletY() < 0) {
                    bullet.removeBullet();
                }
            }
        });
        this.gameTimeline = new Timeline(gf);
        this.gameTimeline.setCycleCount(70);
        this.gameTimeline.play();
    }
    public void shootTimeline() {
        KeyFrame gf = new KeyFrame(Duration.seconds(.025), (f) -> {
            if(bullet.getBullet() != null) {
                this.bullet.setBulletY(bullet.getBulletY() - 10);
            }
        });
        this.shootTimeline = new Timeline(gf);
        this.shootTimeline.setCycleCount(70);
        this.shootTimeline.play();
    }
    public void moveTimeline(int num) {
        KeyFrame ef = new KeyFrame(Duration.seconds(.01), (f) -> {
           this.mainShip.setShipX(this.mainShip.getShipX()+num);
        });
        this.moveTimeline = new Timeline(ef);
        if(this.mainShip.getShipX()>1000) {
            this.mainShip.setShipX(-100);
        }
        if(this.mainShip.getShipX()<-100) {
            this.mainShip.setShipX(1000);
        }
        this.moveTimeline.setCycleCount(20);
        this.moveTimeline.play();
    }
}
