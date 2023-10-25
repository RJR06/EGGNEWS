package com.EGGNews.EGGNEWS.entidades;

import java.util.ArrayList;

public class Periodista extends Usuario {
    private ArrayList<Noticia> misNoticias;
    private Integer sueldoMensual;

    public Periodista() {
    }

    public ArrayList<Noticia> getMisNoticias() {
        return misNoticias;
    }

    public void setMisNoticias(ArrayList<Noticia> misNoticias) {
        this.misNoticias = misNoticias;
    }

    public Integer getSueldoMensual() {
        return sueldoMensual;
    }

    public void setSueldoMensual(Integer sueldoMensual) {
        this.sueldoMensual = sueldoMensual;
    }
}
