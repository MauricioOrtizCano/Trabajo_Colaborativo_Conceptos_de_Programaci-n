// We must import FileWriter Method because we need to write in text files
import java.io.FileWriter;
// We must import IOException method to handle exceptions
import java.io.IOException;
//
import java.util.Random;


public class GenerateInfoFiles {

    public static void main(String[] args) {
        createSalesManFile(5, "Nombre del vendedor", 100);
        createProductsFile(10);
    }

    
    // Method to create document vendor file
    public static void createSalesManFile(int randomSalesCount, String name, long id) {
        try {
            FileWriter fileVendor = new FileWriter(name + "_" + id + ".txt");
            Random random = new Random();

            fileVendor.write("DocumentTypeSeller;" + id + "\n");
            for(int i = 0; i < randomSalesCount; i++) {
                fileVendor.write("ProductID" + i+1 + ";" + (random.nextInt(10) + 1) + "\n");
            }

            fileVendor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Method to create file products information
    public static void createProductsFile(int priductsCount) {
        try {
            FileWriter fileProductName = new FileWriter("products.txt");
            Random random = new Random();

            for(int i = 0; i < priductsCount; i++) {
                fileProductName.write("ProductID" + (i+1) + ";" + "PriductName" + (i+1) + ";" + (random.nextInt(100) + 1) + "\n");
            }

            fileProductName.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}