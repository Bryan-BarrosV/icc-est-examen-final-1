package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.lang.model.util.ElementScanner14;

import models.Maquina;

public class MaquinaController {
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina maquina : maquinas) {
            if (maquina.getSubred()> umbral){
                resultado.push(maquina)
            }
        }
        return resultado;
         
    }

    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
    TreeSet<Maquina> ordenadas = new TreeSet<>();
    for (Maquina maquina : pila) {
        ordenadas.add(maquina); 
    }
    return ordenadas;}


    public TreeMap<Integer, Queue<Maquina>> agruparPorRiesgo(List<Maquina> maquinas) {
    TreeMap<Integer, Queue<Maquina>> mapa = new TreeMap<>();
    for (Maquina m : maquinas) {
        int riesgo = m.getRiesgo();
        mapa.putIfAbsent(riesgo, new LinkedList<>());
        mapa.get(riesgo).add(m);
    }
    return mapa;
}

    public Stack<Maquina> explotarGrupo(Map<Integer, Queue<Maquina>> mapa) {
    int maxCantidad = -1;
    int riesgoSeleccionado = -1;

    for (Map.Entry<Integer, Queue<Maquina>> entry : mapa.entrySet()) {
        int riesgo = entry.getKey();
        int cantidad = entry.getValue().size();

        if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > riesgoSeleccionado)) {
            maxCantidad = cantidad;
            riesgoSeleccionado = riesgo;
        }
    }

    Queue<Maquina> grupo = mapa.get(riesgoSeleccionado);
    Stack<Maquina> resultado = new Stack<>();
    for (Maquina m : grupo) {
        resultado.push(m);
    }
    return resultado;

}

}
