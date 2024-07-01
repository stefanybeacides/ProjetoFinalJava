package repositorio;

import java.util.List;

import entidade.CorrentistaBasico;

public interface CorrentistaBasicoRepositorio {
	
public boolean salvarCorrentista(CorrentistaBasico correntista);
	
	public List<CorrentistaBasico> listarCorrentistaRepositorio();

}
