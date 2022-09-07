package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BufferWriterExample {
    public static void main(String[] args) {
        Path caminho = Paths.get("/home/matheus/Documentos/Aulas/letscode/documentoQualquer.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardOpenOption.APPEND)) {
            writer.write("Escrevendo conteudo do arquivo  ");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
