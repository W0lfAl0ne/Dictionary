package Dictionary;

import java.util.ArrayList;
public class Dictionary {
    public ArrayList<Word> words = new ArrayList<Word>();

    Dictionary(int n){
        for(int i=0;i<n;i++) words.add(new Word());
    }

    public Dictionary() {

    }

    public void addWord(String target, String explain){
        words.add(new Word());
        words.get(words.size()-1).setWord_target(target);
        words.get(words.size()-1).setWord_explain(explain);
    }

    public void sortWord(){
        for (int i = 0;i<words.size();i++)
            for (int j = i+1;j<words.size();j++)
                if (words.get(i).getWord_target().compareTo(words.get(j).getWord_target())>0) {
                    Word temp = words.get(i);
                    words.set(i,words.get(j));
                    words.set(j,temp);

                }
    }

}
