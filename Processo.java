package src;

public class Processo {
	private String nome;
	private int prioridade;
	private int execucao;
	private int chegada;
	
	public Processo(String nome, int prioridade, int execucao, int chegada) {
		super();
		this.nome = nome;
		this.prioridade = prioridade;
		this.execucao = execucao;
		this.chegada = chegada;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getExecucao() {
		return execucao;
	}

	public void setExecucao(int execucao) {
		this.execucao = execucao;
	}

	public int getChegada() {
		return chegada;
	}

	public void setChegada(int chegada) {
		this.chegada = chegada;
	}
}
