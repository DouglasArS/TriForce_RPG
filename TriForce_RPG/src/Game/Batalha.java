import java.util.*;
import java.io.*;

// Funcionalidade da Classe:
//  Realizar e coordenar as ações dos personagens durante a batalha

public class Batalha implements FuncionalidadesBatalha{
  
  Vector<Personagem> personagem_partida = new Vector<Personagem>(); // Vetor Personagens da Partida
  
  Vector<String> nomes_arquivos = new Vector<String>(); // Vetor Nomes dos Arquivos
  
  // Ação Atacar
  @Override
  public void atacar(int atacante, int oponente){
    int ataqueFinal = personagem_partida.get(atacante).getAtaque();

    // Ataque Com Power Up Carregado
    if (personagem_partida.get(atacante).getPercentualPowerUp() == 1){
      ataqueFinal += ataqueFinal + 10;
      personagem_partida.get(atacante).setPercentualPowerUp(0);
    }

    // Ataque Crítico +5 - Ativação Probabilística (40% de ser Ativado)
    Random r = new Random();
    int probabilidadeAtaqueCritico = r.nextInt(10) + 1;

    if (probabilidadeAtaqueCritico <= 4){
      ataqueFinal += 5;
      System.out.printf("\n  ATAQUE CRITICO \n");
    }
    
    // Estabelecendo Vantagens (+5) e Desvantagem (-5) entre Tribos
    if((personagem_partida.get(atacante).getTribo()==1 && personagem_partida.get(oponente).getTribo()==3) ||      
       (personagem_partida.get(atacante).getTribo()==3 && personagem_partida.get(oponente).getTribo()==2) || 
       (personagem_partida.get(atacante).getTribo()==2 && personagem_partida.get(oponente).getTribo()==1)){
      
      ataqueFinal += 5;
    }
    else if((personagem_partida.get(oponente).getTribo()==1 && personagem_partida.get(atacante).getTribo()==3) ||  
            (personagem_partida.get(oponente).getTribo()==3 && personagem_partida.get(atacante).getTribo()==2) || 
            (personagem_partida.get(oponente).getTribo()==2 && personagem_partida.get(atacante).getTribo()==1)){
              
      ataqueFinal -= 5; 
    }
    
    // Certificando a Defesa do Oponente
    if (personagem_partida.get(oponente).getDefesaAtivada() == 0){
      personagem_partida.get(oponente).setVida(personagem_partida.get(oponente).getVida() - ataqueFinal);

      System.out.printf("\n  %s ATACOU %s COM %d DE ATAQUE\n", personagem_partida.get(atacante).getNome(), personagem_partida.get(oponente).getNome(), ataqueFinal);

      Sistema.esperar();
    }
    else{
      // Retirando do Ataque o Nivel de Defesa do Oponente
      ataqueFinal -= personagem_partida.get(oponente).getDefesa();

      if (ataqueFinal > 0){
        personagem_partida.get(oponente).setVida(personagem_partida.get(oponente).getVida() - ataqueFinal);
        
        System.out.printf("\n  %s ATACOU %s (DEFESA ATIVADA) COM %d DE ATAQUE\n", personagem_partida.get(atacante).getNome(), personagem_partida.get(oponente).getNome(), ataqueFinal);
      }
      else{
        System.out.printf("\n  %s DEFENDEU COMPLETAMENTE O ATAQUE\n", personagem_partida.get(oponente).getNome());
      }

      Sistema.esperar();
    }

    // Acrescentando Dano em Vida Retirada (Para a ativação do Poder Especial)
    personagem_partida.get(atacante).setVidaRetirada(ataqueFinal);
  }


  // Ação Defender
  @Override
  public void defender(int jogador){
    personagem_partida.get(jogador).setDefesaAtivada(1);
    System.out.printf("\n %s ATIVOU A DEFESA\n", personagem_partida.get(jogador).getNome());

    Sistema.esperar();
  }


