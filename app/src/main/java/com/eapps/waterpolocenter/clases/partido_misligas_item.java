package com.eapps.waterpolocenter.clases;

/**
 * Created by Edu Hackintosh on 07/02/2016.
 */
public class partido_misligas_item {
    String local , visitante, periodo ,resultado;
    int escudol, escudov, jornada;
    String jugadoresl, jugadoresv, id;



    public partido_misligas_item(String local, String visitante, String periodo, String resultado, int escudol, int escudov, int jornada, String jugadoresl, String jugadoresv, String id) {
        this.local = local;
        this.visitante = visitante;
        this.periodo = periodo;
        this.resultado = resultado;
        this.escudol = escudol;
        this.escudov = escudov;
        this.jornada = jornada;
        this.jugadoresl = jugadoresl;
        this.jugadoresv = jugadoresv;
        this.id = id;


    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public int getEscudol() {
        return escudol;
    }

    public void setEscudol(int escudol) {
        this.escudol = escudol;
    }

    public int getEscudov() {
        return escudov;
    }

    public void setEscudov(int escudov) {
        this.escudov = escudov;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    public String getJugadoresl() {
        return jugadoresl;
    }

    public void setJugadoresl(String jugadoresl) {
        this.jugadoresl = jugadoresl;
    }

    public String getJugadoresv() {
        return jugadoresv;
    }

    public void setJugadoresv(String jugadoresv) {
        this.jugadoresv = jugadoresv;
    }
}
