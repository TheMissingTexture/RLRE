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

        for(int item = 0; item<data.length; item++){
            String binary = "";
            int number = data[item];
            while (number>0){
                int remainder = number%2;
                binary = remainder+binary;
                number/=2;
            }
            binaryArray.add(binary);
        }
        return binaryArray;
    }
}
