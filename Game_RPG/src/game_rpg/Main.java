import java.util.*;

class Main{
  public static void main(String[] args){

    while(true){
      
      Sistema.limparTela();

      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |                TRIFORCE               | ");
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |       [1]. JOGAR                      | ");
      System.out.printf("\n |       [2]. SAIR                       | ");
      System.out.printf("\n *---------------------------------------* ");

      Scanner teclado = new Scanner(System.in);

      System.out.printf("\n\n  Digite sua escolha: ");
      
      int respostaMenuPrincipal;
      //tratamento de erro
      try{
        respostaMenuPrincipal = teclado.nextInt();
      } catch (InputMismatchException e) {
        System.out.printf("\n\n   VALOR INVALIDO");
        Sistema.esperar();
        continue;
      }

      if (respostaMenuPrincipal == 1){
        int i = 1;

        System.out.printf("\n Digite a quantidade de jogadores: ");

        int numero_jogador;
        //tratamento de erro
        try{
          numero_jogador = teclado.nextInt();
        } catch (InputMismatchException e) {
          System.out.printf("\n\n   VALOR INVALIDO");
          System.out.printf("\n\n   VOLTANDO PARA MENU PRINCIPAL");
          Sistema.esperar();
          continue;
        }
        //tratamento de erro
        if (numero_jogador < 2){
          System.out.printf("\n\n  NUMERO DE JOGADORES INSUFICIENTE");
          System.out.printf("\n\n   VOLTANDO PARA MENU PRINCIPAL");
          Sistema.esperar();
          continue;
        }

        Batalha b = new Batalha();
        //tratamento de erro
        boolean personagemCarregados = true;

        while(true){ 
          if (i == (numero_jogador + 1)){
            break;
          }

          Sistema.limparTela();

          System.out.printf("\n *---------------------------------------* ");
          System.out.printf("\n |                JOGADOR %d              | ", i);
          System.out.printf("\n *---------------------------------------* ");
          System.out.printf("\n |       [1]. CRIAR NOVO PERSONAGEM      | ");
          System.out.printf("\n |       [2]. CARREGAR PERSONAGEM        | ");
          System.out.printf("\n |       [3]. EDITAR PERSONAGEM          | ");
          System.out.printf("\n |       [4]. SAIR                       | ");
          System.out.printf("\n *---------------------------------------* ");

          System.out.printf("\n\n  Digite sua escolha: ");
            
          int respostaMenuSecundario;
          // tratamento de erro
          try{
            respostaMenuSecundario = teclado.nextInt();
          } catch (InputMismatchException e) {
            System.out.printf("\n\n  VALOR INVALIDO");
            System.out.printf("\n\n  VOLTANDO PARA O MENU PRINCIPAL");
            personagemCarregados = false;
            Sistema.esperar();
            break;
          }  

          if (respostaMenuSecundario == 1){
            if (b.criarPersonagem() == false){
              System.out.printf("\n\n   VOLTANDO PARA O MENU DO JOGADOR %d", i);
              Sistema.esperar();
              i--;
            }

          }
          else if (respostaMenuSecundario == 2){
            System.out.printf("\n Carregando Personagem ");
          }
          else if (respostaMenuSecundario == 3){
            System.out.printf("\n Editar Personagem ");
          }
          else if (respostaMenuSecundario == 4){
            personagemCarregados = false;
            System.out.printf("\n\n  VOLTANDO PARA O MENU PRINCIPAL");
            Sistema.esperar();
            break;
          }
          else{
            personagemCarregados = false;
            System.out.printf("\n\n  VALOR INVALIDO");
            System.out.printf("\n\n  VOLTANDO PARA O MENU PRINCIPAL");
            Sistema.esperar();
            break;
          }

          i++; 
        }

        //tratamento de erro
        if (personagemCarregados == true){
          b.luta(); 
        }

      }
      else if (respostaMenuPrincipal == 2) {
        System.out.printf("\n SAINDO DO JOGO...");
        break;
      }
      else{
        System.out.printf("\n\n   VALOR INVALIDO");
        Sistema.esperar();
      }
      
    }

  }

}
