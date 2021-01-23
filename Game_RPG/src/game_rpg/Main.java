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
      
      int respostaMenuPrincipal = teclado.nextInt();

      if (respostaMenuPrincipal == 1){
        int i = 1;
        
        System.out.printf("\n Digite a quantidade de jogadores: ");
        
        int numero_jogador=teclado.nextInt();

        Batalha b = new Batalha();
        
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
      
          int respostaMenuSecundario = teclado.nextInt();

          if (respostaMenuSecundario == 1){
            b.criarPersonagem(); //criou arena b
          }
          else if (respostaMenuSecundario == 2){
            System.out.printf("\n Carregando Personagem ");
          }
          else if (respostaMenuSecundario == 3){
            System.out.printf("\n Editar Personagem ");
          }
          else{
            break;
          }
          i++; 
        }

        b.luta();

      }
      else {
        System.out.printf("\n SAINDO DO JOGO...");
        break;
      }
      
    }

  
  }

}
