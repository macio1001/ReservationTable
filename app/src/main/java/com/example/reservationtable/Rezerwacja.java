package com.example.reservationtable;

import java.io.Serializable;

public class Rezerwacja implements Serializable {
    public String documentId;
    private String Imie;
    private String Nazwisko;
    private String Stolik;
    private String Godzina;
    private String Data;
    private String Email;
    private String Telefon;
    private String Ilosc;
    private String Kod;

    public Rezerwacja(){

    }

    public Rezerwacja(String Imie, String Nazwisko, String Stolik, String Godzina, String Ilosc,String Telefon,String Email,String Data){
        this.Imie=Imie;
        this.Nazwisko=Nazwisko;
        this.Stolik=Stolik;
        this.Godzina=Godzina;
        this.Ilosc=Ilosc;
        this.Data=Data;
        this.Telefon=Telefon;
    }

    public String getDocumentId(){
        return documentId;
    }

    public void setDocumentId(String documentId){
        this.documentId=documentId;
    }

    public String getImie(){
        return Imie;
    }

    public void setImie(String Imie){
        this.Imie=Imie;
    }

    public String getNazwisko(){
        return Nazwisko;
    }

    public void setNazwisko(String Nazwisko){
        this.Nazwisko=Nazwisko;
    }

    public String getStolik(){
        return Stolik;
    }

    public void setStolik(String Stolik){
        this.Stolik=Stolik;
    }

    public String getGodzina(){
        return Godzina;
    }

    public void setGodzina(String Godzina){
        this.Godzina=Godzina;
    }

    public void setIlosc(String Ilosc) {
        this.Ilosc = Ilosc;
    }

    public String getIlosc() {
        return Ilosc;
    }

    public void setTelefon(String Telefon) {
        this.Telefon = Telefon;
    }

    public String getTelefon() {
        return Telefon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getData() {
        return Data;
    }

    public String getKod() {
        return Kod;
    }

    public void setKod(String Kod) {
        this.Kod = Kod;
    }
}
