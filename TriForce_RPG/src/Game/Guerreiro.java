import java.util.*;

// Ataque = 20
// Defesa = 7
// Poder Especial - Dano de 35 Perder 15

class Guerreiro extends Personagem{
  public Guerreiro(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  } 
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){
    Scanner teclado = new Scanner(System.in);

    int openente1;

    // Tratamento de Exceções da variável oponente1 (Alvo Inválido/Inexistente)
    try{
      System.out.printf("\n Digite seu oponente: ");
      openente1 = teclado.nextInt()-1;
      personagem_partida.get(openente1);
    } catch (InputMismatchException e) {
      System.out.printf("\n\n  OPONENTE INVALIDO");
      return false;
    } catch (ArrayIndexOutOfBoundsException e){
      System.out.printf("\n\n  OPONENTE INEXISTENTE");
      return false;
    }
    
    // Execução do Poder Especial
    personagem_partida.get(openente1).setVida(personagem_partida.get(openente1).getVida()-35);
    personagem_partida.get(jogador).setVida(personagem_partida.get(jogador).getVida()-15);

    return true;
  }
  
}
