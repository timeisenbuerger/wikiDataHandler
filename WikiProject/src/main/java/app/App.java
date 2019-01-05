package app;

import app.ui.CategorizedLanguagesController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
   public static void main(String[] args)
   {
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception
   {
      CategorizedLanguagesController categorizedLanguagesController = new CategorizedLanguagesController();

      Scene scene = new Scene(categorizedLanguagesController.getCategorizedLanguagesView());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Wiki data collector");
      primaryStage.setResizable(false);

      primaryStage.show();
   }
}
