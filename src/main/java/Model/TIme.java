/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deinfo
 */
public class TIme {

    private String nome;
    private int vitorias = 0;
    private int derrotas = 0;
    private int empates = 0;
    private int golPro = 0;
    private int golContra = 0;
    private byte clas = 0;

    List<Jogo> jogos = new ArrayList<Jogo>();
    
    public TIme(String nome, int vitorias, int derrotas, int empates, int golPro, int golContra) {
        this.nome = nome;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
        this.empates = empates;
        this.golPro = golPro;
        this.golContra = golContra;
    }

    public TIme() {
        this.nome = "não definido";
    }

    public TIme(String nome) {
        this.nome = nome;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGolPro() {
        return golPro;
    }

    public void setGolPro(int golPro) {
        this.golPro = golPro;
    }

    public int getGolContra() {
        return golContra;
    }

    public void setGolContra(int golContra) {
        this.golContra = golContra;
    }

    public byte getClas() {
        return clas;
    }

    public void setClas(byte clas) {
        this.clas = clas;
    }

    public void addVitoria() {
        this.vitorias += 1;
    }

    public void addEmpate() {
        this.empates += 1;
    }

    public void addDerrota() {
        this.derrotas += 1;
    }

    public void addGolPro(int gols) {
        this.golPro += gols;
    }

    public void addGolContra(int gols) {
        this.golContra += gols;
    }

    public int getSaldoGols() {
        return this.golPro - this.golContra;
    }

    public int getPontos() {
        return (this.vitorias * 3) + this.empates;
    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    
    
    @Override
    public String toString() {
        return nome;
    }

}
