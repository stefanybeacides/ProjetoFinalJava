package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import entidade.Correntista;

public class DaoCorrentista {
	
		
		public Correntista buscarCorrentista(String cpf) {
			
			FabricaConexao conexaoFabricaConexao = new FabricaConexao(); 
			Connection connectionCorrentista = null; 
			PreparedStatement preparaOComandoSQL = null; 
			ResultSet resultadoTabelaBanco = null;
			Correntista correntista = new Correntista() {
			};


			String comandoSqlInsert = "SELECT 'correntista_basico' as tabela, \r\n"
					+ "       nome, cpf, cep, logradouro, bairro, cidade, uf, email, qtdtransacao, anuidade FROM correntista_basico WHERE cpf = ? UNION SELECT 'correntista_premium' as tabela, \r\n"
					+ "       nome, cpf, cep, logradouro, bairro, cidade, uf, email, qtdtransacao, anuidade FROM correntista_premium WHERE cpf = ?";
			try {
				connectionCorrentista = conexaoFabricaConexao.criarConexao(); 
				
				preparaOComandoSQL = connectionCorrentista.prepareStatement(comandoSqlInsert); 
				
				preparaOComandoSQL.setString(1, cpf); 
				preparaOComandoSQL.setString(2, cpf); 
				
				resultadoTabelaBanco = preparaOComandoSQL.executeQuery(); 
				
				while (resultadoTabelaBanco.next()) {
					correntista.setTabela(resultadoTabelaBanco.getString("tabela"));
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
				}
				
				
				
			} catch (Exception e) {
				
				
			}finally { 
				try {
					if (connectionCorrentista != null) {
						connectionCorrentista.close(); 
				
					}
					if (preparaOComandoSQL != null) {
						preparaOComandoSQL.close(); 
				
					}
					
					
					
				} catch (Exception e2) {
					
					
				}
				
			}
			
			
			return correntista;
			
		}
		
		public boolean atualizarCorrentistaNoBanco(Correntista correntista) {
			boolean salvamento = false;
			
			FabricaConexao conexaoFabricaConexao = new FabricaConexao(); 
			Connection connectionCorrentista = null; 
			PreparedStatement preparaOComandoSQL = null; 
			
			String comandoSqlUpdate = "UPDATE `cadastro_correntistas`.`"+ correntista.getTabela() +"` SET\r\n"
					+ "`nome` = ?,\r\n"
					+ "`cpf` = ?,\r\n"
					+ "`cep` = ?,\r\n"
					+ "`logradouro` = ?,\r\n"
					+ "`bairro` = ?,\r\n"
					+ "`cidade` = ?,\r\n"
					+ "`uf` = ?,\r\n"
					+ "`email` = ?,\r\n"
					+ "`qtdtransacao` = ?,\r\n"
					+ "`anuidade` = ?\r\n"
					+ "WHERE `cpf` = ?";
			try {
				connectionCorrentista = conexaoFabricaConexao.criarConexao(); 
				
				preparaOComandoSQL = connectionCorrentista.prepareStatement(comandoSqlUpdate); 
				
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
				preparaOComandoSQL.setString(11, correntista.getCpf()); 

				
				preparaOComandoSQL.executeUpdate(); 

				
				System.out.println("Correntista atualizado com sucesso!");
				
				salvamento = true; 
				
				
			} catch (Exception e) {
				
				
			}finally { 
				try {
					if (connectionCorrentista != null) {
						connectionCorrentista.close(); 
				
					}
					if (preparaOComandoSQL != null) {
						preparaOComandoSQL.close(); 
				
					}
					
					
					
				} catch (Exception e2) {
					
					
				}
				
			}
			
			
			return salvamento;
			
		}
		
		
		public boolean deletarCorrentistaNoBanco(Correntista correntista) {
			boolean salvamento = false;
			
			FabricaConexao conexaoFabricaConexao = new FabricaConexao(); 
			Connection connectionCorrentista = null; 
			PreparedStatement preparaOComandoSQL = null; 
			
			String comandoSqlDelete = "DELETE FROM `cadastro_correntistas`.`"+correntista.getTabela()+"` WHERE cpf = "+correntista.getCpf()+" ;";
			try {
				connectionCorrentista = conexaoFabricaConexao.criarConexao(); 
				preparaOComandoSQL = connectionCorrentista.prepareStatement(comandoSqlDelete); 
				preparaOComandoSQL.executeUpdate(); 

				
				System.out.println("Correntista deletado com sucesso!");
				
				salvamento = true; 
				
				
			} catch (Exception e) {
				
				
			}finally { 
				try {
					if (connectionCorrentista != null) {
						connectionCorrentista.close(); 
				
					}
					if (preparaOComandoSQL != null) {
						preparaOComandoSQL.close(); 
				
					}
					
					
					
				} catch (Exception e2) {
					
					
				}
				
			}
			
			
			return salvamento;
			
		}


}


