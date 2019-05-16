package application;

import javafx.application.Application;
import javafx.stage.Stage;
import jsonutils.JSONUtils;
import scenes.ComboScene;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.ArrayList;
import data.Product;

public class Main extends Application {
  
  private static Scene currentScene;
  public static ArrayList<Product> allProducts;
  
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
    allProducts = new ArrayList<>();
    JSONUtils.load("data.json");
    launch(args);
  }
}
