package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import jsonutils.JSONUtils;
import scenes.ComboScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import data.Product;

public class Main extends Application {
  
  private static Scene currentScene;
  public static ObservableList<Product> allProducts;
  
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setOnCloseRequest(e -> { JSONUtils.write("data.json"); });
    primaryStage.setTitle("Jackson Flooring Inventory Management System");
    primaryStage.setResizable(false);
    try {
      BorderPane root = new BorderPane();
      currentScene = new ComboScene(root, 1310, 650, primaryStage);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    allProducts = FXCollections.observableArrayList();
    JSONUtils.load("data.json");
    launch(args);
  }
}
