package scientific.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScientificCalculator extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try{
        Parent root = FXMLLoader.load(getClass().getResource("/scientific/calculator/MainView.fxml"));
        Scene scene = new Scene(root, 465, 320);
        primaryStage.setTitle("Scientific Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
        }catch(Exception e){
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
