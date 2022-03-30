package Controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class CruzamentoController extends Thread {
	public static int sentido;
	String direcao;
	public static Semaphore cruzamento = new Semaphore(1);
	
	int distancia = 1000;
	
	int _carroId;
	
	public CruzamentoController(int carroId) {
		this._carroId = carroId;
	}
	
	public void run() {
		try {
			this.cruzamento();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cruzamento() throws InterruptedException {
		Random random = new Random();
		CruzamentoController.sentido = random.nextInt(1, 4);
		
		switch(CruzamentoController.sentido) {
			case(1): {
				this.direcao = "esquerda";
				break;
			}
			case(2):{
				this.direcao = "direita";
				break;
			}
			case(3):{
				this.direcao = "cima";
				break;
			}
			case(4):{
				this.direcao = "baixo";
				break;
			}
		}
		
		int distanciaPercorrida = 0;
		Random random2 = new Random();
		
		int velocidade = random2.nextInt(30, 60);
		
		while(this.distancia >= 0) {
			this.distancia -= velocidade;
			distanciaPercorrida += velocidade;
			
			if(distanciaPercorrida > 1000) {
				distanciaPercorrida = 1000;
			}
			
			System.out.println(String.format("Carro %d andou %d metros no sentido %s", this._carroId, distanciaPercorrida, this.direcao));
			
			Thread.sleep(500);
		}
		
		CruzamentoController.cruzamento.acquire();
		this.passarCruzamento();
		CruzamentoController.cruzamento.release();
		
	}
	
	public void passarCruzamento() {
		System.out.println(String.format("Carro %d passou pelo cruzamento", this._carroId));
	}
	
}
