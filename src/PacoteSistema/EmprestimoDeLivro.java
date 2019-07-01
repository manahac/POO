package PacoteSistema;

public class EmprestimoDeLivro {
    public String nomeCliente, dataPrevistaDevolucao, cpfCliente, exemplar, funcionario;
    Integer[] dataDeEmprestimo;
    
    public EmprestimoDeLivro() {
    }

    public EmprestimoDeLivro(String nomeCliente, String dataPrevistaDevolucao, String cpfCliente, String exemplar, String funcionario, Integer[] dataDeEmprestimo) {
        this.nomeCliente = nomeCliente;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.cpfCliente = cpfCliente;
        this.exemplar = exemplar;
        this.funcionario = funcionario;
        this.dataDeEmprestimo = dataDeEmprestimo;
    }
    
    
}
