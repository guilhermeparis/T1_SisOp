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
			path = Paths.get(System.getProperty("user.dir") + "//src//teste1.txt");
		}
		
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())){
			
			int processos, fatiaDeTempo, l = 0;
			int fimDasEntradas = -1; //essa variavel eh necessaria p/ para a leitura na primeira linha vazia
			String linha = null;
			while ((linha = br.readLine()) != null){
				Scanner sc = new Scanner(linha);
				if(l == fimDasEntradas) break;
				switch(l){
				case 0:
					processos = sc.nextInt();
					System.out.println("Total de processos: " + processos+"\n");
					listaProcessos = new Processo[processos];
					fimDasEntradas = processos+2;
					break;
				case 1:
					fatiaDeTempo = sc.nextInt();
					System.out.println("Fatia de Tempo: " + fatiaDeTempo+"\n");
					setFatiaDeTempo(fatiaDeTempo);
					break;
				default:
					int entrada = 0;
					int saida = 0;
					System.out.println("Processo P" + (l-1));
					int n = (l-1);
					int c = sc.nextInt();
					System.out.println("Chegada: " + c);
					int e = sc.nextInt();
					System.out.println("Execucao: " + e);
					int p = sc.nextInt();
					System.out.println("Prioridade: " + p +"\n");
					if(sc.hasNextInt()){//tem operacao de ENTRADA
						entrada = sc.nextInt();
						System.out.println("Entrada: " + entrada);
						if(sc.hasNextInt()){//tem operacao de SAIDA
							saida = sc.nextInt();
							System.out.println("Saida: " + saida);
						}
					}
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
