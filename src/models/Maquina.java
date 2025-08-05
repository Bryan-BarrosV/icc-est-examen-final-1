package models;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Maquina {
  private String nombre;
  private String ip;
  private List<Integer> codigos;
  private int subred;
  private int riesgo;

  public Maquina(String var1, String var2, List<Integer> var3) {
    this.nombre = var1;
    this.ip = var2;
    this.codigos = var3;
    this.riesgo = this.calcularRiesgo();
    this.subred = this.calcularSubred();
  }

  private int calcularSubred() {

    return Integer.parseInt(ip.split("\\.")[3]);
  }

  private int calcularRiesgo() {
    int suma = 0;
    for (int codigo : codigos) {
      if (codigo % 3 == 0) {
        suma += codigo;
      }
    }

    Set<Character> var7 = new HashSet<>();

    for (Character character : nombre.toCharArray()) {
      if (character != ' ') {
        var7.add(character);
      }

    }

    return suma * var7.size();
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getIp() {
    return this.ip;
  }

  public List<Integer> getCodigos() {
    return this.codigos;
  }

  public int getSubred() {
    return this.subred;
  }

  public int getRiesgo() {
    return this.riesgo;
  }

  public String toString() {
    return this.nombre + " - Subred: " + this.subred + " - Riesgo: " + this.riesgo;
  }

  public boolean equals(Object var1) {
    if (this == var1) {
      return true;
    } else if (!(var1 instanceof Maquina)) {
      return false;
    } else {
      Maquina var2 = (Maquina) var1;
      return this.subred == var2.subred && Objects.equals(this.nombre, var2.nombre);
    }
  }

  public int hashCode() {
    return Objects.hash(new Object[] { this.nombre, this.subred });
  }
}
