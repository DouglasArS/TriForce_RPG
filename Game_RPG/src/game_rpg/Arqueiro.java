import java.util.*;

//poder especial- Atacar  7 de vida para 3 adversários
//causar 21 de dano divido em 3 personagens
class Arqueiro extends Personagem{

  public Arqueiro(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  }
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){
    
    Scanner teclado = new Scanner(System.in);
    int openente1;
    int openente2;
    int openente3;

    // tratamento de erro
    try{
      System.out.printf("\n  Oponente1: ");
      openente1 = teclado.nextInt()-1;
      personagem_partida.get(openente1);

      System.out.printf("\n  Oponente2: ");
      openente2 = teclado.nextInt()-1;
      personagem_partida.get(openente2);

      System.out.printf("\n  Oponente3: ");
      openente3 = teclado.nextInt()-1;
      personagem_partida.get(openente3); 

    } catch (InputMismatchException e) {
      System.out.printf("\n\n  VALOR INVALIDO");
      return false;
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.printf("\n\n  OPONENTE INEXISTENTE");
      return false;
    }
    
    personagem_partida.get(openente1).setVida(personagem_partida.get(openente1).getVida()-7);
    personagem_partida.get(openente2).setVida(personagem_partida.get(openente2).getVida()-7);
    personagem_partida.get(openente3).setVida(personagem_partida.get(openente3).getVida()-7);

    return true;
  }
}
