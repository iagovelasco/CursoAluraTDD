package br.com.caelum.leilao.teste;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTest {

	
	@Test
	public void deveReceberLeilao(){
	
		Leilao leilao = new Leilao("MackBock 15");
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);
	}
	
	@Test
	public void deveReceberVariosLances(){
		Leilao leilao = new Leilao("mackbook");
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		leilao.propoe(new Lance(new Usuario("Wozniack"), 3000));
		
		assertEquals(2, leilao.getLances().size());
	}
	
	@Test
	public void naoDeveAceitar2LancesSeguidosDoMesmoUsuario(){
		Leilao leilao = new Leilao("mackbook");
		Usuario steveJobs = new Usuario("Steve Jobs");
		
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(steveJobs, 3000.0));
		
		assertEquals(1, leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(),0.00001 );
	
	}
	
	@Test
	public void naoDeveAceitar5LancesDoMesmoUsuario(){
		Leilao leilao = new Leilao("mackbook");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario bilGates = new Usuario("bill Gates");
		
		leilao.propoe(new Lance(steveJobs, 2000.0));
		leilao.propoe(new Lance(bilGates, 3000.0));
		
		leilao.propoe(new Lance(steveJobs, 4000.0));
		leilao.propoe(new Lance(bilGates, 5000.0));
		
		leilao.propoe(new Lance(steveJobs, 6000.0));
		leilao.propoe(new Lance(bilGates, 7000.0));
		
		leilao.propoe(new Lance(steveJobs, 8000.0));
		leilao.propoe(new Lance(bilGates, 9000.0));
		
		leilao.propoe(new Lance(steveJobs, 10000.0));
		leilao.propoe(new Lance(bilGates, 11000.0));
	
		leilao.propoe(new Lance(steveJobs, 12000.0));
	
		assertEquals(10, leilao.getLances().size());
		assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size()-1).getValor(), 0.00001);
		
	}
	
	 @Test
	    public void deveDobrarOUltimoLanceDado() {
	        Leilao leilao = new Leilao("Macbook Pro 15");
	        Usuario steveJobs = new Usuario("Steve Jobs");
	        Usuario billGates = new Usuario("Bill Gates");

	        leilao.propoe(new Lance(steveJobs, 2000));
	        leilao.propoe(new Lance(billGates, 3000));
	        leilao.dobraLance(steveJobs);

	        assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
	    }
	    @Test
	    public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
	        Leilao leilao = new Leilao("Macbook Pro 15");
	        Usuario steveJobs = new Usuario("Steve Jobs");

	        leilao.dobraLance(steveJobs);

	        assertEquals(0, leilao.getLances().size());
	    }

}