  // Ação Power Up
  @Override
  public void powerUp(int jogador){
    System.out.printf("\n %s CARREGANDO ATAQUE", personagem_partida.get(jogador).getNome());
    
    personagem_partida.get(jogador).setPercentualPowerUp(1);

    Sistema.esperar();
  }


  // Mostrar Status dos Jogadores - Cenário do Jogo
  @Override
  public void mostrarPersonagens(){

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |         PERSONAGENS NA PARTIDA        | ");
    System.out.printf("\n *---------------------------------------* ");

    for(int i = 0; i < personagem_partida.size(); i++){

      // Exclui da Rodada os Jogadores Mortos
      if (personagem_partida.get(i).getVida() <= 0){
        continue;
      }
      
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n Jogador %d: \n", (i+1));
      
      System.out.printf("\n Nome: %s", personagem_partida.get(i).getNome());
      
      if (personagem_partida.get(i).getTribo() == 1){
        System.out.printf("\n Tribo: Fogo");         
      }else if(personagem_partida.get(i).getTribo() == 2){
        System.out.printf("\n Tribo: Agua");
      }else if (personagem_partida.get(i).getTribo() == 3){
        System.out.printf("\n Tribo: Planta");
      }
      
      if (personagem_partida.get(i).getClasse() == 1){
        System.out.printf("\n Classe: Guerreiro");         
      }
      else if(personagem_partida.get(i).getClasse() == 2){
        System.out.printf("\n Classe: Arqueiro");
      }
      else if(personagem_partida.get(i).getClasse() == 3){
        System.out.printf("\n Classe: Mago");
      }
      
      System.out.printf("\n Vida: %d",personagem_partida.get(i).getVida());
      
      if (personagem_partida.get(i).getDefesaAtivada() == 1){
        System.out.printf("\n Defesa: ATIVADA");
      }
      else{
        System.out.printf("\n Defesa: DESATIVADA");
      }

      System.out.printf("\n *---------------------------------------* ");

    }
   
  }


  // Calcula Quantidade de Personagens Vivos
  @Override
  public int personagemVivos(){
    int vivos = 0;

    for(int i = 0; i < personagem_partida.size(); i++){

      if (personagem_partida.get(i).getVida() > 0){
        vivos++;
      }
    
    }

    return vivos;
  }


