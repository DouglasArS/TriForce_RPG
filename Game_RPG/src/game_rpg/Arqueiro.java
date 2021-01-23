import java.util.*;

//poder especial- Atacar  7 de vida para 3 adversários
//causar 21 de dano divido em 3 personagens
class Arqueiro extends Personagem{

  public Arqueiro(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  }
  
  public void poderEspecial(int jogador, Vector<Personagem> personagem_partida){
    
    Scanner teclado = new Scanner(System.in);

    System.out.printf("\n Digite seu primeiro oponente: ");
    int openente1 = teclado.nextInt()-1;

    System.out.printf("\n Digite seu segundo oponente: ");
    int openente2 = teclado.nextInt()-1;

    System.out.printf("\n Digite seu terceiro oponente: ");
    int openente3 = teclado.nextInt()-1;
    
    personagem_partida.get(openente1).setVida(personagem_partida.get(openente1).getVida()-7);
    personagem_partida.get(openente2).setVida(personagem_partida.get(openente2).getVida()-7);
    personagem_partida.get(openente3).setVida(personagem_partida.get(openente3).getVida()-7);
  }
}
