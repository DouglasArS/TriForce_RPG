package game_rpg;

//metodo rodada aleatorio para iniciar a partida
//ataque defesa e power up
//interface
import java.util.*;

public class Batalha implements FuncionalidadesBatalha{
  
  Vector<Personagem> personagem_partida = new Vector<Personagem>(); // Quantidade de personagem para começar a partida

  @Override
  //ação ataque
  public void atacar(int atacante, int oponente){
    personagem_partida.get(oponente).vida -= personagem_partida.get(atacante).ataque;
  }

  @Override
  public void defender(int i){
    this.personagem_partida.get(i).defesaAtivada = 1;
  }

  @Override
  public void mostrarPersonagens(){
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |         PERSONAGEM NA PARTIDA         | ");
    System.out.printf("\n *---------------------------------------* ");

    for(int i = 0; i < personagem_partida.size(); i++){
      //após morrer não vai mais aparecer mesmo existindo no vetor
      if (personagem_partida.get(i).vida <= 0){
          continue;
        }
      
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n Persongem %d: \n", (i+1));
      
      System.out.printf("\n Nome: %s", personagem_partida.get(i).nome);
      System.out.printf("\n Tribo: %d", personagem_partida.get(i).tribo);
      System.out.printf("\n Classe: %d", personagem_partida.get(i).classe);
      System.out.printf("\n Vida: %d",personagem_partida.get(i).vida);
      System.out.printf("\n *---------------------------------------* ");
    }
    
  }

  //personagens vivos na partida vida>0
  @Override
  int personagemVivos(){
    int vivos = 0;

    for(int i = 0; i < personagem_partida.size(); i++){

      if (personagem_partida.get(i).vida > 0){
        vivos++;
      }
    
    }

    return vivos;
  }

  @Override
  public void treta(){
    Scanner teclado = new Scanner(System.in);

    while(personagemVivos() > 1){

      for(int i = 0; i < personagem_partida.size(); i++){
        this.mostrarPersonagens();

        if (personagem_partida.get(i).vida <= 0){
          continue;
        }

        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |                JOGADOR %d              | ", i+1);
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |       [1]. ATACAR                     | ");
        System.out.printf("\n |       [2]. DEFENDER                   | ");
        System.out.printf("\n |       [3]. POWER-UP                   | ");
        System.out.printf("\n *---------------------------------------* ");

        System.out.printf("\n Digite sua escolha: ");
        int escolha = teclado.nextInt();
        
        //Escolhendo personagem para atacar
        if (escolha == 1){ // Atacar
          System.out.printf("\n Digite seu oponente: ");
          int numOponente = teclado.nextInt();
          this.atacar(i, numOponente-1);

          if (personagemVivos() == 1){
            break;
          }
          
        }
        else if (escolha==2){
          this.defender(i);
        }

      }

    }

    // Mostrar o Vencedor da partida 
    for(int i = 0; i < personagem_partida.size(); i++){

      if (personagem_partida.get(i).vida > 1){
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |             VENCEDOR                  | ", i+1);
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n        NOME: %s                            ", personagem_partida.get(i).nome);
        System.out.printf("\n *---------------------------------------* ");
          
        break;
      }

    }
    
  }
  
  @Override
  public void criarPersonagem(){
    Personagem p = new Personagem();

    Scanner teclado = new Scanner(System.in);

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |           CRIANDO PERSONAGEM          | ");
    System.out.printf("\n *---------------------------------------* ");

    System.out.printf("\n\n Digite o nome do personagem: ");
    p.nome = teclado.next(); // input nome do personagem  
    
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |            TRIBOS                     | ");
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |       [1]. FOGO                       | ");
    System.out.printf("\n |       [2]. ÁGUA                       | ");
    System.out.printf("\n |       [3]. PLANTA                     | ");
    System.out.printf("\n *---------------------------------------* ");
            
    System.out.printf("\n\n Digite sua tribo: "); 
  
    p.tribo = teclado.nextInt();//input tribo

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |            CLASSES                    | ");
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |       [1]. GUERREIRO                  | ");
    System.out.printf("\n |       [2]. ARQUEIRO                   | ");
    System.out.printf("\n |       [3]. MAGO                       | ");
    System.out.printf("\n *---------------------------------------* ");
    
    System.out.printf("\n\n Digite sua classe: ");
    p.classe = teclado.nextInt();//input classe

    this.personagem_partida.add(p);
  }

}
