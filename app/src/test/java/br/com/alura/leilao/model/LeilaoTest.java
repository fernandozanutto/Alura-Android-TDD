package br.com.alura.leilao.model;

import junit.framework.TestCase;

import java.util.List;

public class LeilaoTest extends TestCase {

    public static final double DELTA = 0.0001;
    private final Leilao console = new Leilao("Console");
    private final Usuario usuarioFernando = new Usuario("Fernando");

    public void test_DevolveDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    public void test_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        double lanceDevolvidoConsole = console.getMaiorLance();

        assertEquals(200.0, lanceDevolvidoConsole, DELTA);
    }

    public void test_DevolveMaiorLance_QuandoRecebeLancesOrdemCrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 10.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoComputador = console.getMaiorLance();
        assertEquals(200.0, lanceDevolvidoComputador, DELTA);
    }

    public void test_DevolveMaiorLance_QUandoRecebeLancesOrdemDecrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 500.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoCarro = console.getMaiorLance();
        assertEquals(500.0, lanceDevolvidoCarro, DELTA);
    }

    public void test_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        double lanceDevolvidoConsole = console.getMenorLance();

        assertEquals(200.0, lanceDevolvidoConsole, DELTA);
    }

    public void test_DevolveMenorLance_QuandoRecebeLancesOrdemCrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 100.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoComputador = console.getMenorLance();
        assertEquals(100.0, lanceDevolvidoComputador, DELTA);
    }

    public void test_DevolveMenorLance_QuandoRecebeLancesOrdemDecrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 500.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoCarro = console.getMenorLance();
        assertEquals(200.0, lanceDevolvidoCarro, DELTA);
    }

    public void test_DevolveTresMaioresLances_QuandoRecebeTresLances() {
        console.proporLance(new Lance(usuarioFernando, 200.0));
        console.proporLance(new Lance(usuarioFernando, 400.0));
        console.proporLance(new Lance(usuarioFernando, 300.0));
        console.proporLance(new Lance(usuarioFernando, 150.0));

        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(3, maioresLances.size());
        assertEquals(400.0, maioresLances.get(0).getValor(), DELTA);
        assertEquals(300.0, maioresLances.get(1).getValor(), DELTA);
        assertEquals(200.0, maioresLances.get(2).getValor(), DELTA);
    }


    public void test_DevolveTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(0, maioresLances.size());
    }

    public void test_DevolveTresMaioresLances_QuandoRecebeUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));
        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(1, maioresLances.size());
        assertEquals(200.0, maioresLances.get(0).getValor(), DELTA);
    }

    public void test_DevolveTresMaioresLances_QuandoRecebeQuatroLances() {
        console.proporLance(new Lance(usuarioFernando, 250.0));
        console.proporLance(new Lance(usuarioFernando, 270.0));
        console.proporLance(new Lance(usuarioFernando, 500.0));
        console.proporLance(new Lance(usuarioFernando, 290.0));
        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(3, maioresLances.size());
        assertEquals(500.0, maioresLances.get(0).getValor(), DELTA);
        assertEquals(290.0, maioresLances.get(1).getValor(), DELTA);
        assertEquals(270.0, maioresLances.get(2).getValor(), DELTA);
    }
}