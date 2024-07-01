package entidade;

public class CorrentistaBasico extends Correntista {

	public Double calcularLimiteSaque(int qtdTransacao) {

		return qtdTransacao * 0.5;

	}
	
}
