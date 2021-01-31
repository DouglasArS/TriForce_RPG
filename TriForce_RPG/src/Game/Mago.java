<<<<<<< HEAD:TriForce_RPG/src/Game/Mago.java
import java.util.Vector;

// Ataque = 10
// Defesa = 7
// Poder Especial - Regenera 25 de vida

class Mago extends Personagem{
  
  public Mago(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  }
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){
    
    // Execução do Poder Especial
    personagem_partida.get(jogador).setVida(personagem_partida.get(jogador).getVida()+25);

    return true;
  }

}
=======
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
>>>>>>> 36a30aeb154158d2f0c971f932ab1c488f0f72aa:Game_RPG/src/game_rpg/Mago.java
