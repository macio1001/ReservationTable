package com.example.reservationtable;

public class RezerwacjaUtils {

    private static final String NAZWY_MIESIACY[]={"Styczeń","Luty","Marzec","Kwiecień","Maj","Czerwiec","Lipiec","Sierpień","Wrzesień","PaŹdziernik","Listopad","Grudzień"};
    private RezerwacjaUtils(){

    }

    public static String getNazwaMiesiaca(int miesiac){
        String nazwaMiesiaca=NAZWY_MIESIACY[miesiac];
        return nazwaMiesiaca;
    }

    public static String przygotujDate(int rok,int miesiac,int dzien){
        return dzien+" "+getNazwaMiesiaca(miesiac)+" "+rok;
    }
}
