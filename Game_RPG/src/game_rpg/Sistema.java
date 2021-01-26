import java.util.*;

public class Sistema{
  
  // Pausa o Programa por Segundos
  static void esperar(){
    try { 
      Thread.sleep(2000); // 10000ms = 10s 
    } catch(InterruptedException ex){
      System.exit(0);
    }
  }
  
  // Limpa a Tela Pulando Linhas
  static void limparTela(){
    for(int x = 0; x < 100; x++){ System.out.printf("\n"); }
  }
  
  // Gera Sequência Aleatória de Personagens
  static Integer[] rodadaAleatoria(int quantidade_personagens){
    
    Integer[] vetorAleatorio = new Integer[quantidade_personagens];
    
    Random radom = new Random();

    for(int i = 0; i < quantidade_personagens; i++) {
      
      int numeroTmp = radom.nextInt(quantidade_personagens);

      boolean contains = Arrays.asList(vetorAleatorio).contains(numeroTmp);
        
      if (contains == false) {
        vetorAleatorio[i] = numeroTmp;
      }
      else {
        i--;
      }

    }

    return vetorAleatorio;
  }

}
