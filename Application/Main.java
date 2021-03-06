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
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 849, 862));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
