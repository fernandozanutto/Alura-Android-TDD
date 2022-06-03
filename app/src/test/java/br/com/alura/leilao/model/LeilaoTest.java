package br.com.alura.leilao.model;

import junit.framework.TestCase;

public class LeilaoTest extends TestCase {

    private final Leilao console = new Leilao("Console");
    private final Usuario usuarioFernando = new Usuario("Fernando");


    public void test_DevolveDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = console.getDescricao();

        assertEquals("Console", descricaoDevolvida);
    }

    public void test_DevolveMaiorLance_QuandoRecebeApenasUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        double lanceDevolvidoConsole = console.getMaiorLance();

        assertEquals(200.0, lanceDevolvidoConsole, 0.00001);
    }

    public void test_DevolveMaiorLance_QuandoRecebeLancesOrdemCrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 10.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoComputador = console.getMaiorLance();
        assertEquals(200.0, lanceDevolvidoComputador, 0.0001);
    }

    public void test_DevolveMaiorLance_QUandoRecebeLancesOrdemDecrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 500.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoCarro = console.getMaiorLance();
        assertEquals(500.0, lanceDevolvidoCarro, 0.0001);
    }

    public void test_DevolveMenorLance_QuandoRecebeApenasUmLance() {
        console.proporLance(new Lance(usuarioFernando, 200.0));

        double lanceDevolvidoConsole = console.getMenorLance();

        assertEquals(200.0, lanceDevolvidoConsole, 0.00001);
    }

    public void test_DevolveMenorLance_QuandoRecebeLancesOrdemCrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 100.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoComputador = console.getMenorLance();
        assertEquals(100.0, lanceDevolvidoComputador, 0.0001);
    }

    public void test_DevolveMenorLance_QUandoRecebeLancesOrdemDecrescente() {
        Usuario usuarioGiovanna = new Usuario("Giovanna");

        console.proporLance(new Lance(usuarioFernando, 500.0));
        console.proporLance(new Lance(usuarioGiovanna, 200.0));

        double lanceDevolvidoCarro = console.getMenorLance();
        assertEquals(200.0, lanceDevolvidoCarro, 0.0001);
    }
}