import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        try {
            // Read sales man information
            Map<Long, Map<String, Integer>> salesData = readSalesData("salesmen_info.txt");

            // Read Product information
            Map<String, Double> productPrices = readProductPrices("products.txt");

            // Calculate total sales for each salesman
            Map<Long, Double> totalSales = calculateTotalSales(salesData, productPrices);

            // Sort salesmen by total sales (descending order)
            List<Map.Entry<Long, Double>> sortedSalesmen = new ArrayList<>(totalSales.entrySet());
            sortedSalesmen.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

            // Write sorted salesmen information to CSV file
            writeSalesmenReport(sortedSalesmen, salesData, productPrices, "salesmen_report.csv");

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

    // Calculate total sales for each salesman
    public static Map<Long, Double> calculateTotalSales(Map<Long, Map<String, Integer>> salesData, Map<String, Double> productPrices) {
        Map<Long, Double> totalSales = new HashMap<>();
        for (Map.Entry<Long, Map<String, Integer>> entry : salesData.entrySet()) {
            long salesmanId = entry.getKey();
            double total = 0;
            for (Map.Entry<String, Integer> productEntry : entry.getValue().entrySet()) {
                String productId = productEntry.getKey();
                int quantity = productEntry.getValue();
                double price = productPrices.getOrDefault(productId, 0.0);
                total += price * quantity;
            }
            totalSales.put(salesmanId, total);
        }
        return totalSales;
    }

    // Write sorted salesmen information to CSV file
    public static void writeSalesmenReport(List<Map.Entry<Long, Double>> sortedSalesmen, Map<Long, Map<String, Integer>> salesData, Map<String, Double> productPrices, String filename) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        for (Map.Entry<Long, Double> entry : sortedSalesmen) {
            long salesmanId = entry.getKey();
            double totalSales = entry.getValue();
            Map<String, Integer> sales = salesData.get(salesmanId);
            writer.write(salesmanId + ";" + totalSales + "\n");
            for (Map.Entry<String, Integer> productEntry : sales.entrySet()) {
                String productId = productEntry.getKey();
                int quantity = productEntry.getValue();
                double price = productPrices.getOrDefault(productId, 0.0);
                writer.write(productId + ";" + price + ";" + quantity + "\n");
            }
            writer.write("\n"); // Separador entre vendedores
        }
        writer.close();
    }
}

