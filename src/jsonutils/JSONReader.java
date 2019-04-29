package jsonutils;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import application.Main;
import data.Product;

public class JSONReader {

  public static void load(String jsonFile) {
    Object obj = null;
    
    try {
      obj = new JSONParser().parse(new FileReader(jsonFile));
    }
    catch(Exception e) {}
    
    JSONObject doc = (JSONObject)obj;
    JSONArray products;
    
    try {
      products = (JSONArray) doc.get("products");
    }
    catch(Exception e) {
      return;
    }
    
    for(int i = 0; i < products.size(); i++) {
      JSONObject curProduct = (JSONObject)products.get(i);
      
      Product productToAdd = new Product();
      
      productToAdd.setManufacturer((String)curProduct.get("manufacturer"));
      productToAdd.setColor((String)curProduct.get("color"));
      productToAdd.setStyle((String)curProduct.get("style"));
      productToAdd.setDimensions((String)curProduct.get("dimensions"));
      productToAdd.setQuantity(Integer.parseInt((String)curProduct.get("quantity")));
      productToAdd.setType((String)curProduct.get("type"));
      productToAdd.setAmountPerBox(Integer.parseInt((String)curProduct.get("amountPerBox")));
      productToAdd.setSqFoot(Double.parseDouble((String)curProduct.get("sqFoot")));
      productToAdd.setSqFootTotal(Double.parseDouble((String)curProduct.get("sqFootTotal")));
      productToAdd.setInStock((String)curProduct.get("inStock"));
      productToAdd.setPrice((String)curProduct.get("price"));
      
      try {
        Main.allProducts.add(productToAdd);
      } catch(Exception e) {}
    }
  }
}
