package br.com.caelum.leilao.teste;



import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.Assert;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TestAvaliador {
	
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jose");
		
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		
		double maoirEsperado = 400;
		double menorEsperado = 250;
		
		
		//Tamanho do erro aceitavel(0000.1)
		Assert.assertEquals(maoirEsperado, leiloeiro.getMaiorLance(),  0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(),0.0001);
		
	}
	
	@Test
	public void deveEncontrarOsTresMaiores(){
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
			
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
	    assertEquals(300, maiores.get(1).getValor(), 0.00001);
	    assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void validarUmLance(){
		Usuario joao = new Usuario("Joao");
	
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 200.0));
	
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(1, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(200, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.00001);
	 }
	
	@Test
	public void valorRandomico(){
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jose");
	
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(jose, 120.0));
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(maria, 630.0));
		leilao.propoe(new Lance(jose, 230.0));
	
	
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(700, maiores.get(0).getValor(), 0.00001);
		assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120, leiloeiro.getMenorLance(), 0.00001);
	 }
	
	@Test
	public void ordemDescrescente(){
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jose");
	
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(jose, 200.0));
		leilao.propoe(new Lance(joao, 100.0));
	
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	 }
	
	 @Test
	    public void deveEncontrarOsTresMaioresLances() {
	        Usuario joao = new Usuario("João");
	        Usuario maria = new Usuario("Maria");
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        leilao.propoe(new Lance(joao, 100.0));
	        leilao.propoe(new Lance(maria, 200.0));
	        leilao.propoe(new Lance(joao, 300.0));
	        leilao.propoe(new Lance(maria, 400.0));

	        Avaliador leiloeiro = new Avaliador();
	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(3, maiores.size());
	        assertEquals(400, maiores.get(0).getValor(), 0.00001);
	        assertEquals(300, maiores.get(1).getValor(), 0.00001);
	        assertEquals(200, maiores.get(2).getValor(), 0.00001);
	    }

	    @Test
	    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
	        Usuario joao = new Usuario("João");
	        Usuario maria = new Usuario("Maria");
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        leilao.propoe(new Lance(joao, 100.0));
	        leilao.propoe(new Lance(maria, 200.0));

	        Avaliador leiloeiro = new Avaliador();
	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(2, maiores.size());
	        assertEquals(200, maiores.get(0).getValor(), 0.00001);
	        assertEquals(100, maiores.get(1).getValor(), 0.00001);
	    }

	    @Test
	    public void deveDevolverListaVaziaCasoNaoHajaLances() {
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        Avaliador leiloeiro = new Avaliador();
	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(0, maiores.size());
	    }
}