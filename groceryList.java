import java.io.*;
import java.util.*;

public class groceryList {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        ArrayList<String> groceries = new ArrayList<>();
        
        while (true) {
            String item = scan.nextLine();
            if (item.equalsIgnoreCase("done")) {
                break;
            }
            groceries.add(item);
        }
        fileWriter(groceries, name);
    }

    public static void fileWriter(List<String> groceries, String name) {
        try (FileWriter fw = new FileWriter(name)) {
            for (String item : groceries) {
                fw.write(item);
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
