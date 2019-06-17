package scenes;

import application.Main;
import data.Product;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ComboScene extends Scene {
  
  Button confirm;
  Button cancel;
  Button delete;
  
  Label lbl;
  
  TextField manField = new TextField();
  TextField styleField = new TextField();
  TextField colorField = new TextField();
  TextField dimField = new TextField();
  TextField qtyField = new TextField();
  TextField qtyPerField = new TextField();
  TextField sqFootField = new TextField();
  TextField sqFootTotalField = new TextField();
  TextField typeField = new TextField();
  TextField inStockField = new TextField();
  TextField priceField = new TextField();
  
  HBox bottomControls;
  
  ListView<Product> lv;

  public ComboScene(BorderPane root, double width, double height, Stage stage) {
    super(root, width, height);
    initLeft(root);
    initRight(root);
  }

  private void initLeft(BorderPane parent) {
    BorderPane root = new BorderPane();
    root.setPrefWidth(810);
    initLeft_Top(root);
    initLeft_Center(root);
    initLeft_Bottom(root);
    parent.setLeft(root);
  }

  private void initLeft_Top(BorderPane root) {
    HBox topControls = new HBox();
    topControls.setPadding(new Insets(30, 0, 0, 40));

    Label header = new Label();
    header.setFont(new Font("Courier New", 15));

    header.setText(String.format("%-15s | %-15s | %-15s | %-15s | %-15s", "Manufacturer", "Color",
        "Style", "Dimensions", "Type"));
    topControls.getChildren().addAll(header);

    root.setTop(topControls);
  }

  private void initLeft_Bottom(BorderPane root) {
    HBox bottomControls = new HBox(30);

    TextField searchBar = new TextField();
    searchBar.setMinWidth(690);
    searchBar.setOnKeyPressed(e -> {
      if (e.getCode().equals(KeyCode.ENTER)) {
        search(searchBar.getText());
      }
    });

    Button searchBtn = new Button("Search");
    searchBtn.setMinWidth(90);
    searchBtn.setOnAction(e -> {
      search(searchBar.getText());
    });

    bottomControls.setPadding(new Insets(0, 0, 30, 30));
    bottomControls.getChildren().addAll(searchBar, searchBtn);
    bottomControls.setMaxWidth(640);

    root.setBottom(bottomControls);
  }

  private void initLeft_Center(BorderPane root) {
    VBox centerControls = new VBox(10);
    centerControls.setPadding(new Insets(5, 30, 30, 30));

    lv = new ListView<>();
    lv.setItems(Main.allProducts);
    lv.setMinHeight(520);
    lv.setMinWidth(810);
    lv.setOnMouseClicked(e -> {
      if (lv.getSelectionModel().getSelectedItem() instanceof Product) {
        editProduct(lv.getSelectionModel().getSelectedItem());
        lv.getSelectionModel().clearSelection();
      }
    });
    updateListView();

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

  private void initRight_Top(BorderPane root) {
    HBox holder = new HBox();
    holder.setPadding(new Insets(30, 30, 0, 120));
    lbl = new Label("Add/Edit Inventory");
    lbl.setStyle("-fx-font-size:30");
    holder.getChildren().addAll(lbl);
    root.setTop(holder);
  }

  private void initRight_Center(BorderPane root) {
    VBox centerControls = new VBox(10);
    centerControls.setPadding(new Insets(30, 30, 0, 40));

    HBox[] rows = new HBox[11];
    Label[] labels = new Label[11];
    for (int i = 0; i < rows.length; i++) {
      rows[i] = new HBox();
      labels[i] = new Label();
    }

    labels[0] = new Label(String.format("%-15s", "Manufacturer: "));
    labels[0].setStyle("-fx-font-size:20");
    rows[0].setSpacing(30);

    labels[1] = new Label(String.format("%-15s", "Color: "));
    labels[1].setStyle("-fx-font-size:20");
    rows[1].setSpacing(62);

    labels[2] = new Label(String.format("%-15s", "Style: "));
    labels[2].setStyle("-fx-font-size:20");
    rows[2].setSpacing(67);

    labels[3] = new Label(String.format("%-15s", "Dimensions: "));
    labels[3].setStyle("-fx-font-size:20");
    rows[3].setSpacing(35);

    labels[4] = new Label(String.format("%-15s", "Num. Units: "));
    labels[4].setStyle("-fx-font-size:20");
    rows[4].setSpacing(40);

    labels[5] = new Label(String.format("%-15s", "Qty./Unit"));
    labels[5].setStyle("-fx-font-size:20");
    rows[5].setSpacing(50);

    labels[6] = new Label(String.format("%-15s", "Type: "));
    labels[6].setStyle("-fx-font-size:20");
    rows[6].setSpacing(62);

    labels[7] = new Label(String.format("%-15s", "Sq.Ft/Unit: "));
    labels[7].setStyle("-fx-font-size:20");
    rows[7].setSpacing(49);

    labels[8] = new Label(String.format("%-15s", "Total Sq.Feet: "));
    labels[8].setStyle("-fx-font-size:20");
    rows[8].setSpacing(40);
    sqFootTotalField.setEditable(false);

    labels[9] = new Label(String.format("%-15s", "Sold: "));
    labels[9].setStyle("-fx-font-size:20");
    rows[9].setSpacing(65);

    labels[10] = new Label(String.format("%-15s", "Price: "));
    labels[10].setStyle("-fx-font-size:20");
    rows[10].setSpacing(66);

    rows[0].getChildren().addAll(labels[0], manField);
    rows[1].getChildren().addAll(labels[1], colorField);
    rows[2].getChildren().addAll(labels[2], styleField);
    rows[3].getChildren().addAll(labels[3], dimField);
    rows[4].getChildren().addAll(labels[4], qtyField);
    rows[5].getChildren().addAll(labels[5], qtyPerField);
    rows[6].getChildren().addAll(labels[6], typeField);
    rows[7].getChildren().addAll(labels[7], sqFootField);
    rows[8].getChildren().addAll(labels[8], sqFootTotalField);
    rows[9].getChildren().addAll(labels[9], inStockField);
    rows[10].getChildren().addAll(labels[10], priceField);

    priceField.setOnKeyPressed(e -> {
      if (e.getCode().equals(KeyCode.ENTER)) {
        addNewProduct();
      }
    });
    centerControls.getChildren().addAll(rows);
    root.setCenter(centerControls);
  }

  private void initRight_Bottom(BorderPane root) {
    bottomControls = new HBox(30);
    bottomControls.setPadding(new Insets(0, 30, 30, 120));

    confirm = new Button();
    confirm.setText("Add Product");
    confirm.setOnAction(e -> {
      if (allFieldsEmpty()) {
        Alert alert = new Alert(AlertType.WARNING, "All fields are empty!");
        alert.showAndWait();
      } else {
        addNewProduct();
      }
    });

    cancel = new Button("Cancel");
    cancel.setOnAction(e -> {
      clearFields();
    });

    bottomControls.getChildren().addAll(confirm, cancel);
    root.setBottom(bottomControls);
  }

  private void search(String query) {
    if(query.equals("")) {
      lv.setItems(Main.allProducts);
    }
    else {
      ObservableList<Product> newList = Main.allProducts.filtered(e -> e.toString().toLowerCase().contains(query.toLowerCase()));
      lv.setItems(newList);
    }

    lv.setCellFactory(cell -> {
      return new ListCell<Product>() {
        @Override
        protected void updateItem(Product item, boolean empty) {
          super.updateItem(item, empty);
          if (item != null) {
            setText(item.toString());
            setFont(new Font("Courier New", 15));
          }
        }
      };
    });
  }

  private void clearFields() {
    manField.setText("");
    styleField.setText("");
    colorField.setText("");
    dimField.setText("");
    qtyField.setText("");
    qtyPerField.setText("");
    sqFootField.setText("");
    sqFootTotalField.setText("");
    typeField.setText("");
    inStockField.setText("");
    priceField.setText("");
  }

  private boolean allFieldsEmpty() {
    if (!manField.getText().equals("")) {
      return false;
    }
    if (!styleField.getText().equals("")) {
      return false;
    }
    if (!colorField.getText().equals("")) {
      return false;
    }
    if (!dimField.getText().equals("")) {
      return false;
    }
    if (!qtyField.getText().equals("")) {
      return false;
    }
    if (!qtyPerField.getText().equals("")) {
      return false;
    }
    if (!sqFootField.getText().equals("")) {
      return false;
    }
    if (!sqFootTotalField.getText().equals("")) {
      return false;
    }
    if (!typeField.getText().equals("")) {
      return false;
    }
    if (!inStockField.getText().equals("")) {
      return false;
    }
    if (!priceField.getText().equals("")) {
      return false;
    }
    return true;
  }

  private void updateListView() {
    lv.setCellFactory(cell -> {
      return new ListCell<Product>() {
        @Override
        protected void updateItem(Product item, boolean empty) {
          super.updateItem(item, empty);
          if (item != null) {
            setText(item.toString());
            setFont(new Font("Courier New", 15));
          }
        }
      };
    });
  }

  private void addNewProduct() {
    Alert alert = null;
    Product prod = new Product();

    String man = manField.getText();
    String color = colorField.getText();
    String style = styleField.getText();
    String dim = dimField.getText();

    int qty = 0;

    try {
      qty = Integer.parseInt(qtyField.getText());
    } catch (NumberFormatException e) {
      alert = new Alert(AlertType.WARNING, "Invalid input for Num. Units!");
      alert.showAndWait();
      return;
    }

    String type = typeField.getText();

    int numPer = 0;

    try {
      numPer = Integer.parseInt(qtyPerField.getText());
    } catch (NumberFormatException e) {
      alert = new Alert(AlertType.WARNING, "Invalid input for Qty./Unit!");
      alert.showAndWait();
      return;
    }

    double sqFoot = 0;

    try {
      sqFoot = Double.parseDouble(sqFootField.getText());
    } catch (NumberFormatException e) {
      alert = new Alert(AlertType.WARNING, "Invalid input for Sq.Ft/Unit!");
      alert.showAndWait();
      return;
    }

    String inStock = inStockField.getText();
    String price = priceField.getText();

    prod.setManufacturer(man);
    prod.setColor(color);
    prod.setStyle(style);
    prod.setDimensions(dim);
    prod.setQuantity(qty);
    prod.setType(type);
    prod.setAmountPerBox(numPer);
    prod.setSqFoot(sqFoot);
    prod.setSqFootTotal(sqFoot * qty);
    prod.setInStock(inStock);
    prod.setPrice(price);

    Main.allProducts.add(prod);
    clearFields();
    updateListView();
  }

  private void editProduct(Product prod) {
    if (!bottomControls.getChildren().contains(delete)) {
      delete = new Button("Delete");
      bottomControls.getChildren().add(delete);
      delete.setOnAction(e -> {
        bottomControls.getChildren().remove(delete);
        Main.allProducts.remove(prod);
        clearFields();
        updateListView();
        confirm.setText("Add New Product");
        confirm.setOnAction(f -> {
          addNewProduct();
        });
      });
    }

    confirm.setText("Save Changes");
    confirm.setOnAction(e -> {
      bottomControls.getChildren().remove(delete);
      saveChanges(prod);
      confirm.setText("Add New Product");
      confirm.setOnAction(f -> {
        addNewProduct();
      });
    });

    cancel.setOnAction(e -> {
      bottomControls.getChildren().remove(delete);
      clearFields();
      confirm.setText("Add New Product");
      confirm.setOnAction(f -> {
        addNewProduct();
      });
    });

    priceField.setOnKeyPressed(e -> {
      if (e.getCode().equals(KeyCode.ENTER)) {
        bottomControls.getChildren().remove(delete);
        saveChanges(prod);
        confirm.setText("Add New Product");
        confirm.setOnAction(f -> {
          addNewProduct();
        });
      }
    });

    manField.setText(prod.getManufacturer());
    styleField.setText(prod.getStyle());
    colorField.setText(prod.getColor());
    dimField.setText(prod.getDimensions());
    qtyField.setText(String.valueOf(prod.getQuantity()));
    qtyPerField.setText(String.valueOf(prod.getAmountPerBox()));
    sqFootField.setText(String.valueOf(prod.getSqFoot()));
    sqFootTotalField.setText(String.valueOf(prod.getSqFootTotal()));
    typeField.setText(prod.getType());
    inStockField.setText(prod.isInStock());
    priceField.setText(prod.getPrice());
  }

  private void saveChanges(Product prod) {
    Alert alert = null;

    String man = manField.getText();
    String color = colorField.getText();
    String style = styleField.getText();
    String dim = dimField.getText();

    int qty = 0;

    try {
      qty = Integer.parseInt(qtyField.getText());
    } catch (NumberFormatException e) {
      alert = new Alert(AlertType.WARNING, "Invalid input for Num. Units!");
      alert.showAndWait();
      return;
    }

    String type = typeField.getText();

    int numPer = 0;

    try {
      numPer = Integer.parseInt(qtyPerField.getText());
    } catch (NumberFormatException e) {
      alert = new Alert(AlertType.WARNING, "Invalid input for Qty./Unit!");
      alert.showAndWait();
      return;
    }

    double sqFoot = 0;

    try {
      sqFoot = Double.parseDouble(sqFootField.getText());
    } catch (NumberFormatException e) {
      alert = new Alert(AlertType.WARNING, "Invalid input for Sq.Ft/Unit!");
      alert.showAndWait();
      return;
    }

    String inStock = inStockField.getText();
    String price = priceField.getText();

    prod.setManufacturer(man);
    prod.setColor(color);
    prod.setStyle(style);
    prod.setDimensions(dim);
    prod.setQuantity(qty);
    prod.setType(type);
    prod.setAmountPerBox(numPer);
    prod.setSqFoot(sqFoot);
    prod.setSqFootTotal(sqFoot * qty);
    prod.setInStock(inStock);
    prod.setPrice(price);

    clearFields();
    updateListView();
  }

}
