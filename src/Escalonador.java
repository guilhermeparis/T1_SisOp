
public class Escalonador {

	private Processo[] listaProcessos;
	private int fatiaDeTempo, comparadorPrioridade, indicePriorizado, robinCounter, cicloAtual;

	public Escalonador(Processo[] listaProcessos, int fatiaDeTempo) {
		this.listaProcessos = listaProcessos;
		this.fatiaDeTempo = fatiaDeTempo;
		this.comparadorPrioridade = 99;
		this.indicePriorizado = -1;
		this.robinCounter = 0;
		this.cicloAtual = 0;
	}

	public void executa(Processo[] listaProcessos) {
		// considere -1 como "unassigned", o valor que
		// nao esta vinculado a nenhum dos processos
		
		////// trocar de volta para (!todosTerminados(listaProcessos))
		while (cicloAtual < 80) {
			// Enquanto houver pelo menos um pendente
			cicloAtual++;
			// sobe o ciclo de execucao (primeira ocorrencia eh 1)
			indicePriorizado = confereChegadas();

			if (indicePriorizado == -1) {
				// ou nao ha nenhum executando
				System.out.print("-");
			} else {
				// ou ha
				listaProcessos[indicePriorizado].atende();
				// atende o processo mais importante
				robinCounter++;
				// sobe o contador da fatia de tempo
				System.out.print(listaProcessos[indicePriorizado].getNumero());
				if (listaProcessos[indicePriorizado].getRestante() == 0) {
					// acabou de ser terminado
					listaProcessos[indicePriorizado].setTerminado(true);
					// seta como terminado
					indicePriorizado = -1;
					// indicePriorizado volta a ser unassigned
					robinCounter = 0;
					// zera robin
				}
				if (robinCounter == fatiaDeTempo) {
					indicePriorizado = aplicaRoundRobin(indicePriorizado);
					// vai conferir se tem outro no mesmo nivel
					robinCounter = 0;
					//zera robinCounter
				}
				
			}
		}

	}

	public int confereChegadas() {
		int indiceEntrada = indicePriorizado;
		//guarda o valor do indice que entrou
		for (int i = 0; i < listaProcessos.length; i++) {
			// Percorre a lista de processos
			if (listaProcessos[i].isTerminado()) {
				// Se o processo lido ja estiver completo, manda para o
				// proximo [item] do for.
				continue;
			}
			if (listaProcessos[i].getChegada() == cicloAtual) {
				// Tem algum processo pro tempo de chegada atual?
				if (listaProcessos[i].getPrioridade() <= comparadorPrioridade) {
					// Ele eh mais importante que o anterior?
					comparadorPrioridade = listaProcessos[i].getPrioridade();
					// Atualiza a prioridade
					indicePriorizado = i;
					// Marca o indice desse novo priorizado
				}
			}
		}
		int indiceSaida = indicePriorizado;
		//guarda o valor do indice que pode ter sido mexido
		if(indiceEntrada != indiceSaida){
			//realmente vai rolar troca de processo
			robinCounter = 0;
		}
		return indicePriorizado;
		// normalmente vai retornar o que ja estava
	}

	public int aplicaRoundRobin(int indicePriorizado) {
		int prioridadeAtual = listaProcessos[indicePriorizado].getPrioridade();
		// guarda a prioridade do atual
		for (int i = 0; i < listaProcessos.length; i++) {
			if (i == indicePriorizado) {
				// pula o que ja estava executando e vai procurar outro
				continue;
			}

			if (listaProcessos[i].getPrioridade() == prioridadeAtual &&
					listaProcessos[i].getChegada() < cicloAtual) {
				// foi encontrado outro com a mesma prioridade
				System.out.print("C");
				cicloAtual++;
				return i;
			}
		}
		return indicePriorizado;
		//se nao foi encontrado, entao segue
	}

	public boolean todosTerminados(Processo[] listaProcessos) {
		for (int i = 0; i < listaProcessos.length; i++) {
			if (!listaProcessos[i].isTerminado()) {
				return false;
			}
		}
		return true;
	}

}
