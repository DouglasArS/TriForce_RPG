import java.io.*;

public class ManipuladorArquivos{
  
  public String getQuantidadeArquivo(){
    String quantidadeArquivo = "";
    
    try {
      FileReader file = new FileReader("ArquivosTexto/QuantidadeArquivos.txt");
            
      BufferedReader br = new BufferedReader(file);

      quantidadeArquivo = br.readLine();

      file.close();
    }
    catch (FileNotFoundException e){
        System.out.println("Arquivo não encontrado1");
    }
    catch (IOException e ) {
        System.out.println("Erro na leitura do arquivo1");
    }

    return quantidadeArquivo;
  }

  public void setQuantidadeArquivo(){
    int quantidadeArquivo = 0;
    
    try {
      FileReader file = new FileReader("ArquivosTexto/QuantidadeArquivos.txt");
            
      BufferedReader br = new BufferedReader(file);
            
      quantidadeArquivo = br.read();

      file.close();
    }
    catch (FileNotFoundException e){
        System.out.println("Arquivo não encontrado1");
    }
    catch (IOException e ) {
        System.out.println("Erro na leitura do arquivo1");
    }
    
    try {
      FileWriter file = new FileWriter("ArquivosTexto/QuantidadeArquivos.txt");
      
      BufferedWriter buffWrite = new BufferedWriter(file);
      
      quantidadeArquivo++;

      buffWrite.write(quantidadeArquivo);
      buffWrite.newLine();
        
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
  
  public void salvarPersonagem(String nome, int tribo, int classe){
    String i = this.getQuantidadeArquivo();

    try {
      FileWriter file = new FileWriter("ArquivosTexto/arquivo"+i+".txt");
      
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

    this.setQuantidadeArquivo();

  }

}
