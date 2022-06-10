package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;
    private double menorLance = 0.0;

    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void proporLance(Lance lance) {
        if (lanceNaoValido(lance)) return;

        lances.add(lance);

        double valorLance = lance.getValor();

        if (definePrimeiroLance(valorLance)) return;

        Collections.sort(lances);

        calcularMaiorLance(valorLance);
        calcularMenorLance(valorLance);
    }

    private boolean definePrimeiroLance(double valorLance) {
        if (lances.size() == 1) {
            maiorLance = valorLance;
            menorLance = valorLance;
            return true;
        }
        return false;
    }

    private boolean lanceNaoValido(Lance lance) {
        double valorLance = lance.getValor();

        if (lanceMenorQueOUltimo(valorLance)) throw new RuntimeException("Lance foi menor que o maior lance");

        if (!lances.isEmpty()) {
            Usuario usuarioNovo = lance.getUsuario();
            if (usuarioMesmoQueUltimoLance(usuarioNovo)) throw new RuntimeException("Mesmo usuário do último lance");

            if (cincoLancesDoUsuario(usuarioNovo)) {
                throw new RuntimeException("Usuário já deu cinco lances");
            }
        }
        return false;
    }

    private boolean cincoLancesDoUsuario(Usuario usuarioNovo) {
        int lancesDoUsuario = 0;
        for (Lance l : lances) {
            Usuario usuarioExistente = l.getUsuario();
            if (usuarioExistente.equals(usuarioNovo)) {
                lancesDoUsuario++;
                if (lancesDoUsuario == 5) return true;
            }
        }
        return false;
    }

    private boolean usuarioMesmoQueUltimoLance(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();

        return usuarioNovo.equals(ultimoUsuario);
    }

    private boolean lanceMenorQueOUltimo(double valorLance) {
        return maiorLance > valorLance;
    }

    private void calcularMenorLance(double valorLance) {
        if (valorLance < menorLance) {
            menorLance = valorLance;
        }
    }

    private void calcularMaiorLance(double valorLance) {
        if (valorLance > maiorLance) {
            maiorLance = valorLance;
        }
    }

    public double getMenorLance() {
        return menorLance;
    }

    public double getMaiorLance() {
        return maiorLance;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Lance> getTresMaioresLances() {
        int endIndex = Math.min(3, lances.size());
        return lances.subList(0, endIndex);
    }

    public int getQuantidadeLances() {
        return lances.size();
    }
}
