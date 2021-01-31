import java.util.*;

// Ataque = 10
// Defesa = 20
// Poder Especial - Regenera 35 de vida

class Mago extends Personagem{
  
  public Mago(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  }
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){
    
    // Execução do Poder Especial
    personagem_partida.get(jogador).setVida(personagem_partida.get(jogador).getVida()+35);

    return true;
  }

}
