package application;

import javafx.application.Application;
import javafx.stage.Stage;
import jsonutils.JSONReader;
import jsonutils.JSONWriter;
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
    primaryStage.setOnCloseRequest(e -> { JSONWriter.write("data.json"); });
    primaryStage.setTitle("Jackson Flooring Inventory Management System");
    try {
      BorderPane root = new BorderPane();
      currentScene = new ComboScene(root, 1100, 650, primaryStage);
      primaryStage.setScene(currentScene);
      primaryStage.show();
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    allProducts = new ArrayList<>();
    JSONReader.load("data.json");
    launch(args);
  }
}
