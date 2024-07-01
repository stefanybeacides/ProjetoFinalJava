package entidade;

public class CorrentistaPremium extends Correntista {
	
	public Double calcularLimiteCredito(int qtdTransacao) {
		
		return qtdTransacao * 0.7;

	}

}
