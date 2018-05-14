
public class Processo {
	private int numero;
	private int prioridade;
	private int execucao;
	private int chegada;
	private int restante;
	private boolean terminado;
	private boolean lock;
	
	public void atende(){
		this.restante--;
	}
	
	public Processo(int numero, int chegada, int execucao, int prioridade) {
		super();
		this.setNumero(numero);
		this.chegada = chegada;
		this.execucao = execucao;
		this.prioridade = prioridade;
		this.restante = execucao;
		this.terminado = false;
		this.lock = false;
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

	public int getRestante() {
		return restante;
	}

	public void setRestante(int restante) {
		this.restante = restante;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isLock() {
		return lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}
}