  // Começa a Batalha Entre os Personagens
  @Override
  public void luta(){
    Scanner teclado = new Scanner(System.in);

    int rodada = 0;

    while(personagemVivos() > 1){
      
      rodada += 1;
      
      // Vetor Sequência Aleatoria da Rodada
      Integer[] vetorAleatorio = Sistema.rodadaAleatoria(personagem_partida.size());

      Sistema.limparTela();

      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |         COMEÇANDO RODADA %d            | ", rodada);
      System.out.printf("\n *---------------------------------------* ");

      Sistema.esperar();
      
      for(int i = 0; i < personagem_partida.size(); i++){

        Sistema.limparTela();
        
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |              RODADA %d                 | ", rodada);
        System.out.printf("\n *---------------------------------------* ");

        this.mostrarPersonagens();

        if (personagem_partida.get(i).getVida() <= 0){
          continue;
        }

        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |                JOGADOR %d              | ", vetorAleatorio[i]+1);
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |       [1]. ATACAR                     | ");
        System.out.printf("\n |       [2]. DEFENDER                   | ");
        
        if (personagem_partida.get(vetorAleatorio[i]).getPercentualPowerUp() == 1){
          System.out.printf("\n |       [3]. POWER-UP - CARREGADO       | ");
        }
        else{
          System.out.printf("\n |       [3]. POWER-UP                   | ");
        }
      
        if (personagem_partida.get(vetorAleatorio[i]).getVidaRetirada() >= 100){
          System.out.printf("\n |       [4]. PODER ESPECIAL - CARREGADO | ");
        }
        else{
          System.out.printf("\n |       [4]. PODER ESPECIAL             | ");
        }

        System.out.printf("\n *---------------------------------------* ");

        System.out.printf("\n Digite sua escolha: ");
        int escolha = 0;
        
        // Tratamento de Exceções da variável escolha
        try{
          escolha = teclado.nextInt();
        } catch (InputMismatchException e){
          System.out.printf("\n\n   VALOR INVALIDO");
          Sistema.esperar();
          i--;
          break;
        }

        // Escolhendo Atacar
        if (escolha == 1){ 
      
          System.out.printf("\n Digite seu oponente: ");
          int numOponente = 0;

          // Tratamento de Exceções da variável numOponente (Alvo Inválido/Inexistente)
          try{
            numOponente = teclado.nextInt();
            personagem_partida.get(numOponente-1);
          } catch (InputMismatchException e){
            System.out.printf("\n\n   OPONENTE INVALIDO");
            Sistema.esperar();
            i--;
          } catch (ArrayIndexOutOfBoundsException e){
            System.out.printf("\n\n   OPONENTE INEXISTENTE");
            Sistema.esperar();
            i--;
          }
          
          this.atacar(vetorAleatorio[i], numOponente-1);
    
          // Se Depois do Ataque Houver Apenas 1 Jogador - Fim do Jogo
          if (personagemVivos() == 1){
            break;
          }
          
        }
        // Escolhendo ação Defesa:
        else if (escolha == 2){
          this.defender(vetorAleatorio[i]);
        }
        // Escolhendo ação Power Up:
        else if (escolha == 3){
          this.powerUp(vetorAleatorio[i]);
        }
        // Escolhendo ação Poder Especial:
        else if (escolha == 4){
          if (personagem_partida.get(vetorAleatorio[i]).getVidaRetirada() >= 100){

            if (personagem_partida.get(vetorAleatorio[i]).poderEspecial(vetorAleatorio[i], personagem_partida) == true){
              personagem_partida.get(vetorAleatorio[i]).setVidaRetirada(-100);
            }
            else{
              System.out.printf("\n\n  NAO FOI POSSIVEL USAR O PODER ESPECIAL");
              i--;
              Sistema.esperar();
            }
      
          }
          else{
            System.out.printf("\n FALTA CAUSAR %d DANO PARA DESBLOQUEAR O ATAQUE ESPECIAL",(100 - personagem_partida.get(vetorAleatorio[i]).getVidaRetirada()));
            i--;

            Sistema.esperar();
          }

        }

      }

      // Desativando Defesas ao Final da Rodada
      for(int j = 0; j < personagem_partida.size(); j++){
        if (personagem_partida.get(j).getDefesaAtivada() == 1){
          personagem_partida.get(j).setDefesaAtivada(0);
        }

      }

    }

    // Mostrar o Vencedor da Partida 
    for(int i = 0; i < personagem_partida.size(); i++){

      if (personagem_partida.get(i).getVida() > 1){
        Sistema.limparTela();

        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |               VENCEDOR                | ");
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n          NOME: %s                           ",personagem_partida.get(i).getNome());
        System.out.printf("\n *---------------------------------------* ");
       
        Sistema.esperar();

        break;
      }

    }

  }
  
