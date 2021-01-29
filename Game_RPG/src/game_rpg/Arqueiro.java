import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

// Ataque = 15
// Defesa = 5
// Poder Especial - Causar 21 de dano divido em até 3 personagens (3 ataques de 7 de dano )

class Arqueiro extends Personagem{

  public Arqueiro(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  }
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){
    
    Scanner teclado = new Scanner(System.in);
    int openente1;
    int openente2;
    int openente3;

    // Tratamento de Exceções das variáveis Oponentes (Alvos Inválidos/Inexistentes)
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
    
    // Execução do Poder Especial
    personagem_partida.get(openente1).setVida(personagem_partida.get(openente1).getVida()-7);
    personagem_partida.get(openente2).setVida(personagem_partida.get(openente2).getVida()-7);
    personagem_partida.get(openente3).setVida(personagem_partida.get(openente3).getVida()-7);

    return true;
  }
}
