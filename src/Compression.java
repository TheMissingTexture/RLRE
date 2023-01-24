import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Compression {
    public static void main(String[] args) throws IOException {
        byte[] data = getFile();
        ArrayList<String> binaryArray = convertBin(data);
        ArrayList<String> hexList = convertHex(binaryArray);
        ArrayList<String> finalCompression = compress(hexList);
        ArrayList<String> rleArray = RLE(finalCompression);
        writeFinal(rleArray);

    }

    public static byte[] getFile() throws IOException {
        Path path = Paths.get("C:\\Users\\dowli\\Desktop\\TestFiles\\testfile2.rtf");
        return Files.readAllBytes(path);
    }

    public static ArrayList<String> convertHex(ArrayList<String> binData) {
        HashMap<String, String> binToHex = new HashMap<>();
        ArrayList<String> hexList = new ArrayList<>();
        binToHex.put("0000", "A");
        binToHex.put("0001", "B");
        binToHex.put("0010", "C");
        binToHex.put("0011", "D");
        binToHex.put("0100", "E");
        binToHex.put("0101", "F");
        binToHex.put("0110", "G");
        binToHex.put("0111", "J");
        binToHex.put("1000", "K");
        binToHex.put("1001", "L");
        binToHex.put("1010", "M");
        binToHex.put("1011", "N");
        binToHex.put("1100", "O");
        binToHex.put("1101", "P");
        binToHex.put("1110", "Q");
        binToHex.put("1111", "R");

        for (String item : binData) {
            String itema = item.substring(0, item.length() / 2);
            String itemb = item.substring(item.length() / 2);
            hexList.add(binToHex.get(itema) + binToHex.get(itemb));
        }

        return hexList;
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
            if (binary.length() != 8){
                binary = ("0".repeat(8-binary.length()) + binary);
            }
            binaryArray.add(binary);
        }
        return binaryArray;
    }

    public static boolean checkUsed(ArrayList<String> current, String checkFor){
        for (String item : current){
            if (Objects.equals(item, checkFor)){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<String> compress(ArrayList<String> binaryArray) {
        ArrayList<String> finalCompression = new ArrayList<>();
        HashMap<String, String> translations = new HashMap<>();

        int counter = 0;
        String code = "";

        for (String item : binaryArray){
            if (code.length() == 8){
                if (checkUsed(finalCompression, code)){
                    finalCompression.add(translations.get(code));
                    code = "";
                }
                else {
                    finalCompression.add(code);
                    translations.put(code, String.valueOf(counter));
                    counter += 1;
                    code = "";
                }
            }
            else {
                                                                                                                                                                                                                     code = code + item;
            }
        }
        return finalCompression;
    }

    public static ArrayList<String> RLE(ArrayList<String> finalCompression){
        ArrayList<String> rleArray = new ArrayList<>();

        String using = "";
        int counter = 1 ;

        for (String item : finalCompression){
            if (item == using){
                counter += 1;
            }
            else {
                if (counter == 1){
                    rleArray.add(using);
                }
                else{
                    rleArray.add(using + " " + counter);

                }
                using = item;
                counter = 1;
            }
        }
        return rleArray;
    }

    public static void writeFinal(ArrayList<String> finalCompression) throws IOException {
        File makeFile = new File("C:\\Users\\dowli\\Desktop\\TestFiles\\compressed2.txt");
        FileWriter compressed = new FileWriter("C:\\Users\\dowli\\Desktop\\TestFiles\\compressed2.txt");
        for (String item : finalCompression){
            compressed.write(item + ",");
        }
        compressed.close();
    }

}
