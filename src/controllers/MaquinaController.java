package controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Comparator;

import models.Maquina;

public class MaquinaController {
    
    public Stack<Maquina> filtrarPorSubred(List<Maquina> maquinas, int umbral) {
        Stack<Maquina> resultado = new Stack<>();
        for (Maquina maquina : maquinas) {
            if (maquina.getSubred() > umbral) {
                resultado.push(maquina);
            }
        }
        return resultado;
    }

    public TreeSet<Maquina> ordenarPorSubred(Stack<Maquina> pila) {
        TreeSet<Maquina> ordenadas = new TreeSet<>(new Comparator<Maquina>() {
            @Override
            public int compare(Maquina m1, Maquina m2) {
                int subredCompare = Integer.compare(m2.getSubred(), m1.getSubred());
                if (subredCompare != 0) {
                    return subredCompare;
                }
                return m1.getNombre().compareTo(m2.getNombre());
            }
        });
        while (!pila.isEmpty()) {
            ordenadas.add(pila.pop());
        }
        return ordenadas;
    }

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
        if (mapa.isEmpty()) {
            return new Stack<>();
        }

        int maxCantidad = -1;
        int riesgoSeleccionado = -1;

        for (Integer riesgo : mapa.keySet()) {
            Queue<Maquina> grupo = mapa.get(riesgo);
            int cantidad = grupo.size();

            if (cantidad > maxCantidad || (cantidad == maxCantidad && riesgo > riesgoSeleccionado)) {
                maxCantidad = cantidad;
                riesgoSeleccionado = riesgo;
            }
        }

        Queue<Maquina> grupo = mapa.get(riesgoSeleccionado);
        Stack<Maquina> resultado = new Stack<>();
        if (grupo != null) {
            for (Maquina m : grupo) {
                resultado.push(m);
            }
        }
        return resultado;
    }
}
