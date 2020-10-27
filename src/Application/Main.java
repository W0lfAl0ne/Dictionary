package Application;

import Dictionary.DictionaryCommandline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        DictionaryCommandline dictionaryCommandline=new DictionaryCommandline();
        dictionaryCommandline.dictionaryBasic();

        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root, 870, 862);
        scene.getStylesheets().add(getClass().getResource("HomeCSS.css").toExternalForm());
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
