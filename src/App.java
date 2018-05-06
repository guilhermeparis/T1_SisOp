
public class App {

    public static void main(String args[]) {
    	Processo[] listaProcessos = null;
        FileReader populator = new FileReader();
        listaProcessos = populator.readFile(listaProcessos); 
        imprimeVetor(listaProcessos);
    }
    
  //Recebe a lista de processos populada e lê seus valores
  	public static void imprimeVetor(Processo[] listaProcessos) {
  		System.out.print("Processo:\t");
  		for(int i = 1; i <= listaProcessos.length; i++){
  			System.out.print("P"+ i +"\t");
  		}
  		
  		System.out.print("\nChegada:\t");
  		for(int i = 0; i < listaProcessos.length; i++){
  			System.out.print("["+listaProcessos[i].getChegada()+"]\t");
  		}
  		
  		System.out.print("\nExecução:\t");
  		for(int i = 0; i < listaProcessos.length; i++){
  			System.out.print("["+listaProcessos[i].getExecucao()+"]\t");
  		}
  		
  		System.out.print("\nPrioridade:\t");
  		for(int i = 0; i < listaProcessos.length; i++){
  			System.out.print("["+listaProcessos[i].getPrioridade()+"]\t");
  		}
  	}
}