package com.trabajofinal.razasypelajescercatomartinez.utils.caballos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CaballoModel {
    private String raza, pelaje,potrillo,padres;
    private String imagen;
    private Map<String, Integer[]> audio;

    public CaballoModel() {
        this.raza = "";
        this.pelaje = "";
        this.imagen = "";
        this.audio = new HashMap<>();
    }

    public CaballoModel(String raza, String pelaje, String imagen) {
        this.raza = raza;
        this.pelaje = pelaje;
        this.imagen = imagen;
    }
    public CaballoModel(String potrillo, String padres) {
        this.potrillo = potrillo;
        this.padres = padres;
    }
    public String getRaza() {
        return raza;
    }

    public String getName(){ return raza + " " + pelaje; }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPelaje() {
        return pelaje;
    }

    public void setPelaje(String pelaje) {
        this.pelaje = pelaje;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Map<String, Integer[]> getAudio() { return audio; }

    public void setAudio(Map<String, Integer[]> audio) { this.audio = audio; }

    public Integer getFemRazaAudio(){
        return audio.get("f")[0];
    }

    public Integer getFemPelajeAudio(){
        return audio.get("f")[1];
    }

    public ArrayList<Integer> getFemSounds(){
        ArrayList<Integer> res = new ArrayList<>();
        res.add(getFemRazaAudio());
        res.add(getFemPelajeAudio());
        return res;
    }
    public Integer getMascRazaAudio(){
        return audio.get("m")[0];
    }

    public Integer getMascPelajeAudio(){
        return audio.get("m")[1];
    }

    public ArrayList<Integer> getMaleSounds(){
        ArrayList<Integer> res = new ArrayList<>();
        res.add(getMascRazaAudio());
        res.add(getMascPelajeAudio());
        return res;
    }
    @Override
    public String toString() {
        return "Horse{" +
                "breed='" + raza + '\'' +
                ", fur='" + pelaje + '\'' +
                '}';
    }

    public String getPotrillo() {
        return potrillo;
    }

    public void setPotrillo(String potrillo) {
        this.potrillo = potrillo;
    }

    public String getPadres() {
        return padres;
    }

    public void setPadres(String padres) {
        this.padres = padres;
    }
}
