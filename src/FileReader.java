import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReader{
	
	private int fatiaDeTempo = 0;
	//L� o arquivo, monta a lista de processos, e a retorna para a main.
	public Processo[] readFile(Processo[] listaProcessos){
		
		Path path = Paths.get(System.getProperty("user.dir") + "\\src\\file01.txt");
		
		if(System.getProperty("os.name").equals("Linux")){
			path = Paths.get(System.getProperty("user.dir") + "//src//file01.txt");
		}
		
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())){
			
			int processos, fatiaDeTempo, l = 0;
			String linha = null;
			while ((linha = br.readLine()) != null){
				Scanner sc = new Scanner(linha);
				//System.out.println(linha);
				switch(l){
				case 0:
					processos = sc.nextInt();
					System.out.println("Total de processos: " + processos+"\n");
					listaProcessos = new Processo[processos];
					break;
				case 1:
					fatiaDeTempo = sc.nextInt();
					System.out.println("Fatia de Tempo: " + fatiaDeTempo+"\n");
					setFatiaDeTempo(fatiaDeTempo);
					break;
				default:
					System.out.println("Processo P" + (l-1));
					int n = (l-1);
					int c = sc.nextInt();
					System.out.println("Chegada: " + c);
					int e = sc.nextInt();
					System.out.println("Execucao: " + e);
					int p = sc.nextInt();
					System.out.println("Prioridade: " + p +"\n");
					Processo x = new Processo(n, c, e, p);
					listaProcessos[l-2] = x;
					break;
				}
				l++;
			}
		}

		catch (IOException e) {
			System.err.format("Erro de E/S: %s%n", e);
		}
		return listaProcessos;
	}
	
	public int getFatiaDeTempo() {
		return fatiaDeTempo;
	}
	
	public void setFatiaDeTempo(int fatiaDeTempo) {
		this.fatiaDeTempo = fatiaDeTempo;
	}

}
