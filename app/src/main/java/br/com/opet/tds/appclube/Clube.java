package br.com.opet.tds.appclube;

/**
 * Created by Diego on 03/04/2017.
 */

public class Clube {
    private long ID;
    private String nome;
    private String cidade;
    private int ano;

    public Clube() {
    }

    public Clube(long ID, String nome, String cidade, int ano) {
        this.ID = ID;
        this.nome = nome;
        this.cidade = cidade;
        this.ano = ano;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
