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

  
  // Salvar nomes no arquivo nomes Personagem
  public void salvarNomesArquivos(String nomesArquivos){

    try {
      FileWriter file = new FileWriter("ArquivosTexto/nomesPersonagem.txt", true);
      
      BufferedWriter buffWrite = new BufferedWriter(file);
      
      buffWrite.append(nomesArquivos + "\n");
  
      buffWrite.close();
      
      file.close();
    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo não encontrado");
    }
    catch (IOException e) {
      System.out.println("Erro na leitura do arquivo");
    }

  }


  public void lerArquivo(String caminho){

    try {
      
      Scanner file = new Scanner(new FileReader("ArquivosTexto/"+caminho+".txt"));
      
      String nome = file.nextLine();

      System.out.printf("\n Nome: %s\n", nome);

      int tribo = file.nextInt();

      if(tribo == 1){
        System.out.printf(" Tribo: Fogo\n");
      }
      else if(tribo == 2){
        System.out.printf(" Tribo: Água\n");
      }
      else if(tribo == 3){
        System.out.printf(" Tribo: Terra\n");
      }

      int classe = file.nextInt();
      
      if(classe == 1){
        System.out.printf(" Classe: Guerreiro\n");
      }
      else if(classe == 2){
        System.out.printf(" Classe: Arqueiro\n");
      }
      else if(classe == 3){
        System.out.printf(" Classe: Mago\n");
      }

      file.close();
    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo não encontrado1");
    }

  }
 
}
