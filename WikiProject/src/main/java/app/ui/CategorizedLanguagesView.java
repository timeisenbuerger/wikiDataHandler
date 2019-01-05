package app.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CategorizedLanguagesView extends StackPane
{
   private Label lblCategories;
   private Label lblLanguages;

   private Button btnCollectData;
   private Button btnExportData;

   private HBox hBoxMain;

   private ListView<String> listCategories;
   private ListView<String> listLanguages;

   public CategorizedLanguagesView()
   {
      initComponents();
   }

   private void initComponents()
   {
      lblCategories = new Label("Category:");
      lblLanguages = new Label("Language:");

      btnCollectData = new Button("Collect data");
      btnExportData = new Button("Export data");

      listCategories = new ListView<>();
      listLanguages = new ListView<>();

      VBox vBoxCategories = new VBox(lblCategories, listCategories, new StackPane(btnCollectData));
      VBox vBoxLanguages = new VBox(lblLanguages, listLanguages, new StackPane(btnExportData));

      vBoxCategories.setSpacing(5.0);
      vBoxLanguages.setSpacing(5.0);

      btnExportData.setDisable(true);

      hBoxMain = new HBox(vBoxCategories, vBoxLanguages);

      getChildren().add(hBoxMain);
   }

   public Button getBtnCollectData()
   {
      return btnCollectData;
   }

   public Button getBtnExportData()
   {
      return btnExportData;
   }

   public ListView<String> getListCategories()
   {
      return listCategories;
   }

   public ListView<String> getListLanguages()
   {
      return listLanguages;
   }

   public HBox getHBoxMain()
   {
      return hBoxMain;
   }
}
