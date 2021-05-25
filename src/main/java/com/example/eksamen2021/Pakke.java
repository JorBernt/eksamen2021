package com.example.eksamen2021;

public class Pakke {
    private String fornavn, etternavn, postnr, adresse, telefonnr, epost, volum, vekt;

    public Pakke(){}

    public Pakke(String fornavn, String etternavn, String adresse, String telefonnr, String epost, String postnr, String volum, String vekt) {
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.adresse = adresse;
        this.telefonnr = telefonnr;
        this.epost = epost;
        this.postnr = postnr;
        this.volum = volum;
        this.vekt = vekt;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelefonnr() {
        return telefonnr;
    }

    public void setTelefonnr(String telefonnr) {
        this.telefonnr = telefonnr;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getVolum() {
        return volum;
    }

    public void setVolum(String volum) {
        this.volum = volum;
    }

    public String getVekt() {
        return vekt;
    }

    public void setVekt(String vekt) {
        this.vekt = vekt;
    }
}
