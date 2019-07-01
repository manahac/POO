package PacoteSistema;

import static PacoteSistema.ValidaCPF.isCPF;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.JOptionPane;

public abstract class Funcionario {
    
    //Atributos de funcionário
    public String usuario;
    public String senha;
    
    //Arrays
    ArrayList<Cliente> clientes = new ArrayList();
    ArrayList<ExemplarL> exemplares = new ArrayList();
    ArrayList<EmprestimoDeLivro> emprestimos = new ArrayList();
    ArrayList<DevolucaoDeLivro> devolucoes = new ArrayList();
    
    //Construtores para outras classes especializadas de funcionário
    public Funcionario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public Funcionario() {
    }
    
    //Lista os serviços disponíveis para o funcionário
    public void servicosDisponiveis(){
        
        //Menu de serviços
        Object[] itens = {"CADASTRAR CLIENTE", "CADASTRAR LIVRO", "EMPRESTMO DE LIVRO", "CONSULTAR LIVRO", "DEVOLUCAO DE LIVRO", "CONSULTAR LIVRO POR TIPO"};
        Object selecionado = JOptionPane.showInputDialog(null,"ESCOLHA", "SERVIÇOS DISPONÍVEIS", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);

        if(selecionado.equals("CADASTRAR CLIENTE")){
            cadastrarCliente();
        } else if (selecionado.equals("CADASTRAR LIVRO")){
            cadastrarLivro();
        } else if (selecionado.equals("EMPRESTMO DE LIVRO")){
            emprestimoDeLivro();
        } else if(selecionado.equals("DEVOLUCAO DE LIVRO")){
            devolucaoDeLivro();
        } else if(selecionado.equals("CONSULTAR LIVRO POR TIPO")){
            String tipoPesquisa = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CONSULTAR LIVROS POR TIPO\n\nPesquisar pelo tipo");
            consultarTipo(tipoPesquisa);
        }else {
            String tituloPesquisa = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CONSULTAR LIVRO\n\nPesquisar pelo titulo");
            
            if (consultarTitulo(tituloPesquisa)){
                
            } else {
                JOptionPane.showMessageDialog(null, "Livro não encontrado!"); 
            } 
            comoProsseguir();
        }
    }
    
    //Intancia um novo cliente no array 'clientes'
    public void cadastrarCliente(){
        
        //Atributos de cliente
        String nomeC, CPFC, emailC;
        
        nomeC = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CADASTRO DE CLIENTE\n\nDigite o nome");
        String CPFTeste = JOptionPane.showInputDialog(null, "Agora digite o CPF");
        
        //Valida o CPF digitado
        do{
            if (isCPF(CPFTeste)==false){
            CPFTeste = JOptionPane.showInputDialog(null, "Digite um CPF válido!");
        }
        }while(isCPF(CPFTeste)==false);
        
        //Recebe o CPF válido
        CPFC = CPFTeste;
        
        emailC = JOptionPane.showInputDialog(null, "Agora digite seu email");
        
        Cliente novoCliente = new Cliente(nomeC, CPFC, emailC);
        clientes.add(novoCliente);
        
        JOptionPane.showMessageDialog(null, "Cliente "+ nomeC + " cadastrado com sucesso!");
        
        //0=Sim, 1=Não, 2=Cancelar
        int novoCadastro = JOptionPane.showConfirmDialog(null, "Cadastrar novo cliente?");
        if(novoCadastro==0){
            cadastrarCliente();
        }
        
        comoProsseguir();
    }
    
