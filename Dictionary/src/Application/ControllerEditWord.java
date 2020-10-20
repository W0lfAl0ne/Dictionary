package Application;

import Dictionary.DictionaryManagement;
import Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerEditWord implements Initializable {
    @FXML
    private TextField textTarget;

    @FXML
    private TextField textExplain;

    @FXML
    private TextField wordEnter;

    @FXML
    private Label text;

    @FXML
    private TableView<Word> table;
    @FXML
    private TableColumn<Word, String> target;
    @FXML
    private TableColumn<Word, String> explain;

    private ObservableList<Word> wordList;


    public void lookup (ActionEvent activeEvent) {
        if (table.getItems().size()>0) {

            String wordEnter = table.getItems().get(0).getWord_target();
            int n = DictionaryManagement.dictionary.words.size();
            for (int i = 0; i < n; i++) {
                if (wordEnter.equalsIgnoreCase(DictionaryManagement.dictionary.words.get(i).getWord_target())) {
                    textTarget.setText(wordEnter);
                    textExplain.setText(DictionaryManagement.dictionary.words.get(i).getWord_explain());
                    break;
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordList = FXCollections.observableArrayList();
        for (int i = 0; i< DictionaryManagement.dictionary.words.size();i++) wordList.add(DictionaryManagement.dictionary.words.get(i));
        target.setCellValueFactory(new PropertyValueFactory<>("word_target"));
        explain.setCellValueFactory(new PropertyValueFactory<>("word_explain"));
        table.setItems(wordList);

        wordEnter.textProperty().addListener((obs, oldText, newText) -> {
            try {
                String s;
                boolean bl = false;
                wordList.clear();
                s = wordEnter.getText();
                int n = DictionaryManagement.dictionary.words.size();
                for (int i = 0;i < n; i++){
                    String temp = "";
                    if (s.length()<=DictionaryManagement.dictionary.words.get(i).getWord_target().length()) temp = DictionaryManagement.dictionary.words.get(i).getWord_target().substring(0,s.length());
                    if (temp.equalsIgnoreCase(s)) {
                        wordList.add(DictionaryManagement.dictionary.words.get(i));
                        table.setItems(wordList);
                    }
                }
            } catch (Exception ex) {
                System.out.println("wordSearch. changeWord. " + ex);
            }
        });


        table.setOnMouseClicked(e -> {
            Word wordTarget = table.getSelectionModel().getSelectedItem();
            if (wordTarget == null) {
                return;
            }
            textTarget.setText(wordTarget.getWord_target());
            textExplain.setText(wordTarget.getWord_explain());

        });
    }

    public void delete(ActionEvent event) {
        Word selected = null;
        boolean bl = false;
        for (int i = 0; i<wordList.size();i++) if (wordList.get(i).getWord_target().equalsIgnoreCase(textTarget.getText())) {
            selected = wordList.get(i);
            bl = true;
            break;
        }
        //Word selected = table.getSelectionModel().getSelectedItem();
        wordList.remove(selected);
        DictionaryManagement.dictionary.words.remove(selected);
        DictionaryManagement.writeToFile();
        if (!bl) text.setText("Không tìm thấy từ bạn muốn xóa");
        else text.setText("Xóa từ thành công");
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Stage stage =(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    public void add(ActionEvent actionEvent) {
        if (textTarget.getText().equalsIgnoreCase("") || textExplain.getText().equalsIgnoreCase("") ){
            text.setText("Nhập đủ English và Vietnammes");
        }else {
            boolean bl = false;
            for (int i = 0; i < DictionaryManagement.dictionary.words.size(); i++)
                if (textTarget.getText().equalsIgnoreCase(DictionaryManagement.dictionary.words.get(i).getWord_target()))
                    bl = true;
            if (!bl) {
                DictionaryManagement.dictionary.addWord(textTarget.getText(), textExplain.getText());
                DictionaryManagement.writeToFile();

                wordList = FXCollections.observableArrayList();
                for (int i = 0; i< DictionaryManagement.dictionary.words.size();i++) wordList.add(DictionaryManagement.dictionary.words.get(i));
                target.setCellValueFactory(new PropertyValueFactory<>("word_target"));
                explain.setCellValueFactory(new PropertyValueFactory<>("word_explain"));
                table.setItems(wordList);
                text.setText("Thêm từ thành công");
            } else {
                text.setText("Từ đã tồn tại");
            }
        }
    }

    public void reset(ActionEvent actionEvent) {
        if (textTarget.getText().equalsIgnoreCase("") || textExplain.getText().equalsIgnoreCase("") ){
            text.setText("Nhập đủ English và Vietnammes");
        }else {
            boolean bl = false;
            for (int i = 0; i < DictionaryManagement.dictionary.words.size(); i++)
                if (textTarget.getText().equalsIgnoreCase(DictionaryManagement.dictionary.words.get(i).getWord_target())) {
                    bl = true;
                    DictionaryManagement.dictionary.words.get(i).setWord_target(textTarget.getText());
                    DictionaryManagement.dictionary.words.get(i).setWord_explain(textExplain.getText());
                    DictionaryManagement.writeToFile();

                    wordList.clear();
                    for (int j = 0; j< DictionaryManagement.dictionary.words.size();j++) wordList.add(DictionaryManagement.dictionary.words.get(j));
                    target.setCellValueFactory(new PropertyValueFactory<>("word_target"));
                    explain.setCellValueFactory(new PropertyValueFactory<>("word_explain"));
                    table.setItems(wordList);
                    text.setText("Sửa từ thành công");
                }
            if(!bl) text.setText("Không tìm thấy từ");
        }
    }
}
