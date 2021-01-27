import java.io.*;
import java.util.*;

public class ManipuladorArquivos{
  // Salvando Personagem
  
  public void salvarPersonagem(String nome, int tribo, int classe){
    
    try {
      FileWriter file = new FileWriter("ArquivosTexto/"+nome+".txt");
      
      BufferedWriter buffWrite = new BufferedWriter(file);
      
      buffWrite.append(nome + "\n");
      buffWrite.append(tribo + "\n");
      buffWrite.append(classe + "\n");
        
      buffWrite.close();
      file.close();
    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo não encontrado2");
    }
    catch (IOException e) {
      System.out.println("Erro na leitura do arquivo1");
    }

  }
  
}
