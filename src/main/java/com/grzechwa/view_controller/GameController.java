package com.grzechwa.view_controller;

import com.grzechwa.model.Character;
import com.grzechwa.model.District;
import com.grzechwa.model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable{
    @FXML HBox playerHand;
    @FXML GridPane playerBoard;
    @FXML HBox characterArea;
    @Setter private Player humanPlayer;
    private Button draggingButton;
    private final DataFormat buttonFormat = new DataFormat("com.example.myapp.formats.button");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerBoard.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

        playerBoard.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)
                    && draggingButton != null
                    && draggingButton.getParent() != playerBoard) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        playerBoard.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            Node node = e.getPickResult().getIntersectedNode();
            if (db.hasContent(buttonFormat)) {
                Integer cIndex = GridPane.getColumnIndex(node);
                Integer rIndex = GridPane.getRowIndex(node);
                int x = cIndex == null ? 0 : cIndex;
                int y = rIndex == null ? 0 : rIndex;
                ((HBox)draggingButton.getParent()).getChildren().remove(draggingButton);
                playerBoard.add(draggingButton,x,y,1,1);
                e.setDropCompleted(true);
            }
        });
    }

    public void addDistrictsToPlayerHand(ObservableList<District> districtsToAdd) {
        for(District district : districtsToAdd){
            district.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(district.getImagePath()),80,140,false,false)));
            district.setOnDragDetected(e -> {
                Dragboard db = district.startDragAndDrop(TransferMode.MOVE);
                db.setDragView(district.snapshot(null, null));
                ClipboardContent cc = new ClipboardContent();
                cc.put(buttonFormat, "button");
                db.setContent(cc);
                draggingButton = district;
            });
            district.setOnDragDone(e -> draggingButton = null);
            playerHand.getChildren().add(district);
        }
    }

    public void fillViewWithCharactersToPick(ArrayList<Character> characters){
        for(Character character : characters){
            character.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(character.getImagePath()),80,140,false,false)));
            character.setOnAction((event) -> {
                humanPlayer.setChoosenCharacter(character);
                humanPlayer.getChoosenCharacter().setCurrentOwner(humanPlayer);
                characterArea.getChildren().removeAll();
            });
            characterArea.getChildren().add(character);
        }
    }

/*
    public Stage createCharactersPossibleToPick(ArrayList<Character> characters){
        for(Character character : characters){

        }
    }

    */
}