    //Cadastra um novo livro no array 'exemplares'
    public void cadastrarLivro()throws NumberFormatException {
        
//Atributos do livro
        String tituloE, ISBN, autorE, edicaoE, anoE, editoraE, descricaoE, tipoDeLivro, valorE, qtdString;
        int qtdInteiro = 0;
        
        tituloE = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CADASTRAR LIVRO\n\nDigite o titulo");
        ISBN = JOptionPane.showInputDialog(null, "Digite o ISBN (Ex.: 000-00-000-0000-0)");
        autorE = JOptionPane.showInputDialog(null, "Autor");
        edicaoE = JOptionPane.showInputDialog(null, "Edição");
        anoE = JOptionPane.showInputDialog(null, "Ano de publicação");
        editoraE = JOptionPane.showInputDialog(null, "Editora responsável");
        descricaoE = JOptionPane.showInputDialog(null, "Descrição");
        tipoDeLivro = JOptionPane.showInputDialog(null, "Tipo de livro");
        valorE = JOptionPane.showInputDialog(null, "Valor");
        
        //Inserir um número inteiro a quantidade de livros disponíveis; utilizando o tratamento de exceção NumberFormatException
        boolean continueLoop = true;
        do{
            try{
                qtdString = JOptionPane.showInputDialog(null, "Quantidade");
                qtdInteiro = Integer.parseInt(qtdString);
                continueLoop = false;
            }
            catch(NumberFormatException entradaInvalida){
                JOptionPane.showMessageDialog(null,"Entrada inválida! Exceção: " +  entradaInvalida + ", Tente novamente: " );
            }   
        }while(continueLoop);
        
        //Instância um novo livro
        ExemplarL novoLivro = new ExemplarL(tituloE, ISBN, autorE, valorE, edicaoE, anoE, editoraE, tipoDeLivro, descricaoE, qtdInteiro);
        exemplares.add(novoLivro);
        
        JOptionPane.showMessageDialog(null, "Livro " + tituloE +" cadastrado com sucesso!");
        
        // 0=Sim, 1=Não, 2=Cancelar
        int novoCadastro = JOptionPane.showConfirmDialog(null, "Cadastrar novo livro?");
        if(novoCadastro==0){
            cadastrarLivro();
        }
        
        comoProsseguir();
    }
    
    //Mostra os dados do livro e retorna true ou não o encontra e retorna false
    public boolean consultarTitulo(String titulo){
        
        for(int i=0;i<exemplares.size();i++){
            if(exemplares.get(i).titulo.equals(titulo)){
                JOptionPane.showMessageDialog(null, "\t\tLIVRO ENCONTRADO!\n\n"
                    + "Titulo .............. : " + exemplares.get(i).titulo 
                    + "\nAutor .............. : " + exemplares.get(i).autor 
                    + "\nISBN ............... : " + exemplares.get(i).isbn 
                    + "\nValor $ ........... : " + exemplares.get(i).valor 
                    + "\nEdição ............ : " + exemplares.get(i).edicao 
                    + "\nAno ................. : " + exemplares.get(i).ano 
                    + "\nEditora ........... : " + exemplares.get(i).editora 
                    + "\nTipo do livro .. : " + exemplares.get(i).tipoDeLivro 
                    + "\nDisponíveis ... : " +  exemplares.get(i).qtd 
                    + "\n\nDescrição \n{ " + exemplares.get(i).descricao + " }");
                return true;
            }
        }
        return false;
    }
    
        //Mostra os dados do livro e retorna true ou não o encontra e retorna false
    public void consultarTipo(String tipo){
        ArrayList<String> livrosTipo = new ArrayList();
        
        for(int i=0;i<exemplares.size();i++){
            if(exemplares.get(i).tipoDeLivro.equalsIgnoreCase(tipo)){
                livrosTipo.add(exemplares.get(i).titulo);
            }
        }
        if(livrosTipo.isEmpty()){
            JOptionPane.showMessageDialog(null, "\t\tLIVROS NÃO ENCONTRADOS!\n\n");
        } else {
            JOptionPane.showMessageDialog(null, "LIVRO(S) ENCONTRADO(S)\n" + Arrays.toString(livrosTipo.toArray()));
        }
        comoProsseguir();
    }
    
