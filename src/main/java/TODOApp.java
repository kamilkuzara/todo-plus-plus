import ui.MainPageUI;
import model.Model;

import com.jfoenix.controls.JFXDecorator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class TODOApp extends Application{

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException
    {

        System.setProperty("prism.lcdtext", "false"); //for better font rendering

        MainPageUI mainPageUI = new MainPageUI();

	      JFXDecorator jfxDecorator = new JFXDecorator(primaryStage, mainPageUI, false, true, true);
        jfxDecorator.setCustomMaximize(true);
        jfxDecorator.setTitle("TODO++");
        Scene scene = new Scene(jfxDecorator, 1200, 600);
        scene.getStylesheets().add(getClass().getResource("/styles/scene_styling.css").toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(event -> {
            Model.instance().saveToFile();
        });

        primaryStage.show();
    }
}
