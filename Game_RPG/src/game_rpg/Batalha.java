/*O QUE FALTA:
Ajeitar as esperas1 
tratamento de erro
salvar/ carregar/... ARQUIVOOO
GOGOGOGOG POWER RANGER
*/
import java.util.*;

public class Batalha implements FuncionalidadesBatalha{
  
  Vector<Personagem> personagem_partida = new Vector<Personagem>(); // Vetor Personagem da Partida

  // Ação Atacar
  @Override
  public void atacar(int atacante, int oponente){
    int ataqueFinal = personagem_partida.get(atacante).getAtaque();

    // Ataque Com Power Up Carregado
    if (personagem_partida.get(atacante).getPercentualPowerUp() == 1){
      ataqueFinal += ataqueFinal + 10;
      personagem_partida.get(atacante).setPercentualPowerUp(0);
    }

    // Ataque Crítico +5
    Random r = new Random();
    int probabilidadeAtaqueCritico = r.nextInt(10) + 1;

    if (probabilidadeAtaqueCritico <= 4){
      ataqueFinal += 5;
      System.out.printf("\n  ATAQUE CRITICO \n");
    }
    
    // Vantagem +5 | Desvantagem -5
    if((personagem_partida.get(atacante).getTribo()==1 && personagem_partida.get(oponente).getTribo()==3) ||      
       (personagem_partida.get(atacante).getTribo()==3 && personagem_partida.get(oponente).getTribo()==2) || 
       (personagem_partida.get(atacante).getTribo()==2 && personagem_partida.get(oponente).getTribo()==1)){
      
      ataqueFinal += 5;
    }
    else if((personagem_partida.get(oponente).getTribo()==1 && personagem_partida.get(atacante).getTribo()==3) ||  
            (personagem_partida.get(oponente).getTribo()==3 && personagem_partida.get(atacante).getTribo()==2) || 
            (personagem_partida.get(oponente).getTribo()==2 && personagem_partida.get(atacante).getTribo()==1)){
              
      ataqueFinal -= 5; 
    }
    
    // Certificando se a defesa do oponente está ativada
    if (personagem_partida.get(oponente).getDefesaAtivada() == 0){
      personagem_partida.get(oponente).setVida(personagem_partida.get(oponente).getVida() - ataqueFinal);

      System.out.printf("\n  %s ATACOU %s COM %d DE ATAQUE\n", personagem_partida.get(atacante).getNome(), personagem_partida.get(oponente).getNome(), ataqueFinal);

      Sistema.esperar();
    }
    else{
      System.out.printf("\n  %s ATACOU %s COM A DEFESA ATIVADA\n", personagem_partida.get(atacante).getNome(), personagem_partida.get(oponente).getNome());

      Sistema.esperar();
    }

    // Acrescentando Em Vida Retirada
    personagem_partida.get(atacante).setVidaRetirada(ataqueFinal);
  }


  // Ação Defender
  @Override
  public void defender(int jogador){

    // Defesa probabilística - Numero de 1 a 10
    Random r = new Random();
    int result = r.nextInt(10) + 1;

    if (result <= this.personagem_partida.get(jogador).getDefesa()){
      personagem_partida.get(jogador).setDefesaAtivada(1);
      System.out.printf("\n %s ATIVOU A DEFESA\n", personagem_partida.get(jogador).getNome());

      Sistema.esperar();
    }
    else{
      System.out.printf("\n %s NAO CONSEGUIU SE DEFENDER\n", personagem_partida.get(jogador).getNome());

      Sistema.esperar();
    }
  }


  // Ação Power Up
  @Override
  public void powerUp(int jogador){
    System.out.printf("\n %s CARREGANDO ATAQUE", personagem_partida.get(jogador).getNome());
    
    personagem_partida.get(jogador).setPercentualPowerUp(1);

    Sistema.esperar();
  }


  // Mostrar Status dos Jogadores - Cenário do Jogo
  @Override
  public void mostrarPersonagens(){

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |         PERSONAGENS NA PARTIDA        | ");
    System.out.printf("\n *---------------------------------------* ");

    for(int i = 0; i < personagem_partida.size(); i++){

      // Tira da Rodada os Jogadores Mortos
      if (personagem_partida.get(i).getVida() <= 0){
        continue;
      }
      
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n Jogador %d: \n", (i+1));
      
      System.out.printf("\n Nome: %s", personagem_partida.get(i).getNome());
      
      if (personagem_partida.get(i).getTribo() == 1){
        System.out.printf("\n Tribo: Fogo");         
      }else if(personagem_partida.get(i).getTribo() == 2){
        System.out.printf("\n Tribo: Água");
      }else if (personagem_partida.get(i).getTribo() == 3){
        System.out.printf("\n Tribo: Terra");
      }
      
      if (personagem_partida.get(i).getClasse() == 1){
        System.out.printf("\n Classe: Guerreiro");         
      }
      else if(personagem_partida.get(i).getClasse() == 2){
        System.out.printf("\n Classe: Arqueiro");
      }
      else if(personagem_partida.get(i).getClasse() == 3){
        System.out.printf("\n Classe: Mago");
      }
      
      System.out.printf("\n Vida: %d",personagem_partida.get(i).getVida());
      
      if (personagem_partida.get(i).getDefesaAtivada() == 1){
        System.out.printf("\n Defesa: ATIVADA");
      }
      else{
        System.out.printf("\n Defesa: DESATIVADA");
      }

      System.out.printf("\n *---------------------------------------* ");

    }
   
  }


  // Calcula Quantidade de Personagens Vivos
  @Override
  public int personagemVivos(){
    int vivos = 0;

    for(int i = 0; i < personagem_partida.size(); i++){

      if (personagem_partida.get(i).getVida() > 0){
        vivos++;
      }
    
    }

    return vivos;
  }


