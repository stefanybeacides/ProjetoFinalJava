# Sistema de Cadastro de Correntistas

Este projeto é um sistema de cadastro de correntistas que permite realizar operações como cadastrar, listar, editar e deletar correntistas, além de gerar relatórios em PDF. O sistema é composto por várias classes distribuídas em diferentes pacotes, cada uma com responsabilidades específicas.

## Estrutura do Projeto

### Pacote: `principal`
- **Classe: `Main`**
  - Método `Main` responsável por iniciar o sistema.

### Pacote: `telas`
- **Classe: `TelaInicial`**
  - Método `chamarTelaMenuInicial` inicializa a tela inicial com todos os componentes necessários (JFrame, JPanel, JLabel, JTextField, JButton, GridLayout, BufferedImage, ImageIcon, BorderLayout).
  - Adiciona o frame ao painel e chama a classe `ControllerTelaInicial`.
  - Responsável por receber a opção do usuário para realizar o cadastro de correntista.

- **Classe: `TelaSecundaria`**
  - Método `chamarTelaMenuSecundario` inicializa a tela secundária com todos os componentes necessários.
  - Adiciona o frame ao painel e chama a classe `ControllerTelaSecundaria`.
  - Responsável por dar ao usuário opções de cadastrar, listar, editar e deletar correntistas, além de encerrar o programa ou voltar à tela anterior.

- **Classe: `TelaCadastroCorrentista`**
  - Método `chamarTelaCadastroCorrentista` inicializa a tela de cadastro de correntista com todos os componentes necessários.
  - Adiciona um método `Override actionPerformed` para preenchimento automático de campos relacionados ao endereço ao digitar o CEP.
  - Responsável por receber os dados do usuário para o cadastro e possui um botão para voltar à tela anterior.

- **Classe: `TelaEditarCorrentista`**
  - Método `chamarTelaEditarCorrentista` inicializa a tela de edição de correntista com todos os componentes necessários.
  - Adiciona um método `Override actionPerformed` para preenchimento automático de campos relacionados ao endereço ao digitar o CEP.
  - Responsável por receber os novos dados do usuário e possui um botão para voltar à tela anterior.

### Pacote: `controller`
- **Classe: `ControllerTelaInicial`**
  - Adiciona atributos `JFrame`, `JTextField` e `JButton` para a criação do construtor.
  - Método `Override actionPerformed` para funcionalidade de acordo com a opção escolhida pelo usuário.
  - Direciona o usuário para um menu secundário ou encerra o programa.

- **Classe: `ControllerTelaSecundaria`**
  - Adiciona atributos `JFrame`, `JTextField` e `JButton` para a criação do construtor.
  - Método `Override actionPerformed` para funcionalidade de acordo com a opção escolhida pelo usuário.
  - Direciona o usuário para as telas de cadastro, lista, edição ou deleção de correntistas, ou encerra o programa.

- **Classe: `ControllerTelaCadastroCorrentista`**
  - Adiciona atributos `JFrame`, `JTextField` e `JButton` para a criação do construtor.
  - Método `Override actionPerformed` para salvar as informações no banco de dados ao clicar no botão `Enviar`.
  - Exibe mensagem de sucesso e limpa os campos para novo cadastro.

- **Classe: `ControllerTelaEditarCorrentista`**
  - Adiciona atributos `JFrame`, `JTextField` e `JButton` para a criação do construtor.
  - Método `Override actionPerformed` para salvar as novas informações no banco de dados ao clicar no botão `Enviar`.
  - Exibe mensagem de sucesso e fecha a tela de edição.

### Pacote: `entidade`
- **Classe: `Correntista`**
  - Classe abstrata com todos os atributos necessários para cadastro (Nome, CPF, CEP, Logradouro, Bairro, Cidade, UF, Email, etc.).
  - Métodos getter e setter para cada atributo.
  - Métodos para calcular limite de saque e limite de crédito.

- **Classe: `CorrentistaBasico` e `CorrentistaPremium`**
  - Herda de `Correntista`, reutilizando todos os atributos e métodos.
  - Realiza cálculos específicos para correntistas básicos (limite de saque) e premium (limite de crédito).

- **Classe: `Cep`**
  - Representa um endereço no formato do código postal brasileiro (CEP).
  - Contém atributos relacionados ao endereço e métodos getter e setter.

### Pacote: `service`
- **Classe: `BuscarCep`**
  - Permite buscar informações de endereço a partir de um CEP, consultando um serviço web (viacep.com.br).
  - Converte a resposta JSON em um objeto `Cep`.

### Pacote: `persistencia`
- **Classe: `FabricaConexao`**
  - Estabelece uma conexão com um banco de dados MySQL.
  - Trata exceções e gerencia credenciais e URL do banco de dados.

- **Classe: `DaoCorrentista`**
  - Realiza operações de CRUD (Create, Read, Update, Delete) no banco de dados para a entidade `Correntista`.
  - Usa a `FabricaConexao` para gerenciar conexões e preparar comandos SQL.

- **Classes: `DaoCorrentistaBasico` e `DaoCorrentistaPremium`**
  - Realiza operações de salvamento e recuperação de correntistas no banco de dados.
  - Usa a `FabricaConexao` para gerenciar conexões e executar comandos SQL.

### Pacote: `repositorio`
- **Interface: `CorrentistaBasicoRepositorio`**
  - Define um contrato para operações de persistência de correntistas básicos.
  - Declara métodos para salvar e listar correntistas básicos.

- **Classe: `CorrentistaBasicoRepositorioImp`**
  - Implementação concreta da interface `CorrentistaBasicoRepositorio`.
  - Fornece a lógica específica para as operações de persistência definidas pela interface.

- **Interface: `CorrentistaPremiumRepositorio`**
  - Define um contrato para operações de persistência e recuperação de dados de correntistas premium.
  - Declara métodos para salvar e listar correntistas premium.

- **Classe: `CorrentistaPremiumRepositorioImp`**
  - Implementação concreta da interface `CorrentistaPremiumRepositorio`.
  - Fornece a lógica específica para as operações de persistência definidas pela interface.

### Pacote: `arquivo`
- **Classe: `ManipuladorArquivo`**
  - Encapsula a lógica para criar um arquivo PDF detalhando informações de correntistas.
  - Oferece uma solução robusta para geração e visualização de relatórios em PDF.

- **Classe: `RodapeTemplate`**
  - Classe auxiliar que estende `PdfPageEventHelper` do iText para adicionar um rodapé padronizado em todas as páginas do documento PDF gerado.
  - Garante um rodapé consistente e informativo com detalhes como localização e data de geração do documento.

## Como Executar o Projeto

1. Clone o repositório.
2. Importe o projeto em sua IDE de preferência.
3. Configure a conexão com o banco de dados MySQL na classe `FabricaConexao`.
4. Execute a classe `Main` para iniciar o sistema.

## Tecnologias Utilizadas

- Java
- Swing (para interface gráfica)
- MySQL (para banco de dados)
- iText (para geração de PDFs)

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para obter mais informações.

---

Feito com 💻 e ☕ por [Seu Nome]
