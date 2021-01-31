import java.util.*;

// Funcionalidade da Classe:
//  Servir como modelo para as Classes Arqueiro, Guerreiro, Mago.

public abstract class Personagem{
  private String nome;
  private int tribo; // Fogo [1] - Água [2] - Planta [3]
  private int classe; // Guerreiro [1] - Arqueiro [2] - Mago [3]

  private int vida = 50;
  private int ataque;
  private int defesa;
  private int defesaAtivada = 0; // [1] Ativada - [0] Desativada
  private int vidaRetirada = 0;
  private int percentualPowerUp = 0; // 1 Rodadas Ausentes

  public Personagem(String nome, int tribo, int classe){
    this.nome = nome;
    this.tribo = tribo;
    this.classe = classe;

    if (classe == 1) { // Guerreiro - Nivel Ataque e Defesa
      this.ataque = 20;
      this.defesa = 2;
    }
    else if (classe == 2) { // Arqueiro - Nivel Ataque e Defesa
      this.ataque = 15;
      this.defesa = 5;
    }
    else if (classe == 3){ // Mago - Nivel Ataque e Defesa
      this.ataque = 10;
      this.defesa = 7;
    }

  }

  // Get e Set Nome
  public String getNome(){
    return this.nome;
  }
  public void setNome(String nome){
    this.nome = nome;
  }

  // Get e Set Tribo
  public int getTribo(){
    return this.tribo; 
  }
  public void setTribo(int tribo){
    this.tribo = tribo;
  }
  
  // Get e Set Classe
  public int getClasse(){
    return this.classe; 
  }
  public void setClasse(int classe){
    this.classe = classe;
  }

  // Get e Set Vida
  public int getVida(){
    return this.vida; 
  }
  public void setVida(int vida){
    this.vida = vida;
  }

  // Get e Set Ataque
  public int getAtaque(){
    return this.ataque; 
  }
  public void setAtaque(int ataque){
    this.ataque = ataque;
  }

  // Get e Set Defesa
  public int getDefesa(){
    return this.defesa; 
  }
  public void setDefesa(int defesa){
    this.defesa = defesa;
  }

  // Get e Set Defesa
  public int getDefesaAtivada(){
    return this.defesaAtivada; 
  }
  public void setDefesaAtivada(int defesaAtivada){
    this.defesaAtivada = defesaAtivada;
  }
  
  // Get e Set Percentual Power Up
  public int getPercentualPowerUp(){
    return this.percentualPowerUp; 
  }
  public void setPercentualPowerUp(int percentualPowerUp){
    this.percentualPowerUp = percentualPowerUp;
  }

  // Get e Set vidaRetirada
  public int getVidaRetirada(){
    return this.vidaRetirada; 
  }
  public void setVidaRetirada(int vidaRetirada){
    this.vidaRetirada += vidaRetirada;
  }

  public abstract boolean poderEspecial(int jogador, Vector<Personagem> personagem_partida);

}
