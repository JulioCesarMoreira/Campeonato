
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilit;

import Model.TIme;
import Model.Jogo;
import com.sun.prism.impl.BufferUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author deinfo
 */
public class Dados {

    private String nomeArq;

    private BufferedReader br = null;
    private Jogo jgLinha;

    List<TIme> lstTimes = new ArrayList<>();

    private TIme achaTime(String nomeBusca) {

        for (TIme t : lstTimes) {
            if (t.getNome().equals(nomeBusca)) {
                return t;
            }
        }
        TIme novo = new TIme(nomeBusca);
        lstTimes.add(novo);
        return novo;
    }

    private void analisa(Jogo jg) {
        TIme timeA, timeB;
        timeA = achaTime(jg.getTimeA());
        timeB = achaTime(jg.getTimeB());

        timeA.getJogos().add(jg);
        timeB.getJogos().add(jg);

        if (jg.getGolA() > jg.getGolB()) { //time A ganhou
            timeA.addVitoria();
            timeB.addDerrota();
        } else if (jg.getGolA() < jg.getGolB()) { // time B ganhou
            timeB.addVitoria();
            timeA.addDerrota();
        } else {
            timeB.addEmpate();
            timeA.addEmpate();
        }
        timeA.addGolPro(jg.getGolA());
        timeB.addGolPro(jg.getGolB());
        timeA.addGolContra(jg.getGolB());
        timeB.addGolContra(jg.getGolA());

    }

    private void ordena() {
        int i, j;
        TIme aux;
        //metodo de inserção
        for (i = 1; i < lstTimes.size(); i++) {
            lstTimes.get(i);
            j = i;
            while (j > 0 && ((lstTimes.get(j).getPontos() > lstTimes.get(j - 1).getPontos())
                    || (lstTimes.get(j).getPontos() == lstTimes.get(j - 1).getPontos()
                    && lstTimes.get(j).getSaldoGols() > lstTimes.get(j - 1).getSaldoGols())
                    || (lstTimes.get(j).getPontos() == lstTimes.get(j - 1).getPontos()
                    && lstTimes.get(j).getVitorias() > lstTimes.get(j - 1).getVitorias()))) {
                aux = lstTimes.get(j - 1);
                lstTimes.set(j - 1, lstTimes.get(j));
                lstTimes.set(j, aux);
                j--;
            }
        }
    }

    public void gravaJson(TIme time) {
        try {
            FileWriter fw = null;
            String nomeArq = "C:\\dados\\" + time.getNome() + ".json";
            File file = new File(nomeArq);
            file.createNewFile();
            fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (Jogo j : time.getJogos()) {
                JSONObject eJSON = new JSONObject();
                eJSON.put("TimeA", j.getTimeA());
                eJSON.put("GolA", j.getGolA());
                eJSON.put("TimeB", j.getTimeB());
                eJSON.put("GolB", j.getGolB());
                bw.write(eJSON.toString() + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<TIme> ler() {
        String linha;
        try {
            br = new BufferedReader(new FileReader(nomeArq));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while ((linha = br.readLine()) != null) {
                
                jgLinha = new Jogo(linha);
                analisa(jgLinha);
                ordena();
                byte i = 1;
                for (TIme t : lstTimes) {
                    t.setClas(i);
                    i++;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Dados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstTimes;
        

    }
    
    public Dados(String nomeArq) {
        this.nomeArq = nomeArq;
        System.out.println(this.nomeArq);
    }

}
