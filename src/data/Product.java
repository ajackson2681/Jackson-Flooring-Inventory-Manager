package data;

public class Product {

  private String manufacturer;
  private String color;
  private String style;
  private String dimensions;
  private int quantity;
  private String type;
  private int amountPerBox;
  private double sqFoot;
  private double sqFootTotal;
  private String inStock;
  private String price;
  
  public Product() {
    this.manufacturer = "N/A";
    this.color = "N/A";
    this.inStock = "N/A";
    this.dimensions = "N/A";
    this.quantity = 0;
    this.type = "N/A";
    this.amountPerBox = 0;
    this.sqFoot = 0;
    this.sqFoot = 0;
    this.inStock = "N/A";
    this.price = "N/A";
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getDimensions() {
    return dimensions;
  }

  public void setDimensions(String dimensions) {
    this.dimensions = dimensions;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getSqFoot() {
    return sqFoot;
  }

  public void setSqFoot(double sqFoot) {
    this.sqFoot = sqFoot;
  }

  public String isInStock() {
    return inStock;
  }

  public void setInStock(String inStock) {
    this.inStock = inStock;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getAmountPerBox() {
    return amountPerBox;
  }

  public void setAmountPerBox(int amountPerBox) {
    this.amountPerBox = amountPerBox;
  }

  public double getSqFootTotal() {
    return sqFootTotal;
  }

  public void setSqFootTotal(double sqFootTotal) {
    this.sqFootTotal = sqFootTotal;
  }

  public String toString() {
    return String.format("%-15s | %-15s | %-15s | %-15s | %-15s",
        manufacturer, color, style, dimensions, type);
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
