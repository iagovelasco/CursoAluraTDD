package br.com.caelum.leilao.teste;



import org.junit.Test;

import org.junit.Assert;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	
	
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
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorDeTodos(),0.0001);
		
	}
	
	@Test
	public void testandoMedia(){
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Usuario jose = new Usuario("Jose");
		
		Leilao leilao = new Leilao("Playsation 3");
		
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(jose, 500.0));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(400, leiloeiro.getMedia(), 0000.1);
		
	}
}