  // Criar Personagens
  @Override
  public boolean criarPersonagem(){
    
    Scanner teclado = new Scanner(System.in);

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |           CRIANDO PERSONAGEM          | ");
    System.out.printf("\n *---------------------------------------* ");
    
    System.out.printf("\n\n Digite o nome do personagem: ");
    
    String nome = teclado.nextLine(); // Input Nome do Personagem

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |            TRIBOS                     | ");
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |       [1]. FOGO                       | ");
    System.out.printf("\n |       [2]. AGUA                       | ");
    System.out.printf("\n |       [3]. PLANTA                     | ");
    System.out.printf("\n *---------------------------------------* ");

    System.out.printf("\n\n Digite sua tribo: "); 
    
    int tribo; 
    
    // Tratamento de Exceções da variável tribo
    try{
      tribo = teclado.nextInt(); // Input Tribo do Personagem
    } catch (InputMismatchException e) {
      System.out.printf("\n\n   TRIBO INVALIDO");
      return false;
    }

    if ((tribo < 1) || (tribo > 3)){
      System.out.printf("\n\n   TRIBO INVALIDO");
      return false;
    }

    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n |               CLASSES                  | "); 
    System.out.printf("\n *----------------------------------------* "); 
    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n      NIVEL:       | ATAQUE  | DEFESA |     "); 
    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n    [1]. GUERREIRO |   20    |   30   |     ");
    System.out.printf("\n    [2]. ARQUEIRO  |   15    |   50   |     ");
    System.out.printf("\n    [3]. MAGO      |   10    |   70   |     ");
    System.out.printf("\n *----------------------------------------* ");
    
    System.out.printf("\n\n Digite sua classe: ");

    int classe;

    // Tratamento de Exceções da variável classe
    try{
      classe = teclado.nextInt(); // Input Classe do Personagem
    } catch (InputMismatchException e) {
      System.out.printf("\n\n   CLASSE INVALIDA");
      
      return false;
    }

    if ((classe < 1) || (classe > 3)){
      System.out.printf("\n\n   CLASSE INVALIDA");
      return false;
    }

    // Criando e Inicializando o Personagem
    if (classe == 1){
      Guerreiro personagem = new Guerreiro(nome, tribo, classe);
      this.personagem_partida.add(personagem);
    }
    else if (classe == 2){
      Arqueiro personagem = new Arqueiro(nome, tribo, classe);
      this.personagem_partida.add(personagem);
    }
    else if(classe == 3){
      Mago personagem = new Mago(nome, tribo, classe);
      this.personagem_partida.add(personagem);
    }
    
    // Menu Salvar Personagem
    Sistema.limparTela();

    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |      DESEJA SALVAR O PERSONAGEM ?     | ");
    System.out.printf("\n *---------------------------------------* ");
    System.out.printf("\n |        [1]. SIM                       | ");
    System.out.printf("\n |        [2]. NAO                       | ");
    System.out.printf("\n *---------------------------------------* ");

    System.out.printf("\n\n Digite sua escolha: ");
    int escolhaSalvar = 0;

    try{
      escolhaSalvar = teclado.nextInt();
    } catch (InputMismatchException e) {
      System.out.printf("\n\n   VALOR INVALIDO");
      System.out.printf("\n\n   POR PADRAO SEU PERSONAGEM FOI SALVO");
      escolhaSalvar = 1;
      Sistema.esperar();
    }

    if ((escolhaSalvar < 1) || (escolhaSalvar > 2)){
      System.out.printf("\n\n   VALOR INVALIDO");
      System.out.printf("\n\n   POR PADRAO SEU PERSONAGEM FOI SALVO");
      escolhaSalvar = 1;
      Sistema.esperar();
    }
    
    if (escolhaSalvar == 1){
      // Salvando Personagem
      ManipuladorArquivos manipulador = new ManipuladorArquivos();
        
      manipulador.salvarPersonagem(nome, tribo, classe);
        
      manipulador.salvarNomesArquivos(nome);
    }

    return true; 
  }

  // Carregar Nomes dos Arquivos no vetor nomes_arquivos
  @Override
  public void getNomesArquivos(){

    try {
      FileReader file = new FileReader("ArquivosTexto/nomesPersonagem.txt");
            
      BufferedReader br = new BufferedReader(file);
            
      String linha = br.readLine();
      
      while (linha != null){
        nomes_arquivos.add(linha);
        
        linha = br.readLine();
      }        

      file.close();

    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo nao encontrado");
    }
    catch (IOException e ) {
      System.out.println("Erro na leitura do arquivo");
    }

  }


  // Mostrar Arquivos de Personagens
  @Override
  public void mostrarArquivos(){ 

    ManipuladorArquivos manipulador = new ManipuladorArquivos();

    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n |           PERSONAGENS SALVOS           | "); 
    System.out.printf("\n *----------------------------------------* "); 

    for (int i = 0; i < nomes_arquivos.size(); i++){
      System.out.printf("\n *----------------------------------------* ");
      System.out.printf("\n      PERSONAGEM %d:                         \n", (i+1));
      manipulador.lerArquivo(nomes_arquivos.get(i));
      System.out.printf("\n *----------------------------------------* ");
    }

  }

