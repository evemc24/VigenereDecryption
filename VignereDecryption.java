import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class VignereDecryption {

    private static String cipher1 = "OAWCQOIWRE";
    private static String cipher2 = "UCGCVHEAWP";
    private static String textFile = "10letterwordslist.txt";
    private ArrayList<String> tenLetterWords = new ArrayList<String>();
    private ArrayList<String> keyList1 = new ArrayList<String>();
    private ArrayList<String> keyList2 = new ArrayList<String>();


    public VignereDecryption(String cipher1, String cipher2, String textFile) throws FileNotFoundException {
        System.out.println("Cipher Text 1 : " + cipher1);
        System.out.println("Cipher Text 2 : " + cipher2);

        // Add all ten letter words into array list
        ArrayList<String> tenLetterWords = new ArrayList<String>();

        File file = new File(textFile);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){
            tenLetterWords.add(sc.nextLine());
        }

        this.tenLetterWords = tenLetterWords;
        System.out.println("Word List created");


        createKeyList(cipher1);
        System.out.println("Possible keys for cipher1");
        createKeyList(cipher2);
        System.out.println("Possible keys for cipher2");

        ArrayList<String> keyMatch = new ArrayList<String>(this.keyList1);
        keyMatch.retainAll(keyList2);
        System.out.println("Key : " + keyMatch.get(0));

    }

    public void createKeyList(String cipher) {
        ArrayList<String> keyList = new ArrayList<String>();
        ArrayList<String> keyList2 = new ArrayList<String>();

        for (String word : tenLetterWords) {
            StringBuffer key = new StringBuffer();
            for (int i = 0; i < 10; i++) {

                int difference = cipher.charAt(i) - word.charAt(i);

                if (difference < 0) {
                    difference += 26;
                }

                char keyLetter = (char) (difference + 65);

                key.append(keyLetter);

            }

            keyList.add(key.toString());



        }

        if(cipher.contentEquals(cipher1)){
            this.keyList1 = keyList;
        } else if(cipher.contentEquals(cipher2)){
            this.keyList2 = keyList;
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        VignereDecryption decrypt = new VignereDecryption(cipher1, cipher2, textFile);
    }
}
