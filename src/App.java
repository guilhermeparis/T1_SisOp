
public class App {

	public static void main(String args[]) {
		Processo[] listaProcessos = null;
		//instancia lista de processos vazia
		FileReader populator = new FileReader();
		//instancia leitor de arquivo
		listaProcessos = populator.readFile(listaProcessos);
		//popula lista de processos
		int fatiaDeTempo = populator.getFatiaDeTempo();
		//recolhe a fatia de tempo
		imprimeVetor(listaProcessos);
		//imprime dados dos processos
		Escalonador esc = new Escalonador(listaProcessos, fatiaDeTempo);
		//manda lista e fatia para o escalonador
		esc.executa(listaProcessos);
		esc.aftermath();
	}

	// Recebe a lista de processos populada e l� seus valores
	public static void imprimeVetor(Processo[] listaProcessos) {
		System.out.print("Processo:\t");
		for (int i = 1; i <= listaProcessos.length; i++) {
			System.out.print("P" + i + "\t");
		}

		System.out.print("\nChegada:\t");
		for (int i = 0; i < listaProcessos.length; i++) {
			System.out.print("[" + listaProcessos[i].getChegada() + "]\t");
		}

		System.out.print("\nExecucao:\t");
		for (int i = 0; i < listaProcessos.length; i++) {
			System.out.print("[" + listaProcessos[i].getExecucao() + "]\t");
		}

		System.out.print("\nPrioridade:\t");
		for (int i = 0; i < listaProcessos.length; i++) {
			System.out.print("[" + listaProcessos[i].getPrioridade() + "]\t");
		}
		System.out.println("\n\n");
	}

}