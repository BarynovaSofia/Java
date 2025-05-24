package ua.pro.baynova.MockitoExample.lesson_2;

public class DictionaryService {
    private Translator translator;

    public DictionaryService(Translator translator){
        this.translator = translator;
    }

    public String translateWord(String word){
        return translator.translate(word);
    }
}
