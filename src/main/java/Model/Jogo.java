/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author deinfo
 */
public class Jogo {

    private String timeA;
    private String timeB;
    private Integer golA;
    private Integer golB;

    public Jogo() {
    }

    public Jogo(String linha) {
        String[] partes = linha.split(",");
        timeA = partes[0];
        timeB = partes[2];
        golA = Integer.parseInt(partes[1]);
        golB = Integer.parseInt(partes[3]);
    }

    public Jogo(String timeA, int golA, String timeB, int golB) {
        this.timeA = timeA;
        this.golA = golA;
        this.timeB = timeB;
        this.golB = golB;
    }

    public String getTimeA() {
        return timeA;
    }

    public String getTimeB() {
        return timeB;
    }

    public Integer getGolA() {
        return golA;
    }

    public Integer getGolB() {
        return golB;
    }

    @Override
    public String toString() {
        return "Jogo{" + "timeA=" + timeA + ", timeB=" + timeB + ", golA=" + golA + ", golB=" + golB + '}';
    }
    
    
}
