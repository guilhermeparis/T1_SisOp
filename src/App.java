
public class App {

    public static void main(String args[]) {
    	Processo[] listaProcessos = null;
        Escalonador esc = new Escalonador();
        listaProcessos = esc.readFile(listaProcessos); 
        esc.imprimeVetor(listaProcessos);
    }
}