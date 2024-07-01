package repositorio;

import java.util.List;

import entidade.CorrentistaPremium;

public interface CorrentistaPremiumRepositorio {
	
public boolean salvarCorrentista(CorrentistaPremium correntista);
	
	public List<CorrentistaPremium> listarCorrentistaRepositorio();

}
