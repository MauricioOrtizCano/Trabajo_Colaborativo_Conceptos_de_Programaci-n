import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            // Read sales man information
            Map<Long, Map<String, Integer>> salesData = readSalesData("salesmen_info.txt");

            // Read Product information
            Map<String, Double> productPrices = readProductPrices("products.txt");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // This method allow us to read the sales of each sales men 
    public static Map<Long, Map<String, Integer>> readSalesData(String filename) throws IOException {
        Map<Long, Map<String, Integer>> salesData = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            long documentNumber = Long.parseLong(parts[1]);
            salesData.put(documentNumber, new HashMap<>()); // Inicializar mapa para ventas
        }
        reader.close();
        return salesData;
    }

    // This method allow us to read the product´s prices
    public static Map<String, Double> readProductPrices(String filename) throws IOException {
        Map<String, Double> productPrices = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            String productId = parts[0];
            double productPrice = Double.parseDouble(parts[2]);
            productPrices.put(productId, productPrice);
        }
        reader.close();
        return productPrices;
    }
}
