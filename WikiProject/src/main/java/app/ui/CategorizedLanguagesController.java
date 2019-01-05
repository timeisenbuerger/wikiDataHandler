package app.ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import handler.WikiDataHandler;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;

public class CategorizedLanguagesController
{
   private CategorizedLanguagesView categorizedLanguagesView;
   private Map<String, List<String>> dataMap;

   public CategorizedLanguagesController()
   {
      categorizedLanguagesView = new CategorizedLanguagesView();

      initListener();
   }

   private void initListener()
   {
      categorizedLanguagesView.getListCategories().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
         String key = categorizedLanguagesView.getListCategories().getSelectionModel().getSelectedItem();
         if( key != null )
         {
            categorizedLanguagesView.getListLanguages().itemsProperty().setValue(FXCollections.observableArrayList(dataMap.get(key)));
         }
      });

      categorizedLanguagesView.getBtnCollectData().setOnAction(event -> {
         try
         {
            ProgressIndicator progressIndicator = new ProgressIndicator();
            VBox vBox = new VBox(progressIndicator);
            vBox.setAlignment(Pos.CENTER);

            categorizedLanguagesView.getHBoxMain().setDisable(true);
            categorizedLanguagesView.getChildren().add(vBox);

            Task<Void> task = new Task<Void>()
            {
               protected Void call()
               {
                  collectData();

                  Platform.runLater(() -> {
                     categorizedLanguagesView.getChildren().remove(vBox);
                     categorizedLanguagesView.getHBoxMain().setDisable(false);
                     categorizedLanguagesView.getBtnExportData().setDisable(false);
                  });
                  return null;
               }
            };
            new Thread(task).start();
         }
         catch(Exception e)
         {
            e.printStackTrace();
         }
      });

      categorizedLanguagesView.getBtnExportData().setOnAction(event -> {
         try
         {
            exportData();
         }
         catch(IOException e)
         {
            e.printStackTrace();
         }
      });
   }

   private void collectData()
   {
      dataMap = WikiDataHandler.collectLanguageFeatures();

      categorizedLanguagesView.getListCategories().itemsProperty().setValue(FXCollections.observableArrayList(dataMap.keySet()));
   }

   private void exportData() throws IOException
   {
      WikiDataHandler.exportData(dataMap);
   }

   public CategorizedLanguagesView getCategorizedLanguagesView()
   {
      return categorizedLanguagesView;
   }
}
