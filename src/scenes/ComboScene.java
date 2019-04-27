package scenes;

import java.util.List;
import java.util.stream.Collectors;
import application.Main;
import data.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComboScene extends Scene {
  
  ObservableList<Product> listProducts;
  Button confirm;
  Button cancel;
  Label lbl;
  TextField[] fields = new TextField[10];
  Button delete;
  HBox bottomControls;
  
  public ComboScene(BorderPane root, double width, double height, Stage stage) {
    super(root, width, height);
    initLeft(root);
    initRight(root);
  }

  private void initLeft(BorderPane parent) {
    BorderPane root = new BorderPane();
    root.setPrefWidth(640);
    initLeft_Center(root);
    initLeft_Bottom(root);
    parent.setLeft(root);
  }
  
  private void initLeft_Bottom(BorderPane root) {
    HBox bottomControls = new HBox(30);
    
    TextField searchBar = new TextField();
    searchBar.setMinWidth(400);
    searchBar.setOnKeyPressed(e -> {
      if(e.getCode().equals(KeyCode.ENTER)) {
        search(searchBar.getText());
      }
    });
    
    Button searchBtn = new Button("Search");
    searchBtn.setMinWidth(90);
    searchBtn.setOnAction(e -> {
      search(searchBar.getText());
    });
    
    bottomControls.setPadding(new Insets(0,0,30,30));
    bottomControls.getChildren().addAll(searchBar, searchBtn);
    bottomControls.setMaxWidth(640);
    
    root.setBottom(bottomControls);
  }
 
  private void initLeft_Center(BorderPane root) {
    VBox centerControls = new VBox(10);
    centerControls.setPadding(new Insets(30));
    listProducts = FXCollections.observableArrayList();
    listProducts.addAll(Main.allProducts);
    
    ListView<Product> lv = new ListView<>();
    lv.setItems(listProducts);
    lv.setMinHeight(500);
    lv.setOnMouseClicked(e ->{
      if(lv.getSelectionModel().getSelectedItem() instanceof Product) {
        editProduct(lv.getSelectionModel().getSelectedItem());
      }
    });
    
    centerControls.getChildren().addAll(lv);
    centerControls.setMaxWidth(640);
    root.setCenter(centerControls);
  }
  
  private void initRight(BorderPane parent) {
    BorderPane root = new BorderPane();
    root.setPrefWidth(640);
    initRight_Center(root);
    initRight_Bottom(root);
    initRight_Top(root);
    parent.setRight(root);
  }

  private void initRight_Center(BorderPane root) {
    VBox centerControls = new VBox(10);
    centerControls.setPadding(new Insets(30,30,0,40));

    HBox[] rows = new HBox[10];
    Label[] labels = new Label[10];
    for(int i = 0; i < rows.length; i++) {
      rows[i] = new HBox();
      fields[i] = new TextField();
      labels[i] = new Label();
    }
    
    labels[0] = new Label(String.format("%-15s","Manufacturer: "));
    labels[0].setStyle("-fx-font-size:20");
    rows[0].setSpacing(30);
    
    labels[1] = new Label(String.format("%-15s","Color: "));
    labels[1].setStyle("-fx-font-size:20");
    rows[1].setSpacing(62);
    
    labels[2] = new Label(String.format("%-15s","Style: "));
    labels[2].setStyle("-fx-font-size:20");
    rows[2].setSpacing(67);
    
    labels[3] = new Label(String.format("%-15s","Dimensions: "));
    labels[3].setStyle("-fx-font-size:20");
    rows[3].setSpacing(35);
    
    labels[4] = new Label(String.format("%-15s","Num. Units: "));
    labels[4].setStyle("-fx-font-size:20");
    rows[4].setSpacing(40);
    
    labels[5] = new Label(String.format("%-15s", "Qty./Unit"));
    labels[5].setStyle("-fx-font-size:20");
    rows[5].setSpacing(50);
    
    labels[6] = new Label(String.format("%-15s","Type: "));
    labels[6].setStyle("-fx-font-size:20");
    rows[6].setSpacing(62);
    
    labels[7] = new Label(String.format("%-15s","Sq.Ft/Unit: "));
    labels[7].setStyle("-fx-font-size:20");
    rows[7].setSpacing(49);
    
    labels[8] = new Label(String.format("%-15s", "Total Sq.Feet: "));
    labels[8].setStyle("-fx-font-size:20");
    rows[8].setSpacing(40);
    fields[8].setEditable(false);
    
    labels[9] = new Label(String.format("%-15s", "In Stock: "));
    labels[9].setStyle("-fx-font-size:20");
    rows[9].setSpacing(56);
    
    for(int i = 0; i <= 9; i++) {
      rows[i].getChildren().addAll(labels[i], fields[i]);
    }
    
    centerControls.getChildren().addAll(rows);
    root.setCenter(centerControls);
  }

  private void initRight_Bottom(BorderPane root) {
    bottomControls = new HBox(30);
    bottomControls.setPadding(new Insets(0, 30, 30, 120));
    
    confirm = new Button();
    confirm.setText("Add Product");
    confirm.setOnAction(e -> {
      makeNewProduct();
    });
    
    cancel = new Button("Cancel");
    cancel.setOnAction(e -> {
      clearFields();
    });
    bottomControls.getChildren().addAll(confirm, cancel);
    root.setBottom(bottomControls);
  }
  
  private void initRight_Top(BorderPane root) {
    HBox holder = new HBox();
    holder.setPadding(new Insets(30, 30, 0, 120));
    lbl = new Label("Add New Inventory");
    lbl.setStyle("-fx-font-size:30");
    holder.getChildren().addAll(lbl);
    root.setTop(holder);
  }
  
  private void search(String query) {
    List<Product> newList = Main.allProducts
        .stream()
        .filter( s -> s.toString().toLowerCase().contains(query.toLowerCase()))
        .collect(Collectors.toList());
    listProducts.clear();
    listProducts.addAll(newList);
    
  }

  private void clearFields() {
    for(int i = 0; i < fields.length; i++) {
      fields[i].setText("");
    }
  }

  private void editProduct(Product prod) {
    delete = new Button("Delete");
    delete.setOnAction(e -> {
      deleteProduct(prod);
    });
    bottomControls.getChildren().add(delete);
    lbl.setText("Edit Product");
    confirm.setText("Save Changes");
    confirm.setOnAction(e -> {
      makeChanges(prod);
    });
    cancel.setOnAction(e -> {
      cancelEdit();
    });
    
    fields[0].setText(prod.getManufacturer());
    fields[1].setText(prod.getColor());
    fields[2].setText(prod.getStyle());
    fields[3].setText(prod.getDimensions());
    fields[4].setText(String.valueOf(prod.getQuantity()));
    fields[5].setText(String.valueOf(prod.getAmountPerBox()));
    fields[6].setText(prod.getType());
    fields[7].setText(String.valueOf(prod.getSqFoot()));
    fields[8].setText(String.valueOf(prod.getSqFootTotal()));
    fields[9].setText(String.valueOf(prod.isInStock()));
  }

  private void deleteProduct(Product prod) {
    Main.allProducts.remove(prod);
    bottomControls.getChildren().remove(delete);
    clearFields();
    listProducts.clear();
    listProducts.addAll(Main.allProducts);
    lbl.setText("Add New Product");
    confirm.setOnAction(e -> {
      makeNewProduct();
    });
    cancel.setOnAction(e -> {
      clearFields();
    });
  }

  private void makeChanges(Product prod) {
    Alert alert = null;
    
    String man = fields[0].getText();
    String color = fields[1].getText();
    String style = fields[2].getText();
    String dim = fields[3].getText();
    
    int qty = 0;
    try { 
      qty = Integer.parseInt(fields[4].getText()); 
    } 
    catch(Exception e) { 
      alert = new Alert(AlertType.WARNING, "Input for quantity was not a number!");
      alert.showAndWait();
      return;
    }
    
    int numPer = 0;
    try {
      numPer = Integer.parseInt(fields[5].getText());
    }
    catch(Exception e) {
      alert = new Alert(AlertType.WARNING, "Input for Qty./unit was not a number!");
    }
    
    String type = fields[6].getText();
    
    double sqFoot = 0;
    try {
      sqFoot = Double.parseDouble(fields[7].getText());
    }
    catch(Exception e) {
      alert = new Alert(AlertType.WARNING, "Input for sq.foot/unit was not a number!");
      alert.showAndWait();
      return;
    }
    
    boolean inStock = false;
    if(!fields[9].getText().equals("true") && !fields[9].getText().contentEquals("false")) {
      alert = new Alert(AlertType.WARNING, "In stock status must be true or false");
      alert.showAndWait();
      return;
    }
    
    inStock = Boolean.parseBoolean(fields[9].getText());

    prod.setManufacturer(man);
    prod.setColor(color);
    prod.setStyle(style);
    prod.setDimensions(dim);
    prod.setQuantity(qty);
    prod.setType(type);
    prod.setAmountPerBox(numPer);
    prod.setSqFoot(sqFoot);
    prod.setSqFootTotal(numPer*sqFoot*qty);
    prod.setInStock(inStock);
    clearFields();
    
    listProducts.clear();
    listProducts.addAll(Main.allProducts);
    
    lbl.setText("Add New Inventory");
    confirm.setText("Add Product");
    confirm.setOnAction(e -> {
      makeNewProduct();
    });
    cancel.setOnAction(e -> {
      clearFields();
    });
  }

  private void makeNewProduct() {
    Product prod = new Product();
    Alert alert = null;
    
    if(allFieldsEmpty()) {
      alert = new Alert(AlertType.WARNING, "All fields are empty!");
      alert.showAndWait();
      return;
    }
    
    String man = fields[0].getText();
    String color = fields[1].getText();
    String style = fields[2].getText();
    String dim = fields[3].getText();
    
    int qty = 0;
    try { 
      qty = Integer.parseInt(fields[4].getText()); 
    } 
    catch(Exception e) { 
      alert = new Alert(AlertType.WARNING, "Input for quantity was not a number!");
      alert.showAndWait();
      return;
    }
    
    int numPer = 0;
    try {
      numPer = Integer.parseInt(fields[5].getText());
    }
    catch(Exception e) {
      alert = new Alert(AlertType.WARNING, "Input for Qty./unit was not a number!");
    }
    
    String type = fields[6].getText();
    
    double sqFoot = 0;
    try {
      sqFoot = Double.parseDouble(fields[7].getText());
    }
    catch(Exception e) {
      alert = new Alert(AlertType.WARNING, "Input for sq.foot/unit was not a number!");
      alert.showAndWait();
      return;
    }
    
    boolean inStock = false;
    if(!fields[9].getText().equals("true") && !fields[9].getText().contentEquals("false")) {
      alert = new Alert(AlertType.WARNING, "In stock status must be true or false");
      alert.showAndWait();
      return;
    }
    
    inStock = Boolean.parseBoolean(fields[9].getText());
    
    prod.setManufacturer(man);
    prod.setColor(color);
    prod.setStyle(style);
    prod.setDimensions(dim);
    prod.setQuantity(qty);
    prod.setType(type);
    prod.setAmountPerBox(numPer);
    prod.setSqFoot(sqFoot);
    prod.setSqFootTotal(numPer*sqFoot*qty);
    prod.setInStock(inStock);
    clearFields();
    
    listProducts.clear();
    Main.allProducts.add(prod);
    listProducts.addAll(Main.allProducts);
    
    lbl.setText("Add New Inventory");
    confirm.setText("Add Product");
    confirm.setOnAction(e -> {
      clearFields();
    });
  }

  private void cancelEdit() {
    clearFields();
    lbl.setText("Add New Inventory");
    confirm.setText("Add Product");
    confirm.setOnAction(e -> {
      makeNewProduct();
    });
    
    cancel.setOnAction(e -> {
      clearFields();
    });
  }

  private boolean allFieldsEmpty() {
    for(int i = 0; i < fields.length; i++) {
      if(!fields[i].getText().equals("")) {
        return false;
      }
    }
    
    return true;
  }
}
