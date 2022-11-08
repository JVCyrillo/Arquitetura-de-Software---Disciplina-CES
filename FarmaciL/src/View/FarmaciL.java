/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Estoque;
import Model.Medicamento;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

/**
 *
 * @author joaod
 */
public class FarmaciL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Medicamento> estoqueDinamicoEmTempoDeExecucao = new ArrayList();
        Estoque estoque = new Estoque(estoqueDinamicoEmTempoDeExecucao);
        System.out.println("--------Estoque de Farmácia em tempo de execução---------");
        int controleApp = 0;
        int opcaoUser = 0;
        while (controleApp == 0) {
            System.out.println("Digite [ 1 ] para cadastrar medicamento");
            System.out.println("Digite [ 2 ] para consultar estoque");
            System.out.println("Digite [ 3 ] para retirada de produto");
            System.out.println("Digite [ 4 ] para consultar quantidade de produto");
            System.out.println("Digite [ 0 ] para sair do sistema");
            opcaoUser = sc.nextInt();
            switch (opcaoUser) {
                case 1:
                    System.out.println("Insira o código do medicamento: ");
                    int codigo = sc.nextInt();
                    System.out.println("Insira o nome do medicamento: ");
                    String nome = sc.next();
                    Medicamento m = new Medicamento(codigo, nome);
                    estoque.addMedicamentoNoEstoque(m);
                    if (estoque.buscarCodigoMedicamento(codigo)) {
                        System.out.println("Medicamento inserido com sucesso");
                        estoqueDinamicoEmTempoDeExecucao = estoque.GetEstoque();
                        estoque = new Estoque(estoqueDinamicoEmTempoDeExecucao);
                        estoque.ListarMedicamentos();                        
                        Date dataHora = new Date();
                        String log = (dataHora + " - medicamento inserido de codigo " + codigo + " e nome " + nome);   
                        generateLog(log);                       

                    } else {
                        System.out.println("Medicamento não inserido");
                        Date dataHora = new Date();
                        String log = (dataHora + " - medicamento não inserido de codigo " + codigo + " e nome " + nome);   
                        generateLog(log);
                        
                    }
                    break;

                case 2:
                    estoque.ListarMedicamentos();
                    break;

                case 3:
                    System.out.println("Informe o codigo do produto a ser retirado do estoque: ");
                    int cod = sc.nextInt();
                    if (estoque.retirarMedicamento(cod)) {
                        System.out.println("Medicamento " + cod + " retirado com sucesso");
                        estoqueDinamicoEmTempoDeExecucao = estoque.GetEstoque();
                        estoque = new Estoque(estoqueDinamicoEmTempoDeExecucao);
                        estoque.ListarMedicamentos();

                    } else {
                        System.out.println("Medicamento inexistente ou erro durante a exclusão");
                    }

                    break;

                case 4:
                    System.out.println("Insira o nome do medicamento para identificadar a quantidade deste no estoque");
                    String descr = sc.next();
                    System.out.println("No sistema há um total de " + estoque.BuscarCategoriaDeMedicamentos(descr) + " " + descr + " no estoque");
                    break;
                default:
                    System.out.println("Valor invalido");
                    controleApp = 1;
            }

        }

    }
    
    public static void generateLog(String message) throws IOException {
		
		Path path = Paths.get("C:/Users/joaod/OneDrive/Documentos/EAD/Semestre 2022_2/Arquitetura e Projeto de Software/Tarefas/Tarefa 5/logs");
		
		if(!Files.exists(path)) {
			
			Files.createDirectory(path);
			
		}
		
		File log = new File("C:/Users/joaod/OneDrive/Documentos/EAD/Semestre 2022_2/Arquitetura e Projeto de Software/Tarefas/Tarefa 5/logs/log.txt");
		
		if(!log.exists()) {
			
			log.createNewFile();
		
		}
		
		FileWriter fw = new FileWriter(log, true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(message);
		bw.newLine();

		bw.close();
		fw.close();
		
	}
}


