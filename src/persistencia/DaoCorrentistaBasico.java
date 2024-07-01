package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidade.Correntista;
import entidade.CorrentistaBasico;

public class DaoCorrentistaBasico {
		
		public boolean salvarCorrentistaNoBanco(Correntista correntista) {
			boolean salvamento = false;
			
			FabricaConexao conexaoFabricaConexao = new FabricaConexao(); 
			Connection connectionCorrentistaBasico = null; 
			PreparedStatement preparaOComandoSQL = null; 
			
			String comandoSqlInsert = "insert into " + correntista.getTabela() + " (nome, cpf, cep, logradouro, bairro, cidade, uf, email, qtdtransacao, anuidade, limitesaque) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			try {
				connectionCorrentistaBasico = conexaoFabricaConexao.criarConexao(); 
				
				preparaOComandoSQL = connectionCorrentistaBasico.prepareStatement(comandoSqlInsert); 
				
				preparaOComandoSQL.setString(1, correntista.getNome()); 
				preparaOComandoSQL.setString(2, correntista.getCpf()); 
				preparaOComandoSQL.setString(3, correntista.getCep()); 
				preparaOComandoSQL.setString(4, correntista.getLogradouro());
				preparaOComandoSQL.setString(5, correntista.getBairro());
				preparaOComandoSQL.setString(6, correntista.getCidade());
				preparaOComandoSQL.setString(7, correntista.getUf());
				preparaOComandoSQL.setString(8, correntista.getEmail());
				preparaOComandoSQL.setInt(9, correntista.getQtdTransacao());
				preparaOComandoSQL.setDouble(10, correntista.getValorAnuidade());
				preparaOComandoSQL.setDouble(11, correntista.getCalcularLimiteSaque());
				
				preparaOComandoSQL.execute(); 

				
				
				
				salvamento = true; 
				
				
			} catch (Exception e) {
				
				
			}finally { 
				try {
					if (connectionCorrentistaBasico != null) {
						connectionCorrentistaBasico.close(); 
				
					}
					if (preparaOComandoSQL != null) {
						preparaOComandoSQL.close(); 
				
					}
					
					
					
				} catch (Exception e2) {
					
					
				}
				
			}
			
			
			return salvamento;
			
		}


	public static List<Correntista> retornoListaCorrentista(String tipoCorrentista) {
		
		String comandoSqlBuscarCorrentista = "SELECT * FROM correntista_basico";
		List<Correntista> listaCorrentista = new ArrayList<>();
		FabricaConexao conexaoFabricaConexao = new FabricaConexao();
		
		
		Connection connectionExemplo = null;
		PreparedStatement preparaOComandoSQL = null;
		ResultSet resultadoTabelaBanco = null;
		
		try {
			
			connectionExemplo = conexaoFabricaConexao.criarConexao();
			preparaOComandoSQL = connectionExemplo.prepareStatement(comandoSqlBuscarCorrentista);
			resultadoTabelaBanco = preparaOComandoSQL.executeQuery();
			
			while (resultadoTabelaBanco.next()) {
				Correntista correntista = new Correntista() {
				};
				
				correntista.setNome(resultadoTabelaBanco.getString("nome"));
				correntista.setCpf(resultadoTabelaBanco.getString("cpf"));
				correntista.setCep(resultadoTabelaBanco.getString("cep"));
				correntista.setLogradouro(resultadoTabelaBanco.getString("logradouro"));
				correntista.setBairro(resultadoTabelaBanco.getString("bairro"));
				correntista.setCidade(resultadoTabelaBanco.getString("cidade"));
				correntista.setUf(resultadoTabelaBanco.getString("uf"));
				correntista.setEmail(resultadoTabelaBanco.getString("email"));
				correntista.setQtdTransacao(resultadoTabelaBanco.getInt("qtdtransacao"));
				correntista.setValorAnuidade(resultadoTabelaBanco.getDouble("anuidade"));
				correntista.setCalcularLimiteSaque(resultadoTabelaBanco.getDouble("limitesaque"));
				correntista.setTabela("correntista_basico");
				
				listaCorrentista.add(correntista);
				
			}
			
			
		} catch (Exception e) {
			
			
		}finally {
			try {
				if (connectionExemplo != null) {
					connectionExemplo.close();
				}
				if (preparaOComandoSQL != null) {
					preparaOComandoSQL.close();
				}
				
			} catch (Exception e2) {
				
				
			}
		}
		
		
		return listaCorrentista;
	}
	
}
