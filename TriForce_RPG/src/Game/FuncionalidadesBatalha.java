public interface FuncionalidadesBatalha
{
  public void atacar(int atacante, int oponente);
  public void defender(int jogador);
  public void powerUp(int jogador);
  public void mostrarPersonagens();
  public int personagemVivos();
  public void luta();
  public boolean criarPersonagem();
  public void getNomesArquivos();
  public void mostrarArquivos();
  public boolean carregarPersonagem();
  public boolean excluirPersonagem();
  public boolean editarPersonagem();
}
