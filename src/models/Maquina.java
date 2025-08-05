package models;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Maquina {
    private String nombre;
    private String ip;
    private List<Integer> codigos;

    public Maquina(String nombre, String ip, List<Integer> codigos) {
        this.nombre = nombre;
        this.ip = ip;
        this.codigos = codigos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public void setCodigos(List<Integer> codigos) {
        this.codigos = codigos;
    }

    public int getSubred() {
        String[] octetos = ip.split("\\.");
        if (octetos.length >= 3) {
            try {
                return Integer.parseInt(octetos[2]);
            } catch (NumberFormatException e) {
                System.err.println("Error al parsear la subred de la IP: " + ip + ". Detalles: " + e.getMessage());
                return -1; 
            }
        }
        return -1;
    }

    public int getRiesgo() {
        int sumaCodigosDivisiblesPor5 = 0;
        if (codigos != null) {
            for (Integer codigo : codigos) {
                if (codigo % 5 == 0) {
                    sumaCodigosDivisiblesPor5 += codigo;
                }
            }
        }

        Set<Character> caracteresUnicos = new HashSet<>();
        if (nombre != null) {
            String nombreSinEspacios = nombre.replace(" ", "");
            for (char c : nombreSinEspacios.toCharArray()) {
                caracteresUnicos.add(c);
            }
        }
        int numeroCaracteresUnicos = caracteresUnicos.size();

        return sumaCodigosDivisiblesPor5 * numeroCaracteresUnicos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, ip, codigos);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Maquina other = (Maquina) obj;
        return Objects.equals(nombre, other.nombre) &&
               Objects.equals(ip, other.ip) &&
               Objects.equals(codigos, other.codigos);
    }
}