  // Carregar Personagem e escolhendo 
  @Override
  public boolean carregarPersonagem(){
    
    Scanner teclado = new Scanner(System.in);

    Sistema.limparTela();

    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n |        CARREGANDO PERSONAGENS          | "); 
    System.out.printf("\n *----------------------------------------* \n");

    Sistema.esperar();

    this.mostrarArquivos();

    System.out.printf("\n  Digite o numero do personagem: ");
    
    int numPersonagem;
    
    // Tratamento de Exceções da variável numPersonagem
    try{
      numPersonagem = teclado.nextInt();
    } catch (InputMismatchException e) {
      System.out.printf("\n\n   PERSONAGEM INVALIDO");
      return false;
    }

    if ((numPersonagem < 1) || (numPersonagem > nomes_arquivos.size())){
      System.out.printf("\n\n   PERSONAGEM INVALIDO");
      return false;
    }
    
    try {
      Scanner file = new Scanner(new FileReader("ArquivosTexto/"+nomes_arquivos.get(numPersonagem-1)+".txt"));
            
      String nome = file.nextLine();

      int tribo = file.nextInt();

      int classe = file.nextInt();

      file.close();

      // Criando e Inicializando o Personagem
      if (classe == 1){
        Guerreiro personagem = new Guerreiro(nome, tribo, classe);
        this.personagem_partida.add(personagem);
      }
      else if (classe == 2){
        Arqueiro personagem = new Arqueiro(nome, tribo, classe);
        this.personagem_partida.add(personagem);
      }
      else if(classe == 3){
        Mago personagem = new Mago(nome, tribo, classe);
        this.personagem_partida.add(personagem);
      }

    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo nao encontrado");
      return false;
    }

    return true;
    
  }

  // Excluir Arquivo
  @Override
  public boolean excluirPersonagem(){
    Scanner teclado = new Scanner(System.in);

    // Atualizando Vetor NomesArquivos
    nomes_arquivos = new Vector<String>();
    this.getNomesArquivos();

    Sistema.limparTela();

    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n |          EXCLUINDO PERSONAGENS         | "); 
    System.out.printf("\n *----------------------------------------* \n");

    Sistema.esperar();
    
    this.mostrarArquivos();

    System.out.printf("\n  Digite o numero do personagem: ");
    
    int numPersonagem;
    
    try{
      numPersonagem = teclado.nextInt();
    } catch (InputMismatchException e) {
      System.out.printf("\n\n   PERSONAGEM INVALIDO");
      return false;
    }

    if ((numPersonagem < 1) || (numPersonagem > nomes_arquivos.size())){
      System.out.printf("\n\n   PERSONAGEM INVALIDO");
      return false;
    }

    // Excluindo Personagem Salvo
    File file = new File("ArquivosTexto/"+nomes_arquivos.get(numPersonagem-1)+".txt");
    file.delete();

    // Reformulando nomesPersonagem.txt
    try {
      FileWriter arq = new FileWriter("ArquivosTexto/nomesPersonagem.txt");
      
      BufferedWriter buffWrite = new BufferedWriter(arq);
      
      for (int i = 0; i < nomes_arquivos.size(); i++){
        if(i == numPersonagem-1){ continue; }
        buffWrite.append(nomes_arquivos.get(i) + "\n");
      }
        
      buffWrite.close();
      arq.close();
    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo nao encontrado");
    }
    catch (IOException e) {
      System.out.println("Erro na leitura do arquivo");
    }

    // Atualizando Vetor NomesArquivos
    nomes_arquivos = new Vector<String>();
    this.getNomesArquivos();

    return true;
  }

