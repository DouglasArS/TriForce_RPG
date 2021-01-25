//Poder espcial- Dano de 35 Perder 15
import java.util.*;

class Guerreiro extends Personagem{
  public Guerreiro(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  } 
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){

    Scanner teclado = new Scanner(System.in);

    System.out.printf("\n Digite seu oponente: ");
    int openente1 = teclado.nextInt()-1;

    personagem_partida.get(openente1).setVida(personagem_partida.get(openente1).getVida()-35);
    personagem_partida.get(jogador).setVida(personagem_partida.get(jogador).getVida()-15);

    return true;
  }
}
