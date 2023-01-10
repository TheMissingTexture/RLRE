import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Compression {
    public static void main(String[] args) throws IOException {
        byte[] data = getFile();
        ArrayList<String> binaryArray = convertBin(data);

    }

    public static byte[] getFile() throws IOException {
        Path path = Paths.get("C:\\Users\\dowli\\Desktop\\testfile.txt");
        byte[] data = Files.readAllBytes(path);
        return data;
    }

    public static ArrayList<String> convertBin(byte[] data){
        ArrayList<String> binaryArray = new ArrayList<>();

        for (int item : data) {
            String binary = "";
            int number = item;
            while (number > 0) {
                int remainder = number % 2;
                binary = remainder + binary;
                number /= 2;
            }
            binaryArray.add(binary);
        }
        return binaryArray;
    }
    public static void compress(ArrayList<String> binaryArray){
        ArrayList<String> finalCompression = new ArrayList<>();

        }
    }
    public static boolean checkUsed(ArrayList<String> current, String checkFor){
        for (String item : current){
            if (item == checkFor){
                return true;
            }
        }
        return false;
    }
}
