package ua.pro.baynova.MockitoExample.lesson_8;

public class PostalService {

    public boolean validateAddress(String address){
        return address != null && address.contains("ул.");
    }

    public void sendLetter(String address, String message){
        if (!validateAddress(address)){
            throw new IllegalArgumentException("Адрес некорректный");
        }
        System.out.println("Письмо отправлено на адрес: " + address + " с сообщением: " + message);
    }
}
