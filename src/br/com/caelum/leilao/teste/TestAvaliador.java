package br.com.caelum.leilao.teste;



import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TestAvaliador {
	
	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario jose;
	
	@Before
	public void criaAvaliaor(){
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("Joao");
		this.maria = new Usuario("Maria");
		this.jose = new Usuario("Jose");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemNenhumLance(){
		Leilao leilao = new	CriadorDeLeilao().para("Paly").constroi();
		
		leiloeiro.avalia(leilao);
	}
	 
	
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
	
		leiloeiro.avalia(leilao);
		
		
		double maoirEsperado = 400;
		double menorEsperado = 250;
		
		
		//Tamanho do erro aceitavel(0000.1)
		Assert.assertEquals(maoirEsperado, leiloeiro.getMaiorLance(),  0.0001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(),0.0001);
		
	}
	
	@Test
	public void deveEncontrarOsTresMaiores(){
					
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));
		
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

		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(1, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(200, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.00001);
	 }
	
	@Test
	public void valorRandomico(){
			
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(jose, 120.0));
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(maria, 630.0));
		leilao.propoe(new Lance(jose, 230.0));
	
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		
		assertEquals(3, maiores.size());
		assertEquals(700, maiores.get(0).getValor(), 0.00001);
		assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120, leiloeiro.getMenorLance(), 0.00001);
	 }
	
	@Test
	public void ordemDescrescente(){
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(jose, 200.0));
		leilao.propoe(new Lance(joao, 100.0));

		leiloeiro.avalia(leilao);
		
        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	 }
	
	 @Test
	    public void deveEncontrarOsTresMaioresLances() {
	      
		 Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
	                .lance(joao, 100.0)
	                .lance(maria, 200.0)
	                .lance(joao, 300.0)
	                .lance(maria, 400.0)
	                .constroi();

	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(3, maiores.size());
	        assertEquals(400, maiores.get(0).getValor(), 0.00001);
	        assertEquals(300, maiores.get(1).getValor(), 0.00001);
	        assertEquals(200, maiores.get(2).getValor(), 0.00001);
	    }

	    @Test
	    public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
	      
	        Leilao leilao = new Leilao("Playstation 3 Novo");

	        leilao.propoe(new Lance(joao, 100.0));
	        leilao.propoe(new Lance(maria, 200.0));

	        leiloeiro.avalia(leilao);

	        List<Lance> maiores = leiloeiro.getTresMaiores();

	        assertEquals(2, maiores.size());
	        assertEquals(200, maiores.get(0).getValor(), 0.00001);
	        assertEquals(100, maiores.get(1).getValor(), 0.00001);
	    }

}
