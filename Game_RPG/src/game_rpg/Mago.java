import java.util.*;
//poder especial-Regenera 25 de vida
class Mago extends Personagem{
  
  public Mago(String nome, int tribo, int classe){
    super(nome, tribo, classe);
  }
  
  public boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida){
     personagem_partida.get(jogador).setVida(personagem_partida.get(jogador).getVida()+25);

     return true;
  }

}
