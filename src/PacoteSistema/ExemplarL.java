package PacoteSistema;

public class ExemplarL {
    
    public String titulo;
    public String isbn;
    public String autor;
    public String valor;
    public String edicao;
    public String ano;
    public String editora;
    public String tipoDeLivro;
    public String descricao;
    public int qtd;

    public ExemplarL(String titulo, String isbn, String autor, String valor, String edicao, String ano, String editora, String tipoDeLivro, String descricao, int qtd) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.valor = valor;
        this.edicao = edicao;
        this.ano = ano;
        this.editora = editora;
        this.tipoDeLivro = tipoDeLivro;
        this.descricao = descricao;
        this.qtd = qtd;
    }

    public ExemplarL() {
    }
    
}
