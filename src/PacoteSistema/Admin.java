package PacoteSistema;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Admin extends Funcionario{
    
    //Arrays de administradores e bibliotecarios
    ArrayList<Bibliotecario> bibliotecarios = new ArrayList();
    ArrayList<Admin> admins = new ArrayList();
    
    //Construtores de administrador
    public Admin(String usuario, String senha) {
        super(usuario, senha);
    }
    public Admin(){
    }
    
     //Lista os serviços disponíveis para o administrador
    @Override
     public void servicosDisponiveis(){

        Object[] itens = {"CADASTRAR CLIENTE", "CADASTRAR LIVRO", "EMPRESTMO DE LIVRO", "CONSULTAR LIVRO", "CONSULTAR LIVRO POR TIPO", "CADASTRAR FUNCIONÁRIO","DEVOLUCAO DE LIVRO", "CADASTRAR ADMINISTRADOR"};
        Object selecionado = JOptionPane.showInputDialog(null,"ESCOLHA", "SERVIÇOS DISPONÍVEIS", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
        
        if(selecionado.equals("CADASTRAR CLIENTE")){
            cadastrarCliente();
        } else if (selecionado.equals("CADASTRAR LIVRO")){
            cadastrarLivro();
        } else if (selecionado.equals("EMPRESTMO DE LIVRO")){
            emprestimoDeLivro();
        } else if (selecionado.equals("CADASTRAR FUNCIONÁRIO")){
            cadastrarFuncionario();
        } else if(selecionado.equals("DEVOLUCAO DE LIVRO")){
            devolucaoDeLivro();
        } else if(selecionado.equals("CADASTRAR ADMINISTRADOR")){
             cadastrarAdmin();
        } else if(selecionado.equals("CONSULTAR LIVRO POR TIPO")){
            String tipoPesquisa = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CONSULTAR LIVROS POR TIPO\n\nPesquisar pelo tipo");
            consultarTipo(tipoPesquisa);
        } else {
            String tituloPesquisa = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CONSULTAR LIVRO\n\nPesquisar pelo titulo");
           
            if (consultarTitulo(tituloPesquisa)){
                
                comoProsseguir();
                
            } else {
                JOptionPane.showMessageDialog(null, "Livro não encontrado!"); 
                
                comoProsseguir();  
            }
        }
    }    
    
     //Verifica no array a existêcia de administradores 
    public boolean validaAdmin(String usuario, String senha){
        for (Admin admin : admins) {
            if (usuario.equals(admin.usuario) && senha.equals(admin.senha)) {
                return true; 
            }
        }
        return false;
    }
   
    //Verifica no array a existêcia de bibliotecários
    public boolean validaBibliotecario(String usuario, String senha){
        for (Bibliotecario bibliotec : bibliotecarios) {
            if (usuario.equals(bibliotec.usuario) && senha.equals(bibliotec.senha)) {
                return true; 
            }
        }
        return false;
    }
    
    //Inicializa os funcionários de teste
    public void inicializaFuncionarios(){
        
        //Cria um bibliotecario e adiciona ao arraylist
        Bibliotecario bruno = new Bibliotecario("Bruno", "123456");
        bibliotecarios.add(bruno);
        
        //Cria um administrador e adiciona ao arraylist
        Admin jon = new Admin("Jon", "123456");
        admins.add(jon);
    }
    
    //Cadastra novo funcionario no sistema
    private void cadastrarFuncionario(){
        
        //Atributos de funcionário
       String usuarioF, senhaF;
        
       usuarioF = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CADASTRO DE FUNCIONÁRIO\n\nDigite o usuario do funcionário");
       senhaF =  JOptionPane.showInputDialog(null, "Digite a senha");
       
       Bibliotecario novoBibliotecario = new Bibliotecario(usuarioF, senhaF);
       bibliotecarios.add(novoBibliotecario);
       
       JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
       
       //0=Sim, 1=Não, 2=Cancelar
       int novoCadastro = JOptionPane.showConfirmDialog(null, "Cadastrar novo funcionário?");
       if(novoCadastro==0){
           cadastrarFuncionario();
       }
       
       comoProsseguir();
    }
    
    //Cadastrar admin 
    private void cadastrarAdmin(){
        
       String usuarioA, senhaA;
        
       usuarioA = JOptionPane.showInputDialog(null, "\t\tVOCÊ ESCOLHEU A OPÇÃO CADASTRO DE ADMINISTRADOR\n\nDigite o usuario do administrador");
       senhaA =  JOptionPane.showInputDialog(null, "Digite a senha");
       
       Admin novoAdmin = new Admin(usuarioA, senhaA);
       admins.add(novoAdmin);
       
       JOptionPane.showMessageDialog(null, "Administrador cadastrado com sucesso!");
       
       //0=Sim, 1=Não, 2=Cancelar
       int novoCadastro = JOptionPane.showConfirmDialog(null, "Cadastrar novo administrador?");
       if(novoCadastro==0){
           cadastrarAdmin();
       }
       comoProsseguir();
    }
}
