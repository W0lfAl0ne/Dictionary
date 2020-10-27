package Application;

import Dictionary.DictionaryManagement;
import Dictionary.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerHome {

    @FXML
    ListView<String> WorList;
    @FXML
    private TextField WordEnter;
    @FXML
    private Label WordTarget;
    @FXML
    private Label WordExplain;

    public void lookup (ActionEvent activeEvent) {
        String wordEnter = WordEnter.getText();
        int n = DictionaryManagement.dictionary.words.size();
        for (int i = 0; i < n; i++) {
            if (wordEnter.equalsIgnoreCase(DictionaryManagement.dictionary.words.get(i).getWord_target())) {
                WordTarget.setText(wordEnter);
                WordExplain.setText(DictionaryManagement.dictionary.words.get(i).getWord_explain());
                break;
            }
        }
    }

    public void initialize() {

        for (int j = 0;j < DictionaryManagement.dictionary.words.size(); j++) WorList.getItems().add(DictionaryManagement.dictionary.words.get(j).getWord_target());
        WordEnter.textProperty().addListener((obs, oldText, newText) -> {
            try {
                String s;
                boolean bl = false;
                WorList.getItems().clear();
                s = WordEnter.getText();
                int n = DictionaryManagement.dictionary.words.size();
                for (int i = 0;i < n; i++){
                    String temp = "";
                    if (s.length()<=DictionaryManagement.dictionary.words.get(i).getWord_target().length()) temp = DictionaryManagement.dictionary.words.get(i).getWord_target().substring(0,s.length());
                    if (temp.equalsIgnoreCase(s)) {
                        WorList.getItems().add(DictionaryManagement.dictionary.words.get(i).getWord_target());
                        bl = true;
                    }
                }

                if (!bl) WorList.getItems().add("không tìm thấy từ muốn tra");

                if (WordEnter.getText().equalsIgnoreCase(WorList.getItems().get(0))){
                    WordTarget.setText(WorList.getItems().get(0));
                    for (int j = 0; j < n; j++) {
                        if (WorList.getItems().get(0).equalsIgnoreCase(DictionaryManagement.dictionary.words.get(j).getWord_target())) {
                            WordExplain.setText(DictionaryManagement.dictionary.words.get(j).getWord_explain());
                            break;
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("wordSearch. changeWord. " + ex);
            }
        });

        WorList.setOnMouseClicked(e -> {
            String wordTarget = WorList.getSelectionModel().getSelectedItem();
            if (wordTarget == null) {
                return;
            }
            for (Word w: DictionaryManagement.dictionary.words) {
                if (w.getWord_target().equals(wordTarget)) {
                    WordTarget.setText(wordTarget);
                    WordExplain.setText(w.getWord_explain());
                    break;
                }
            }


        });
    }

    public void addAndDelete (ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditWord.fxml"));
        Parent studentViewParent = loader.load();
        Scene scene = new Scene(studentViewParent);

        scene.getStylesheets().add(getClass().getResource("HomeCSS.css").toExternalForm());

        stage.setScene(scene);



    }
}
