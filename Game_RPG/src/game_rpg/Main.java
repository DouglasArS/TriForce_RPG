import java.util.*;

class Main{
  public static void main(String[] args){

    while(true){
      
      Sistema.limparTela();

      // Menu Principal
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |                TRIFORCE               | ");
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |       [1]. JOGAR                      | ");
      System.out.printf("\n |       [2]. SAIR                       | ");
      System.out.printf("\n *---------------------------------------* ");

      Scanner teclado = new Scanner(System.in);

      System.out.printf("\n\n  Digite sua escolha: ");
      
      int respostaMenuPrincipal;
      
      // Tratamento de Exceções da variável respostaMenuPrincipal
      try{
        respostaMenuPrincipal = teclado.nextInt();
      } catch (InputMismatchException e) {
        System.out.printf("\n\n   VALOR INVALIDO");
        Sistema.esperar();
        continue;
      }

      if (respostaMenuPrincipal == 1){
        Batalha batalha = new Batalha(); // Objeto para acessar métodos da classe Batalha a partir da Main

        // Carregando Nomes dos Arquivo no Vetor Nomes_Arquivos
        batalha.getNomesArquivos();

        System.out.printf("\n Digite a quantidade de jogadores: ");

        int numeroJogador;
        
        // Tratamento de Exceções da variável numeroJogador
        try{
          numeroJogador = teclado.nextInt();
        } catch (InputMismatchException e) {
          System.out.printf("\n\n   VALOR INVALIDO");
          System.out.printf("\n\n   VOLTANDO PARA MENU PRINCIPAL");
          Sistema.esperar();
          continue;
        }
        if (numeroJogador < 2){
          System.out.printf("\n\n  NUMERO DE JOGADORES INSUFICIENTE");
          System.out.printf("\n\n   VOLTANDO PARA MENU PRINCIPAL");
          Sistema.esperar();
          continue;
        }

        // Tratamento de Erro
        // Jogo iniciará quando houver a certeza de que os Personagens foram carregados
        boolean personagemCarregados = true;

        int i = 1; // Variável de Controle do While - 1 até (Numero de Jogador + 1)
        
        while(true){ 
          if (i == (numeroJogador + 1)){
            break;
          }

          Sistema.limparTela();

          // Menu Secundario (Menu do Jogador)
          System.out.printf("\n *---------------------------------------* ");
          System.out.printf("\n |                JOGADOR %d              | ", i);
          System.out.printf("\n *---------------------------------------* ");
          System.out.printf("\n |       [1]. CRIAR NOVO PERSONAGEM      | ");
          System.out.printf("\n |       [2]. CARREGAR PERSONAGEM        | ");
          System.out.printf("\n |       [3]. EDITAR PERSONAGEM          | ");
          System.out.printf("\n |       [4]. DELETAR PERSONAGEM         | ");
          System.out.printf("\n |       [5]. SAIR                       | ");
          System.out.printf("\n *---------------------------------------* ");

          System.out.printf("\n\n  Digite sua escolha: ");
            
          int respostaMenuSecundario;

          // Tratamento de Exceções da variável respostaMenuSecundario
          try{
            respostaMenuSecundario = teclado.nextInt();
          } catch (InputMismatchException e) {
            System.out.printf("\n\n  VALOR INVALIDO");
            System.out.printf("\n\n  VOLTANDO PARA O MENU PRINCIPAL");
            personagemCarregados = false;
            Sistema.esperar();
            break;
          }

          // Criar personagem 
          if (respostaMenuSecundario == 1){
            if (batalha.criarPersonagem() == false){
              System.out.printf("\n\n   VOLTANDO PARA O MENU DO JOGADOR %d", i);
              Sistema.esperar();
              i--;
            }

          }
          // Carregar Personagem
          else if (respostaMenuSecundario == 2){
            if (batalha.carregarPersonagem() == false){
              System.out.printf("\n\n   VOLTANDO PARA O MENU DO JOGADOR %d", i);
              Sistema.esperar();
              i--;
            }
          }
          // Editar personagem 
          else if (respostaMenuSecundario == 3){
            if (batalha.editarPersonagem() == false){
              System.out.printf("\n\n   VOLTANDO PARA O MENU DO JOGADOR %d", i);
              Sistema.esperar();
            }
            i--;
          }
          // Excluir Personagem
          else if (respostaMenuSecundario == 4){
            if (batalha.excluirPersonagem() == false){
              System.out.printf("\n\n   VOLTANDO PARA O MENU DO JOGADOR %d", i);
              Sistema.esperar();
            }
            i--;
          }
          // Sair do Menu Secundario
          else if (respostaMenuSecundario == 5){
            personagemCarregados = false;
            System.out.printf("\n\n  VOLTANDO PARA O MENU PRINCIPAL");
            Sistema.esperar();
            break;
          }
          // Valor Default
          else{
            personagemCarregados = false;
            System.out.printf("\n\n  VALOR INVALIDO");
            System.out.printf("\n\n  VOLTANDO PARA O MENU PRINCIPAL");
            Sistema.esperar();
            break;
          }

          i++; 
        }

        // Tratamento de Erro
        // Jogo iniciará quando houver a certeza de que os Personagens foram carregados
        if (personagemCarregados == true){
          batalha.luta(); 
        }

      }
      // Sair Meu Principal (Sair do Jogo)
      else if (respostaMenuPrincipal == 2) {
        System.out.printf("\n SAINDO DO JOGO...");
        break;
      }
      // Valor Default
      else{
        System.out.printf("\n\n   VALOR INVALIDO");
        Sistema.esperar();
      }
      
    }

  }

}