  // Começa a Batalha Entre os Personagens
  @Override
  public void luta(){
    Scanner teclado = new Scanner(System.in);
    int rodada = 0;
    
    while(personagemVivos() > 1){
      rodada += 1;

      // Vetor Sequência Aleatoria da Rodada
      Integer[] vetorAleatorio = Sistema.rodadaAleatoria(personagem_partida.size());

      Sistema.limparTela();

      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |         COMEÇANDO RODADA %d            | ", rodada);
      System.out.printf("\n *---------------------------------------* ");

      Sistema.esperar();
      
      for(int i = 0; i < personagem_partida.size(); i++){
        Sistema.limparTela();
        
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |              RODADA %d                 | ", rodada);
        System.out.printf("\n *---------------------------------------* ");

        this.mostrarPersonagens();

        if (personagem_partida.get(i).getVida() <= 0){
          continue;
        }

        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |                JOGADOR %d              | ", vetorAleatorio[i]+1);
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |       [1]. ATACAR                     | ");
        System.out.printf("\n |       [2]. DEFENDER                   | ");
        
        if (personagem_partida.get(vetorAleatorio[i]).getPercentualPowerUp() == 1){
          System.out.printf("\n |       [3]. POWER-UP - CARREGADO       | ");
        }
        else{
          System.out.printf("\n |       [3]. POWER-UP                   | ");
        }
      
        if (personagem_partida.get(vetorAleatorio[i]).getVidaRetirada() >= 100){
          System.out.printf("\n |       [4]. PODER ESPECIAL - CARREGADO | ");
        }
        else{
          System.out.printf("\n |       [4]. PODER ESPECIAL             | ");
        }

        System.out.printf("\n *---------------------------------------* ");
        

        System.out.printf("\n Digite sua escolha: ");
        int escolha = teclado.nextInt();
        
        // Escolhendo Atacar
        if (escolha == 1){ 
          System.out.printf("\n Digite seu oponente: ");
          int numOponente = teclado.nextInt();
          this.atacar(vetorAleatorio[i], numOponente-1);

          if (personagemVivos() == 1){
            break;
          }
          
        }
        // Escolhendo Defesa
        else if (escolha == 2){
          this.defender(vetorAleatorio[i]);
        }
        // Escolhendo Power Up
        else if (escolha == 3){
          this.powerUp(vetorAleatorio[i]);
        }
        // Escolhendo Poder Especial
        else if (escolha == 4){
          if (personagem_partida.get(vetorAleatorio[i]).getVidaRetirada() >= 100){
            personagem_partida.get(vetorAleatorio[i]).poderEspecial(vetorAleatorio[i], personagem_partida);
            personagem_partida.get(vetorAleatorio[i]).setVidaRetirada(-100);
          }
          else{
            System.out.printf("\n FALTA CAUSAR %d DANO PARA DESBLOQUEAR O ATAQUE ESPECIAL",personagem_partida.get(vetorAleatorio[i]).getVidaRetirada());
            i--;

            Sistema.esperar();
          }
        }

      }

      // Desativando Defesas ao final da rodada
      for(int j = 0; j < personagem_partida.size(); j++){
        if (personagem_partida.get(j).getDefesaAtivada() == 1){
          personagem_partida.get(j).setDefesaAtivada(0);
        }
      }

    }

    // Mostrar o Vencedor da partida 
    for(int i = 0; i < personagem_partida.size(); i++){

      if (personagem_partida.get(i).getVida() > 1){
        Sistema.limparTela();

        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |               VENCEDOR                | ");
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n          NOME: %s                           ",personagem_partida.get(i).getNome());
        System.out.printf("\n *---------------------------------------* ");

        Sistema.esperar();

        break;
      }

    }
    
  }
  
  // Criar Personagens
  @Override
  public void criarPersonagem(){
    
    Scanner teclado = new Scanner(System.in);

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |           CRIANDO PERSONAGEM          | ");
    System.out.printf("\n *---------------------------------------* ");

    System.out.printf("\n\n Digite o nome do personagem: ");
    String nome = teclado.next(); // input nome do personagem
    
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |            TRIBOS                     | ");
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |       [1]. FOGO                       | ");
    System.out.printf("\n |       [2]. ÁGUA                       | ");
    System.out.printf("\n |       [3]. PLANTA                     | ");
    System.out.printf("\n *---------------------------------------* ");
            
    System.out.printf("\n\n Digite sua tribo: "); 
  
    int tribo = teclado.nextInt(); // input tribo

    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n |               CLASSES                  | "); 
    System.out.printf("\n *----------------------------------------* "); 
    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n     NÍVEL:       | ATAQUE  | DEFESA |      "); 
    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n   [1]. GUERREIRO |   20    |   30   |      ");
    System.out.printf("\n   [2]. ARQUEIRO  |   15    |   50   |      ");
    System.out.printf("\n   [3]. MAGO      |   10    |   70   |      ");
    System.out.printf("\n *----------------------------------------* ");
    
    System.out.printf("\n\n Digite sua classe: ");
    int classe = teclado.nextInt();// input classe

    // Criando e Inicializando o p
    if (classe == 1){
      Guerreiro p = new Guerreiro(nome, tribo, classe);
      this.personagem_partida.add(p);
    }
    else if (classe == 2){
      Arqueiro p = new Arqueiro(nome, tribo, classe);
      this.personagem_partida.add(p);
    }
    else if(classe == 3){
      Mago p = new Mago(nome, tribo, classe);
      this.personagem_partida.add(p);
    }
    
  }

}
