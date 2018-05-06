
public class Processo {
	private String nome;
	private int prioridade;
	private int execucao;
	private int chegada;
	
	public Processo(String nome, int chegada, int execucao, int prioridade) {
		super();
		this.nome = nome;
		this.chegada = chegada;
		this.execucao = execucao;
		this.prioridade = prioridade;
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
