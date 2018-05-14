
public class Escalonador{

	private Processo[] listaProcessos;
	private int fatiaDeTempo, indicePriorizado, robinCounter, cicloAtual;

	public Escalonador(Processo[] listaProcessos, int fatiaDeTempo) {
		this.listaProcessos = listaProcessos;
		this.fatiaDeTempo = fatiaDeTempo;
		//this.comparadorPrioridade = 99;
		this.indicePriorizado = -1;
		this.robinCounter = 0;
		this.cicloAtual = 0;
	}

	public void executa(Processo[] listaProcessos) {
		// considere -1 como "unassigned", o valor que nao esta vinculado a nenhum dos processos
		
		// trocar de volta para (!todosTerminados())
		while (!todosTerminados()) {
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
					trocaDeContexto();
					//realiza troca de contexto
				}
				if (robinCounter == fatiaDeTempo) {
					indicePriorizado = aplicaRoundRobin();
					// vai conferir se tem outro no mesmo nivel
					robinCounter = 0;
					//zera robinCounter
					trocaDeContexto();
					//realiza troca de contexto
				}
				
			}
		}

	}

	public int confereChegadas() {
		int indiceEntrada = indicePriorizado;
		//guarda o valor do indice que entrou
		for (int i = listaProcessos.length-1; i >=0; i--) {
			// Percorre a lista de processos
			if (listaProcessos[i].isTerminado() || listaProcessos[i].isLock()) {
				// Se o processo lido ja estiver completo, manda para o
				// proximo [item] do for.
				continue;
			}
			if (listaProcessos[i].getChegada() <= cicloAtual) {
				// Tem algum processo pro tempo de chegada atual?
				if (listaProcessos[i].getPrioridade() <= flagPrioridade()) {
					// Ele eh mais importante que o anterior?
					//comparadorPrioridade = listaProcessos[i].getPrioridade();
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

	public int aplicaRoundRobin() {
		for (int i = 0; i < listaProcessos.length; i++) {
			if (i == indicePriorizado || listaProcessos[i].isTerminado()) {
				// pula o que ja estava executando e vai procurar outro
				continue;
			}
			//Está relacionado a todos os demais processos -> deve ser da mesma prioridade que a flag e tem que também já ter chegado na lista para disputar CPU -> Em outras palavras, deve estar em espera. 
			if (listaProcessos[i].getPrioridade() == flagPrioridade() && listaProcessos[i].getChegada() < cicloAtual) {
				//System.out.println("Processo: " + i + " trocou.");
				// foi encontrado outro com a mesma prioridade
				//listaProcessos[indicePriorizado].setLock(true);
				//desbloqueiaTodosOutros();
				return i;
			}
		}
		return indicePriorizado;
		//se nao foi encontrado, entao segue
	}



	public int flagPrioridade() {
		int prioridade = 99; 
		for(int i = 0; i < listaProcessos.length; i++){
			if(listaProcessos[i].isTerminado()){
				continue;
				//irrelevante
			}
			if(listaProcessos[i].getPrioridade() < prioridade){
				 prioridade = listaProcessos[i].getPrioridade();
			}
		}
		return prioridade;
	}
	
	public void trocaDeContexto(){
		System.out.print("C");
		cicloAtual++;
	}
	
	public void aftermath(){
		System.out.print("\n\nRestante:\t");
		for(int i = 0; i<listaProcessos.length; i++){
			System.out.print("["+listaProcessos[i].getRestante()+"]\t");
		}
		System.out.print("\n\nTerminado:\t");
		for (int i = 0; i < listaProcessos.length; i++) {
			if (listaProcessos[i].isTerminado()) {
				System.out.print("[S]\t");
			}else{
				System.out.print("[N]\t");
			}
		}
	}

	public void desbloqueiaTodosOutros(){
		for (int i = 0; i < listaProcessos.length; i++) {
			if (i == indicePriorizado) {
				continue; //esse continua bloqueado
			} else {
				listaProcessos[i].setLock(false); //desbloqueia, mesmo se nao estiver bloqueado
			}
		}
		
	}
	
	public boolean todosTerminados() {
		for (int i = 0; i < listaProcessos.length; i++) {
			if (!listaProcessos[i].isTerminado()) {
				return false;
			}
		}
		return true;
	}

}
