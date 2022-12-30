package spaceinvaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    //sets the size of the scene and disables resizability
    @Override
    public void start(Stage stage) {
        stage.setTitle("SPACE INVADERS");
        stage.show();
        stage.setResizable(false);
        PaneOrganizer organizer = new PaneOrganizer();
        Scene scene = new Scene(organizer.getRoot(), Constants.PANE_WIDTH, Constants.PANE_HEIGHT);
        stage.setScene(scene);
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
