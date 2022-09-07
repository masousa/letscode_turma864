package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferReaderExample {
    public static void main(String[] args) {

        Path caminho = Paths.get("/home/matheus/Documentos/Aulas/letscode/entradaDeDados");

        try (BufferedReader reader = Files.newBufferedReader(caminho)) {

            String linha = null;
            while ((linha = reader.readLine()) != null) {

                System.out.println(linha);

            }
            System.out.println("Terminou");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
