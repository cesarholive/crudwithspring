package br.com.alura.screenmatch.domain.filme;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //informando ao JPA que essa classe vai ser transformada em tabela
@Table(name = "filmes") // passando o nome da tabela dentro do banco
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;
	private Integer duracaoEmMinutos;
	private Integer anoLancamento;
	private String genero;
	
	public Filme(DadosCadastroFilme dados) {
		this.nome = dados.nome();
		this.duracaoEmMinutos = dados.duracao();
		this.anoLancamento = dados.ano();
		this.genero = dados.genero();
		}
	
	public Filme() {// obrigátoria a criação de um construtor vazio para o JPA funcionar
		
	}
	
	@Override
	public String toString() {
		return "Filme [nome=" + nome + ", duracaEmMinutos=" + duracaoEmMinutos + ", anoLancamento=" + anoLancamento
				+ ", genero=" + genero + "]";
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public Integer getDuracaoEmMinutos() {
		return duracaoEmMinutos;
	}
	public Integer getAnoLancamento() {
		return anoLancamento;
	}
	public String getGenero() {
		return genero;
	}

	public void atulizaDados(DadosAlteracaoFilme dados) {
		this.nome = dados.nome(); // passando os dados atualizados
		this.duracaoEmMinutos = dados.duracao();
		this.anoLancamento = dados.ano();
		this.genero = dados.genero();
	}
 

}
