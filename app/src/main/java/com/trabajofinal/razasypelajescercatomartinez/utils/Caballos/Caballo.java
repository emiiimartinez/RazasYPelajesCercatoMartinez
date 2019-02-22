package com.trabajofinal.razasypelajescercatomartinez.utils.Caballos;

public class Caballo {
    private String raza, pelaje;
    private Integer imagen;
    private Integer[] audio;

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getPelaje() {
        return pelaje;
    }

    public void setPelaje(String pelaje) {
        this.pelaje = pelaje;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    public Integer[] getAudio() {
        return audio;
    }

    public void setAudio(Integer[] audio) {
        this.audio = audio;
    }
}
