package org.example;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsandoOPath {
    public static void main(String[] args) {
        Path caminho = Paths.get("/home/matheus/Documentos/Aulas/letscode/documento.csv");
        if (!caminho.toFile().exists()) {
            try {
                caminho.toFile().createNewFile();
                System.out.println("Arquivo criado");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