    //Realiza emprestimo 
    public void emprestimoDeLivro(){
        
        //Atributos de emprestimo
        String nomeClienteE, funcionarioE, dataPrevistaDevolucaoE, CPFClienteE;
        Integer []dataEmprestimoE;
        String exemplarEmprestimo = null;
        
        String buscaCPF = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO EMPRESTIMO DE LIVRO\n\nDigite o CPF do cliente");
        
        //Verifica se o cliente está cadastrado no sistema e se sim faz o cadastro, senão, não o faz
        if(consultarCliente(buscaCPF)){
            
            //Atribui à variável criada o cliente cadastrado
            CPFClienteE = buscaCPF;
            
            //Atribui o nome do cliente por meio do método de consulta de clientes
            nomeClienteE = consultarNomeCliente(buscaCPF);
            
            imprimeCliente(CPFClienteE);

              String adicionarLivro = JOptionPane.showInputDialog(null, "Nome do livro para emprestimo");
              
              //verifica existência de livro e quantidade de exemplares
              if(consultarTitulo(adicionarLivro)){
                  exemplarEmprestimo = adicionarLivro;
                  subtrairExemplar(adicionarLivro);
                  
              } else {
                    JOptionPane.showMessageDialog(null, "Livro não encontrado!"); 
                }
              
            //Recebe a data atual de emprestimo
            dataEmprestimoE = dataEmprestimo();
            
            dataPrevistaDevolucaoE = JOptionPane.showInputDialog(null, "Data prevista de devolução");
            funcionarioE = JOptionPane.showInputDialog(null, "Nome do funcionário responsável");
            
            //Intancia um novo emprestimo
            EmprestimoDeLivro newEmprestimo = new EmprestimoDeLivro(nomeClienteE, dataPrevistaDevolucaoE,
                        CPFClienteE, exemplarEmprestimo, funcionarioE,  dataEmprestimoE);
                 
            emprestimos.add(newEmprestimo);
            
            JOptionPane.showMessageDialog(null, "Emprestimo realizado com sucesso!");
            
            imprimeRelatorioE(newEmprestimo);
            
            // 0=Sim, 1=Não, 2=Cancelar
            int novoEmprestimo = JOptionPane.showConfirmDialog(null, "Realizar novo emprestimo?");
            if(novoEmprestimo==0){
                cadastrarLivro();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            //0=Sim, 1=Não, 2=Cancelar
            int novoCadastro = JOptionPane.showConfirmDialog(null, "Cadastrar novo cliente?");
            if(novoCadastro==0){
                cadastrarCliente();
            }
        }
        
        comoProsseguir(); 
    }
    
    //Retorna data atual
    public Integer[] dataEmprestimo(){
        
        Integer []dataEmprestimo = new Integer[3];
        
        Calendar hoje = Calendar.getInstance();
        
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        int mes = hoje.get(Calendar.MONTH);
        int ano = hoje.get(Calendar.YEAR);
        
        dataEmprestimo[0] = dia;
        dataEmprestimo[1] = mes + 1;
        dataEmprestimo[2] = ano;
        return dataEmprestimo;
    }
    
    //Retorna cliente cadastrado ou não
    public boolean consultarCliente(String cpf){
        for (Cliente cliente : clientes) {
            if (cliente.CPF.equals(cpf)) {
                return true;
            }
        }
        return false;
    }
    
    //Retorna o nome do cliente cadastrado
    public String consultarNomeCliente(String cpf){
        for (Cliente cliente : clientes) {
            if (cliente.CPF.equals(cpf)) {
                return cliente.nome;
            }
        }
        return "";
    }
    
    //Imprime os dados do cliente
    public void imprimeCliente(String cpf){
        for (Cliente cliente : clientes) {
            if (cliente.CPF.equals(cpf)) {
                JOptionPane.showMessageDialog(null, "DADOS DO CLIENTE CADASTRADO\n\nCliente: " + cliente.nome + "\nCPF: " + cliente.CPF + "\nEmail: " + cliente.email);
            }
        }
    }
    
    //Subtrai a quantidade de exemplares ao acervo da biblioteca
    public void subtrairExemplar(String titulo){
        for (ExemplarL exemplare : exemplares) {
            if (titulo.equals(exemplare.titulo)) {
                exemplare.qtd = exemplare.qtd - 1;
            }
        }
    }
    
    //Imprime o relatorio de emprestimo
    public void imprimeRelatorioE(EmprestimoDeLivro emprestimo){
        
        JOptionPane.showMessageDialog(null, "RELATÓRIO DE EMPRESTIMO\n\nCliente: " + emprestimo.nomeCliente + "\nCPF do cliente: "
        + emprestimo.cpfCliente +  "\nData de emprestimo: dia " + emprestimo.dataDeEmprestimo[0] + "/" + emprestimo.dataDeEmprestimo[1] + "/"
        + emprestimo.dataDeEmprestimo[2] + "\nPossível data de devolução: " + emprestimo.dataPrevistaDevolucao + "\nFuncionário responsável: "
        + emprestimo.funcionario + "\nExemplar: " + emprestimo.exemplar);
    }
    
    //Inicializa os componetes do funcionário
    public void inicializaComponentes(){
        
        //Instância(s) de livro
        ExemplarL estruturas = new ExemplarL("Estruturas de dados e algoritmos em C++", " 978-85-221-2665-1", "Adam Drozdek", "90.00", "2ª", "2016", 
        "Cengage Learning", "CC", "O livro enfatiza especialmente a conexão entre a estrutura de dados e seus \n algoritmos, "
        + "incluindo uma análise da complexidade dos algoritmos", 5);
        exemplares.add(estruturas);
        
        ExemplarL uml = new ExemplarL("UML Essencial", " 0-321-19368-7", "Martin Fowler", "124.9", "3ª", "2005", 
        "Bookman", "CC", "UML (Unified Modeling Language) é uma família de notações gráficas, apoiada por "
        + "um metamodelo único, que ajuda na descrição \n e no projeto de sistemas de software, particularmente daqueles construídos utilizando o estilo orientado a objetos (OO)", 2);
        exemplares.add(uml);
        
        ExemplarL cplusplus = new ExemplarL("Programação em C++", " 978-85-8055-026-9", "Luis Joyanes Aguilar", "64.52", "2ª", "2008", 
        "Mc Graw Hill", " Linguagens de programacão – C++", "Este livro foi feito para ensinar a programar utilizando C++ e não para \n "
        + "ensinar C++, ainda que também pretenda atingir esse objetivo", 3);
        exemplares.add(cplusplus);
        
        
        //Instância(s) de cliente
        Cliente luiz = new Cliente("Luiz", "29828420090", "luiz@gmail.com");
        clientes.add(luiz);
        Cliente lucas = new Cliente("Lucas", "65761848082", "lucas@gmail.com");
        clientes.add(lucas);
        Cliente jose = new Cliente("José", "03205564251", "jose@gmail.com");
        clientes.add(jose);
        
        
        //Instâncias de emprestimos
        Integer []dataEmprestimoLuiz = new Integer[3];
        dataEmprestimoLuiz[0] = 21;
        dataEmprestimoLuiz[1] = 6;
        dataEmprestimoLuiz[2] = 2019;
        EmprestimoDeLivro emprestimoLuiz = new EmprestimoDeLivro("Luiz", "25/6/2019", "29828420090", "Programação em C++", "Jon", dataEmprestimoLuiz);
        emprestimos.add(emprestimoLuiz);
    
    }
    
    //Opções de continuação
    public void comoProsseguir(){
        
        Object[] opcoes = {"VOLTAR À SERVIÇOS DISPONÍVEIS", "FINALIZAR"};
        Object escolhido = JOptionPane.showInputDialog(null,"SELECIONE", "O QUE DESEJA FAZER?", JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[0]);
            
        if ("VOLTAR À SERVIÇOS DISPONÍVEIS".equals(escolhido)){
            servicosDisponiveis();
        } else {
            JOptionPane.showMessageDialog(null, "Saindo...");
            System.exit(0);
        }  
    }
    
    //Verificar a existência de emprestimo atraves do CPF do cliente
    public boolean existeEmprestimo(String CPF){
        for(EmprestimoDeLivro empres : emprestimos){
            if(empres.cpfCliente.equals(CPF)){
                return true;
            }
        }
        return false;
    }
    
    //Faz a instancição de uma devolução 
    public void devolucaoDeLivro(){
        
        //Atributos de devolução
        Integer []dataDevolve;
        final float multa = 3;
        float totalMulta;
        
        int diasExcedidos = 0;
        String CPFCliente = JOptionPane.showInputDialog(null, "Digite o CPF do cliente para realizar a devolução");
        
        //Verifica se a entrada é vazia
        if(CPFCliente.isEmpty()){
        do{
            CPFCliente = JOptionPane.showInputDialog(null, "Digite novamente o CPF");
          }while(CPFCliente.isEmpty());   
        }

        //Verifica a existência de emprestimo
        if(existeEmprestimo(CPFCliente)){
            
            //Como há a existência de emprestimo, recebe o indice do emprestimo 
            int indiceEmprestimo = indiceEmprestimo(CPFCliente);
            
            JOptionPane.showMessageDialog(null, "Emprestimo efetuado dia " + emprestimos.get(indiceEmprestimo).dataDeEmprestimo[0] + 
            "/" + emprestimos.get(indiceEmprestimo).dataDeEmprestimo[1] + "/" + emprestimos.get(indiceEmprestimo).dataDeEmprestimo[2] 
            + "\nDia prevista de devolução: " + emprestimos.get(indiceEmprestimo).dataPrevistaDevolucao);
            
            dataDevolve = dataDeDevolucao();
            
            // 0=Sim, 1=Não, 2=Cancelar
            int haAtraso = JOptionPane.showConfirmDialog(null, "Houve atraso na devolução do livro?");
            if(haAtraso==0){
                JOptionPane.showMessageDialog(null, "Os dias de atraso são multiplicados por 3$!");
                
                //Inserir um número inteiro
                boolean continueLoop = true;
                do{
                        try{
                        String diasE = JOptionPane.showInputDialog(null, "Quantos dias foram?");
                        diasExcedidos = Integer.parseInt(diasE);
                        continueLoop = false;
                        }
                        catch(NumberFormatException entradaInvalida){
                            JOptionPane.showMessageDialog(null,"Entrada inválida! Exceção: " +  entradaInvalida + ", Tente novamente " );
                        }   
                }while(continueLoop);
                
                totalMulta = calcularMulta(diasExcedidos, multa);
                
                DevolucaoDeLivro novaDevolucao = new DevolucaoDeLivro(dataDevolve, multa, totalMulta);
                devolucoes.add(novaDevolucao);
                
                imprimeRelatorioComMulta(emprestimos.get(indiceEmprestimo), totalMulta, dataDevolve, diasExcedidos);
        
            } else {
                imprimeRelatorioE(emprestimos.get(indiceEmprestimo));
            }   
            
            comoProsseguir();
             
        } else {
            JOptionPane.showMessageDialog(null,"Emprestimo inexistente!" );
            
            comoProsseguir();
        }
    }
    
    //Adiciona 1 a quantidade de exemplares na biblioteca
    public void adicionarExemplar(String titulo){
        for (ExemplarL exemplare : exemplares) {
            if (titulo.equals(exemplare.titulo)) {
                exemplare.qtd = exemplare.qtd + 1;
            }
        }
    }
    
    public int indiceEmprestimo(String CPF){
        for(int i=0;i<emprestimos.size();i++){
            if(emprestimos.get(i).cpfCliente.equals(CPF)){
                return i;
            }
        }
        return 0;
    }
    //Retorna data atual
    public Integer[] dataDeDevolucao(){
        
        Integer []dataDevolucao = new Integer[3];
        
        Calendar hoje = Calendar.getInstance();
        
        int dia = hoje.get(Calendar.DAY_OF_MONTH);
        int mes = hoje.get(Calendar.MONTH);
        int ano = hoje.get(Calendar.YEAR);
        
        dataDevolucao[0] = dia;
        dataDevolucao[1] = mes + 1;
        dataDevolucao[2] = ano;
        return dataDevolucao;
    }
    
    public float calcularMulta(int diasExcedidos, float multa){
        return diasExcedidos * multa;
    }
    
    //Imprime relatorio de devolucao com multa e a data definitiva de devolução
    public void imprimeRelatorioComMulta(EmprestimoDeLivro emprestimo, float multa, Integer []dataDevolucao, int atraso){
        
        JOptionPane.showMessageDialog(null, "\t\tRELATÓRIO DE DEVOLUÇÃO\n\nCliente: " + emprestimo.nomeCliente + "\nCPF do cliente: "
        + emprestimo.cpfCliente +  "\nData de emprestimo: dia " + emprestimo.dataDeEmprestimo[0] + "/" + emprestimo.dataDeEmprestimo[1] 
        + "/" + emprestimo.dataDeEmprestimo[2] +  "\nPossível data de devolução: " + emprestimo.dataPrevistaDevolucao + "\nFuncionário responsável: " 
        + emprestimo.funcionario + "\nExemplar: " + emprestimo.exemplar + "\nData de devolução: dia " + dataDevolucao[0] + "/" + dataDevolucao[1] + "/" 
        + dataDevolucao[2] + "\nAtraso de " + atraso + " dia(s)\n" +"\nMulta $: " + multa);
    }
   
}