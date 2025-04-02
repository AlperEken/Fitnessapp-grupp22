package model;

public class Konto {
    private String namn;
    private String efternamn;
    private String epost;
    private String lösenord;
    private int ålder;
    private double vikt;
    private String kön;
    private int dagligtMal;

    public Konto(String namn, String efternamn, String epost, String lösenord, int ålder, double vikt, String kön, int dagligtMal) {
        this.namn = namn;
        this.efternamn = efternamn;
        this.epost = epost;
        this.lösenord = lösenord;
        this.ålder = ålder;
        this.vikt = vikt;
        this.kön = kön;
        this.dagligtMal = dagligtMal;
    }

    public String getNamn() {
        return this.namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getEfternamn() {
        return this.efternamn;
    }

    public void setEfternamn(String efternamn) {
        this.efternamn = efternamn;
    }

    public String getEpost() {
        return this.epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getLösenord() {
        return this.lösenord;
    }

    public void setLösenord(String lösenord) {
        this.lösenord = lösenord;
    }

    public int getÅlder() {
        return this.ålder;
    }

    public void setÅlder(int ålder) {
        this.ålder = ålder;
    }

    public double getVikt() {
        return this.vikt;
    }

    public void setVikt(double vikt) {
        this.vikt = vikt;
    }

    public String getKön() {
        return this.kön;
    }

    public void setKön(String kön) {
        this.kön = kön;
    }

    public int getDagligtMal() {
        return this.dagligtMal;
    }

    public void setDagligtMal(int dagligtMal) {
        this.dagligtMal = dagligtMal;
    }
}