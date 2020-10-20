package Dictionary;
import java.io.*;
import java.sql.SQLOutput;
import  java.util.Scanner;

public class DictionaryManagement {

    static Scanner scanner= new Scanner(System.in);
    public static Dictionary dictionary;

    public static void insertFromCommandline() {
        int n;
        n = Integer.parseInt(scanner.nextLine());
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        dictionary = new Dictionary(n);
        for(int i=0;i<n;i++){
            dictionary.words.get(i).setWord_target(scanner.nextLine());
            dictionary.words.get(i).setWord_explain(scanner.nextLine());
            }
        }

    public static void  insertFromFile() {
        dictionary = new Dictionary();
        int n = 0;
        try {

            File f = new File("dictionary.txt");
            FileReader fr = new FileReader(f);

            BufferedReader br = new BufferedReader(fr);

            String s;
            for(int i = 0;(s = br.readLine()) != null ; ++i){

                String[] words = s.split("\\t",2);
                    if (words.length==2) {
                        dictionary.addWord(words[0],words[1] );

                    }
                    else {
                        dictionary.addWord("Error reading","" );
                    }
            }
            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeToFile(){
        dictionary.sortWord();
        try {
            FileWriter fw = new FileWriter("dictionary.txt");
            for (int i = 0; i<dictionary.words.size();i++) {

                fw.write(dictionary.words.get(i).getWord_target() + "\t" + dictionary.words.get(i).getWord_explain() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryLookup() {
        String s;
        int n = dictionary.words.size();
        boolean bl = false;
        System.out.println("Enter the word you want to look up:");
        s = scanner.nextLine();

        for (int i = 0;i < n; i++){
            if (s.equalsIgnoreCase(dictionary.words.get(i).getWord_target())) {
                System.out.println(dictionary.words.get(i).getWord_explain());
                bl = true;
            }
        }
        if (!bl) System.out.println("Cannot find the word you want to look up!");
    }

    public static void editdata() {
        int n = dictionary.words.size();
        boolean bl=false;
        System.out.println("Enter the word you want to edit:");
        String s = scanner.nextLine();
        int m=-1;
        for (int i = 0; i < n; ++i) if (s.equalsIgnoreCase(dictionary.words.get(i).getWord_target())){
            System.out.println("Do you want to edit the target or explain?\n1. target\n2.explain\nEnter 0 để thoát!\n");
            m = scanner.nextInt();
            while (m != 1 && m != 2 && m !=0) {

                System.out.println("Vui lòng nhập 1 hoặc 2\n1. target\n2.explain\nEnter 0 để thoát!\n");
                m = scanner.nextInt();
            }
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            if(m == 1){
                System.out.println("bạn muốn sửa thành từ gì?");
                String temp = scanner.nextLine();
                dictionary.words.get(i).setWord_target(temp);
                bl = true;
            }
            else if (m == 2){
                System.out.println("bạn muốn sửa thành từ gì?");
                String temp = scanner.nextLine();
                dictionary.words.get(i).setWord_explain(temp);
                bl = true;
            }else break;
        }
        if (!bl && m==0) System.out.println("sửa từ thất bại!");
        else  if (!bl) System.out.println("không tìm được từ muốn sửa!");
        else System.out.println("sửa từ thành công!");

    }

    public static void addData() {
        System.out.println("nhập từ muốn thêm:");
        String target;
        target = scanner.nextLine();
        System.out.println("nhập từ muốn thêm:");
    }

    public static void  dictionarySearcher() {
        String s;
        boolean bl = false;
        System.out.println("Nhập từ muốn tìm:");
        s = scanner.nextLine();
        int n = dictionary.words.size();

        for (int i = 0;i < n; i++){
            String temp = "";
            if (s.length()<=dictionary.words.get(i).getWord_target().length()) temp = dictionary.words.get(i).getWord_target().substring(0,s.length());
            if (temp.equalsIgnoreCase(s)) {
                int max = "|English".length();
                System.out.print(dictionary.words.get(i).getWord_target());
                for(int j = dictionary.words.get(i).getWord_target().length(); j<max ;++j) System.out.print(" ");
                System.out.println("\t\t\t" + dictionary.words.get(i).getWord_explain());
                bl = true;
            }
        }
        if (!bl) System.out.println("Không tìm thấy từ muốn tìm!");

    }

}
