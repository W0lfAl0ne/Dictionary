package Dictionary;
public class DictionaryCommandline {

    void  showAllWords(){
        // số cặp word của list;
        int n = DictionaryManagement.dictionary.words.size();

        int max = "|English".length();
        for(int i=0; i<n; i++) if (DictionaryManagement.dictionary.words.get(i).getWord_target().length()>max) max = DictionaryManagement.dictionary.words.get(i).getWord_target().length();

        System.out.print("No\t|English");
        for(int i = "|English".length(); i<max; ++i) System.out.print(" ");
        System.out.println("\t\t\t|Vietnamese ");

        for(int i=0; i<n ; i++) {
            System.out.print((i+1) + "\t" + DictionaryManagement.dictionary.words.get(i).getWord_target());
            for(int j = DictionaryManagement.dictionary.words.get(i).getWord_target().length(); j<max ;++j) System.out.print(" ");
            System.out.println("\t\t\t" + DictionaryManagement.dictionary.words.get(i).getWord_explain());
        }
    }
    public void dictionaryBasic(){
        DictionaryManagement.insertFromFile();
        //showAllWords();

    }
    public void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile();
        showAllWords();
        DictionaryManagement.dictionaryLookup();
        DictionaryManagement.dictionarySearcher();
        DictionaryManagement.editdata();
    }

}
