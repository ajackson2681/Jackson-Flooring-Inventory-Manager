package jsonutils;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.*;
import application.Main;
import data.Product;

public class JSONWriter {

  @SuppressWarnings("unchecked")
  public static void write(String jsonFile) {
    JSONObject doc = new JSONObject();
    JSONArray allProducts = new JSONArray();

    for (Product p : Main.allProducts) {
      JSONObject curProduct = new JSONObject();
      curProduct.put("manufacturer", p.getManufacturer());
      curProduct.put("color", p.getColor());
      curProduct.put("style", p.getStyle());
      curProduct.put("dimensions", p.getDimensions());
      curProduct.put("quantity", String.valueOf(p.getQuantity()));
      curProduct.put("type", String.valueOf(p.getType()));
      curProduct.put("amountPerBox", String.valueOf(p.getAmountPerBox()));
      curProduct.put("sqFoot", String.valueOf(p.getSqFoot()));
      curProduct.put("sqFootTotal", String.valueOf(p.getSqFootTotal()));
      curProduct.put("inStock", String.valueOf(p.isInStock()));
      allProducts.add(curProduct);
    }
    doc.put("products", allProducts);

    try (FileWriter file = new FileWriter(jsonFile)) {
      file.write(doc.toJSONString());
      file.flush();
    } 
    catch (IOException e) {
      e.printStackTrace();
      JSONWriter.write(jsonFile);
    }
  }
}
