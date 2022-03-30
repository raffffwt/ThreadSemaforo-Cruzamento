package Main;

import Controller.CruzamentoController;

public class Main {
	public static void main(String[] args) {
		for(int i = 0; i < 4; i++) {
			new CruzamentoController((i+1)).start();
		}
	}
}
