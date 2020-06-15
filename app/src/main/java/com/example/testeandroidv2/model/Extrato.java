package com.example.testeandroidv2.model;

public class Extrato {
    private String descricaoPg;
    private String dataPg;
    private double valorPg;

    public Extrato() {
    }

    public Extrato(String dataPg, String descricaoPg, double valorPg) {
        this.descricaoPg = descricaoPg;
        this.valorPg = valorPg;
        this.dataPg = dataPg;
    }



    public String getDescricaoPg() {
        return descricaoPg;
    }

    public void setDescricaoPg(String descricaoPg) {
        this.descricaoPg = descricaoPg;
    }

    public double getValorPg() {
        return valorPg;
    }

    public void setValorPg(double valorPg) {
        this.valorPg = valorPg;
    }

    public String getDataPg() {
        return dataPg;
    }

    public void setDataPg(String dataPg) {
        this.dataPg = dataPg;
    }
}