  // Editar Personagem
  @Override
  public boolean editarPersonagem(){

    // Atualizando Vetor NomesArquivos
    nomes_arquivos = new Vector<String>();
    this.getNomesArquivos();

    Scanner teclado = new Scanner(System.in);

    Sistema.limparTela();

    System.out.printf("\n *----------------------------------------* ");
    System.out.printf("\n |          EDITANDO PERSONAGENS          | "); 
    System.out.printf("\n *----------------------------------------* \n");

    Sistema.esperar();
    
    this.mostrarArquivos();

    System.out.printf("\n  Digite o numero do personagem: ");
    
    int numPersonagem;
    
    // Tratamento de Exceções da variável numPersonagem
    try{
      numPersonagem = teclado.nextInt();
    } catch (InputMismatchException e) {
      System.out.printf("\n\n   PERSONAGEM INVALIDO");
      return false;
    }

    if ((numPersonagem < 1) || (numPersonagem > nomes_arquivos.size())){
      System.out.printf("\n\n   PERSONAGEM INVALIDO");
      return false;
    }

    String nome = "";
    int tribo = 0;
    int classe = 0;

    try {
      Scanner file = new Scanner(new FileReader("ArquivosTexto/"+nomes_arquivos.get(numPersonagem-1)+".txt"));
            
      nome = file.nextLine();

      tribo = file.nextInt();

      classe = file.nextInt();

      file.close();
    }
    catch (FileNotFoundException e){
      System.out.println("Arquivo nao encontrado");
      return false;
    }
  
    // Variáveis que indicam se houve alteração
    boolean nomeEditado = false;
    boolean triboEditado = false;
    boolean classeEditado = false;

    String novoNome = "";

    while (true){
      
      Sistema.limparTela();

      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |         EDITANDO PERSONAGEM           | ");
      System.out.printf("\n *---------------------------------------* ");
      System.out.printf("\n |       [1]. EDITAR NOME                | ");
      System.out.printf("\n |       [2]. EDITAR TRIBO               | ");
      System.out.printf("\n |       [3]. EDITAR CLASSE              | ");
      
      if ((nomeEditado == true) || (triboEditado == true) || (classeEditado == true)){
        System.out.printf("\n |       [4]. SALVAR                     | ");
      }
      else{
        System.out.printf("\n |       [4]. SAIR                       | ");
      }

      System.out.printf("\n *---------------------------------------* ");

      int escolhaEditar;
      System.out.printf("\n\n  Digite uma opcao: ");

      // Tratamento de Exceções da variável escolhaEditar
      try{
        escolhaEditar = teclado.nextInt();
      } catch (InputMismatchException e) {
        System.out.printf("\n\n   OPCAO INVALIDA");
        System.out.printf("\n   VOLTANDO PARA O MENU DO JOGADOR ");
        Sistema.esperar();
        break;
      }

      if ((escolhaEditar < 1) || (escolhaEditar > 4)){
        System.out.printf("\n\n   OPCAO INVALIDA");
        System.out.printf("\n   VOLTANDO PARA O MENU DO JOGADOR ");
        Sistema.esperar();
        break;
      }

      // Tipos de Alterações Disponíveis:
      if (escolhaEditar == 1){
        nomeEditado = true;

        System.out.printf("\n\n Digite o Novo Nome: ");
        novoNome = teclado.nextLine();
        novoNome = teclado.nextLine();
      }
      else if (escolhaEditar == 2){

        triboEditado = true;

        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |            TRIBOS                     | ");
        System.out.printf("\n *---------------------------------------* ");
        System.out.printf("\n |       [1]. FOGO                       | ");
        System.out.printf("\n |       [2]. AGUA                       | ");
        System.out.printf("\n |       [3]. PLANTA                     | ");
        System.out.printf("\n *---------------------------------------* ");

        System.out.printf("\n\n Digite a nova Tribo: ");

        // Tratamento de Exceções da variável tribo
        try{
          tribo = teclado.nextInt();
        } catch (InputMismatchException e) {
          System.out.printf("\n\n   OPCAO INVALIDA");
          System.out.printf("\n   VOLTANDO PARA O MENU DO JOGADOR ");
          Sistema.esperar();
          break;
        }

      }
      else if (escolhaEditar == 3){
        classeEditado = true;

        System.out.printf("\n *----------------------------------------* ");
        System.out.printf("\n |               CLASSES                  | "); 
        System.out.printf("\n *----------------------------------------* "); 
        System.out.printf("\n *----------------------------------------* ");
        System.out.printf("\n      NÍVEL:       | ATAQUE  | DEFESA |     "); 
        System.out.printf("\n *----------------------------------------* ");
        System.out.printf("\n    [1]. GUERREIRO |   20    |   30   |     ");
        System.out.printf("\n    [2]. ARQUEIRO  |   15    |   50   |     ");
        System.out.printf("\n    [3]. MAGO      |   10    |   70   |     ");
        System.out.printf("\n *----------------------------------------* ");

        System.out.printf("\n\n Digite a nova Classe: ");

        // Tratamento de Exceções da variável classe
        try{
          classe = teclado.nextInt();
        } catch (InputMismatchException e) {
          System.out.printf("\n\n   OPCAO INVALIDA");
          System.out.printf("\n   VOLTANDO PARA O MENU DO JOGADOR ");
          Sistema.esperar();
          break;
        }

      }
      else if (escolhaEditar == 4){
        break;
      }

    }

    ManipuladorArquivos manipulador = new ManipuladorArquivos();
    
    // Nome Não Alterado - Criar Arquivo com mesmo Nome e Tribos e/ou Classes Diferentes
    if (nomeEditado == false){

      // Atualizando Vetor NomesArquivos
      nomes_arquivos = new Vector<String>();
      this.getNomesArquivos();

      // Reformulando nomesPersonagem.txt
      try {
        FileWriter arq = new FileWriter("ArquivosTexto/nomesPersonagem.txt");
        
        BufferedWriter buffWrite = new BufferedWriter(arq);
        
        for (int i = 0; i < nomes_arquivos.size(); i++){
          if(i == numPersonagem-1){ continue; }
          buffWrite.append(nomes_arquivos.get(i) + "\n");
        }
          
        buffWrite.close();
        arq.close();
      }
      catch (FileNotFoundException e){
        System.out.println("Arquivo nao encontrado");
      }
      catch (IOException e) {
        System.out.println("Erro na leitura do arquivo");
      }

      manipulador.salvarPersonagem(nome, tribo, classe);
      manipulador.salvarNomesArquivos(nome);

      // Atualizando Vetor NomesArquivos
      nomes_arquivos = new Vector<String>();
      this.getNomesArquivos();
      
    }
    // Nome Alterado - Criar Novo Arquivo (com nome, tribo e/ou classes editadas) e Apagar Arquivo Antigo
    else{
      manipulador.salvarPersonagem(novoNome, tribo, classe);
      manipulador.salvarNomesArquivos(novoNome);

      // Atualizando Vetor NomesArquivos
      nomes_arquivos = new Vector<String>();
      this.getNomesArquivos();

      // Excluir Arquivo Antigo:
      File file = new File("ArquivosTexto/"+nomes_arquivos.get(numPersonagem-1)+".txt");
      file.delete();

      // Reformulando nomesPersonagem.txt
      try {
        FileWriter arq = new FileWriter("ArquivosTexto/nomesPersonagem.txt");
        
        BufferedWriter buffWrite = new BufferedWriter(arq);
        
        for (int i = 0; i < nomes_arquivos.size(); i++){
          if(i == numPersonagem-1){ continue; }
          buffWrite.append(nomes_arquivos.get(i) + "\n");
        }
          
        buffWrite.close();
        arq.close();
      }
      catch (FileNotFoundException e){
        System.out.println("Arquivo nao encontrado");
      }
      catch (IOException e) {
        System.out.println("Erro na leitura do arquivo");
      }

      // Atualizando Vetor NomesArquivos
      nomes_arquivos = new Vector<String>();
      this.getNomesArquivos();
    }

    return true;
  }

}
