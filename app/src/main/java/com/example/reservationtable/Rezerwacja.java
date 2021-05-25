package com.example.reservationtable;

import java.io.Serializable;

public class Rezerwacja implements Serializable {
    public String documentId;
    private String imie;
    private String nazwisko;
    private String stolik;
    private String godzina;
    private String data;
    private String email;
    private String telefon;
    private String ilosc;
    private String kod;

    public Rezerwacja(){

    }

    public Rezerwacja(String imie, String nazwisko, String stolik, String godzina, String ilosc,String telefon,String email,String data){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.stolik=stolik;
        this.godzina=godzina;
        this.ilosc=ilosc;
        this.telefon=telefon;
        this.data=data;
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId=documentId;
    }

    public String getImie(){
        return imie;
    }

    public void setImie(String imie){
        this.imie=imie;
    }

    public String getNazwisko(){
        return nazwisko;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }

    public String getStolik(){
        return stolik;
    }

    public void setStolik(String stolik){
        this.stolik=stolik;
    }

    public String getGodzina(){
        return godzina;
    }

    public void setGodzina(String godzina){
        this.godzina=godzina;
    }

    public void setIlosc(String ilosc) {
        ilosc = ilosc;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setTelefon(String telefon) {
        telefon = telefon;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public void setData(String data) {
        data = data;
    }

    public String getData() {
        return data;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        kod = kod;
    }
}
