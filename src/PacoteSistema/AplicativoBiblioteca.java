package PacoteSistema;

import javax.swing.JOptionPane;

public class AplicativoBiblioteca {

    public static void main(String[] args) {
        
        //Inicializa os funcionários do sistema
        Admin administrador = new Admin();
        administrador.inicializaFuncionarios();
        
        //Intancia um bibliotecario usar os recursos de bibliotecário
        Bibliotecario bibliotecario = new Bibliotecario();
        
        Object[] itens = {"ADMINISTRADOR", "BIBLIOTECÁRIO"};
        Object selecionado = JOptionPane.showInputDialog(null,"LOGAR COMO", "SISTEMA DE BIBLIOTECA", JOptionPane.INFORMATION_MESSAGE, null, itens, itens[0]);
        
        if("ADMINISTRADOR".equals(selecionado)){
            
            int novaTentativa;
            
            do{
                //Entra com o login do administrador
                String usuarioTeste =  JOptionPane.showInputDialog(null, "Admin, digite seu usuário");
                String senhaTeste =  JOptionPane.showInputDialog(null, "Agora sua senha");
            
                if(administrador.validaAdmin(usuarioTeste, senhaTeste)){
                //String adminLogado = administrador.NomeAdminLogado(usuarioTeste, senhaTeste);
                
                //Iniciliza o array de exemplares, clientes e emprestimos
                administrador.inicializaComponentes();
                
                JOptionPane.showMessageDialog(null, "Bem-vindo, " + usuarioTeste + ".");
                administrador.servicosDisponiveis();
                } else {
                JOptionPane.showMessageDialog(null, "Administrador não encontrado");
                }
                // 0=Sim, 1=Não, 2=Cancelar
                novaTentativa = JOptionPane.showConfirmDialog(null, "Tentar novamente?");
                
            }while(novaTentativa==0);
            
            JOptionPane.showMessageDialog(null, "Saindo...");
            System.exit(0);
            
        } else if ("BIBLIOTECÁRIO".equals(selecionado)){
            
            int novaTentativa;
            
            do{
                //Entra com o login do bibliotecário
                String usuarioTeste =  JOptionPane.showInputDialog(null, "Bibliotecário, digite seu usuário");
                String senhaTeste =  JOptionPane.showInputDialog(null, "Agora, digite sua senha");
            
                if(administrador.validaBibliotecario(usuarioTeste, senhaTeste)){
                
                //Iniciliza o array de exemplares, clientes e emprestimos
                bibliotecario.inicializaComponentes();
                
                JOptionPane.showMessageDialog(null, "Bem-vindo, " + usuarioTeste + ".");
                bibliotecario.servicosDisponiveis();
                } else {
                JOptionPane.showMessageDialog(null, "Bibliotecário não encontrado");
                }
                // 0=Sim, 1=Não, 2=Cancelar
                novaTentativa = JOptionPane.showConfirmDialog(null, "Tentar novamente?");
                
            }while(novaTentativa==0);
            
            JOptionPane.showMessageDialog(null, "Saindo...");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Saindo...");
            System.exit(0);
        }     
    } 
}