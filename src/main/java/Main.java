
import com.grzechwa.model.Game;
import com.grzechwa.model.GameInitializer;
import com.grzechwa.service.CharacterService;
import com.grzechwa.view_controller.GameController;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Game.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Cytadela by Gesok");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        Game game = new GameInitializer(3,loader.getController(),"Gesok").initialize();

        while(!game.hasEnded()){
            game.choosingCharacterPhase();
            game.resolvingCharacterPhase();
            game.setEnded(true);
        }

    }
}
