/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Medicamento;
import java.util.ArrayList;

/**
 *
 * @author joaod
 */
public class Estoque {

    private ArrayList<Medicamento> estoque;

    public Estoque(ArrayList<Medicamento> estoque) {
        this.estoque = estoque;
    }

    public void addMedicamentoNoEstoque(Medicamento m) {
        this.estoque.add(m);
    }

    public boolean retirarMedicamento(int m) {
        boolean controle = true;
        for (Medicamento med : this.estoque) {
            if (med.getCodigo() == m) {
                this.estoque.remove(med);
                break;
            } else {
                controle = false;
            }
        }
        return controle;
    }

    public int BuscarCategoriaDeMedicamentos(String descricao) {
        int contador = 0;
        for (Medicamento m : this.estoque) {
            if (m.getNome().equals(descricao)) {
                contador++;
            }
        }
        return contador;
    }

    public void ListarMedicamentos() {
        System.out.println("----- Medicamentos no estoque -------");
        for (Medicamento m : this.estoque) {
            System.out.println("Código do medicamento: " + m.getCodigo());
            System.out.println("Nome do medicamento: " + m.getNome());
            System.out.println("");
        }
    }

    public boolean buscarCodigoMedicamento(int codigo) {
        boolean verifica = true;
        for (Medicamento m : this.estoque) {
            if (m.getCodigo() == codigo) {
                verifica = true;
                break;
            } else {
                verifica = false;
        
            }
        }
        return verifica;
    }

    public ArrayList<Medicamento> GetEstoque() {
        return this.estoque;
    }

}
