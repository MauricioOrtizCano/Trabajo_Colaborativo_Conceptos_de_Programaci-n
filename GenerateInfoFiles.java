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
        createSalesManInfoFile(5);
    }

    // Method to create document vendor file
    public static void createSalesManFile(int randomSalesCount, String name, long id) {
        try {
            FileWriter fileVendor = new FileWriter(name + "_" + id + ".txt");
            Random random = new Random();

            fileVendor.write("DocumentTypeSeller;" + id + "\n");
            for (int i = 0; i < randomSalesCount; i++) {
                fileVendor.write("ProductID" + i + 1 + ";" + (random.nextInt(10) + 1) + "\n");
            }

            fileVendor.close();
            System.out.println("Vendor document file created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating vendor document file.");
            e.printStackTrace();
        }
    }

    // Method to create file products information
    public static void createProductsFile(int priductsCount) {
        try {
            // Create a new file writer for the products file
            FileWriter fileProductName = new FileWriter("products.txt");
            Random random = new Random();

            for (int i = 0; i < priductsCount; i++) {
                fileProductName.write("ProductID" + (i + 1) + ";" + "PriductName" + (i + 1) + ";" + (random.nextInt(100) + 1) + "\n");
            }
            // Close the file writer
            fileProductName.close();
            System.out.println("Products information file created successfully.");
        } catch (IOException e) {
            System.err.println("Error creating products information file.");
            e.printStackTrace();
        }
    }

    public static void createSalesManInfoFile(int salesmanCount) {
        try {
            FileWriter fileSalesManInfo = new FileWriter("salesmen_info.txt");
            Random random = new Random();

            // Array with first names and last names
            String[] firstNames = {"Juan", "Paula", "Jorge", "Diego", "Hernan", "Carlos", "Isabel", "Yenny", "Daniel", "Laura"};
            String[] lastNames = {"Guzmán", "Pérez", "Cárdenas", "Paez", "Barrios", "Garcia", "Forero", "Gordillo", "Rodriguez", "Martinez"};

            // Write vendor information to the file
            for (int i = 0; i < salesmanCount; i++) {
                String firstName = firstNames[random.nextInt(firstNames.length)];
                String lastName = lastNames[random.nextInt(lastNames.length)];
                long documentNumber = 1000000 + i; // Pseudo-random document number
                fileSalesManInfo.write("CC;" + documentNumber + ";" + firstName + ";" + lastName + "\n");
            }

            fileSalesManInfo.close();
            System.out.println("Salesmen information file created successfully.");
        } catch (IOException e) {
            // Print error message if file creation fails
            System.err.println("Error creating salesmen information file.");
            e.printStackTrace();
        }
    }
}