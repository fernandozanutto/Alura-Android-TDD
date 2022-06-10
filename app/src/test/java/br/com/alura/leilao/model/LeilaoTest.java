package br.com.alura.leilao.model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import java.util.List;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao console = new Leilao("Console");
    private final Usuario usuarioFernando = new Usuario("Fernando");

    @Test
    public void test_DevolveDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    @Test
    public void test_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        double lanceDevolvidoConsole = console.getMaiorLance();

        assertEquals(200.0, lanceDevolvidoConsole, DELTA);
    }

    @Test
    public void test_DevolveMaiorLance_QuandoRecebeLancesOrdemCrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 10.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoComputador = console.getMaiorLance();
        assertEquals(200.0, lanceDevolvidoComputador, DELTA);
    }

    @Test
    public void test_DevolveMaiorLance_QUandoRecebeLancesOrdemDecrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioGiovanna, 200.0));
        console.proporLance(new Lance(usuarioFernando, 500.0));

        double lanceDevolvidoCarro = console.getMaiorLance();
        assertEquals(500.0, lanceDevolvidoCarro, DELTA);
    }

    @Test
    public void test_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        double lanceDevolvidoConsole = console.getMenorLance();

        assertEquals(200.0, lanceDevolvidoConsole, DELTA);
    }

    @Test
    public void test_DevolveMenorLance_QuandoRecebeLancesOrdemCrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 100.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoComputador = console.getMenorLance();
        assertEquals(100.0, lanceDevolvidoComputador, DELTA);
    }

    @Test
    public void test_DevolveTresMaioresLances_QuandoRecebeTresLances() {
        console.proporLance(new Lance(usuarioFernando, 200.0));
        console.proporLance(new Lance(new Usuario("Giovanna"), 300.0));
        console.proporLance(new Lance(new Usuario("Ganache"), 400.0));

        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(3, maioresLances.size());
        assertEquals(400.0, maioresLances.get(0).getValor(), DELTA);
        assertEquals(300.0, maioresLances.get(1).getValor(), DELTA);
        assertEquals(200.0, maioresLances.get(2).getValor(), DELTA);
    }


    @Test
    public void test_DevolveTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(0, maioresLances.size());
    }

    @Test
    public void test_DevolveTresMaioresLances_QuandoRecebeUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));
        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(1, maioresLances.size());
        assertEquals(200.0, maioresLances.get(0).getValor(), DELTA);
    }

    @Test
    public void test_DevolveTresMaioresLances_QuandoRecebeQuatroLances() {
        console.proporLance(new Lance(usuarioFernando, 250.0));
        console.proporLance(new Lance(new Usuario("Giovanna"), 270.0));
        console.proporLance(new Lance(new Usuario("Ganache"), 290.0));
        console.proporLance(new Lance(new Usuario("Jorge, O Rato"), 500.0));
        List<Lance> maioresLances = console.getTresMaioresLances();

        assertEquals(3, maioresLances.size());
        assertEquals(500.0, maioresLances.get(0).getValor(), DELTA);
        assertEquals(290.0, maioresLances.get(1).getValor(), DELTA);
        assertEquals(270.0, maioresLances.get(2).getValor(), DELTA);
    }

    @Test
    public void test_DevolveValorZeraParaMaiorLance_QuandoNaoTiverLance() {
        double maiorLance = console.getMaiorLance();
        assertEquals(0.0, maiorLance, DELTA);
    }

    @Test
    public void test_DevolveValorZeraParaMenorLance_QuandoNaoTiverLance() {
        double menorLance = console.getMenorLance();
        assertEquals(0.0, menorLance, DELTA);
    }

    @Test
    public void test_naoDeveAdicionarLance_QuandoForMenorQueMaiorLance() {
        console.proporLance(new Lance(usuarioFernando, 500.0));

        try {
            console.proporLance(new Lance(new Usuario("Giovanna"), 400.0));
            fail("Era esperado RuntimeException");
        } catch (RuntimeException exception) {
            assertEquals("Lance foi menor que o maior lance", exception.getMessage());
        }
    }

    @Test
    public void naoDeveAdicinarLance_QuandoForMesmoUsuarioDoUltimoLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        try {
            console.proporLance(new Lance(usuarioFernando, 300.0));
            fail("Era esperado RuntimeException");
        } catch (RuntimeException exception) {
            assertEquals("Mesmo usuário do último lance", exception.getMessage());
        }
    }

    @Test
    public void naoDeveAdicionarLance_QuandoUsuarioJaTiverCincoLances() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");
        console.proporLance(new Lance(usuarioFernando, 200.0));
        console.proporLance(new Lance(usuarioGiovanna, 300.0));
        console.proporLance(new Lance(usuarioFernando, 400.0));
        console.proporLance(new Lance(usuarioGiovanna, 500.0));
        console.proporLance(new Lance(usuarioFernando, 600.0));
        console.proporLance(new Lance(usuarioGiovanna, 700.0));
        console.proporLance(new Lance(usuarioFernando, 800.0));
        console.proporLance(new Lance(usuarioGiovanna, 900.0));
        console.proporLance(new Lance(usuarioFernando, 1000.0));
        console.proporLance(new Lance(usuarioGiovanna, 1100.0));

        try {
            console.proporLance(new Lance(usuarioFernando, 1200.0));
        } catch (RuntimeException e) {
            assertEquals("Usuário já deu cinco lances", e.getMessage());
        }
    }
}