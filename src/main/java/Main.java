
import com.grzechwa.model.Game;
import com.grzechwa.service.CharacterService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;


public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));
        primaryStage.setTitle("Cytadela by Gesok");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Game game = new Game(3,"Gesok");

        while(!game.hasEnded()){
            game.choosingCharacterPhase();
            /*game.resolvingCharacterPhase();
            game.checkWinConditions(); */
            game.setEnded(true);
        }

    }
}
