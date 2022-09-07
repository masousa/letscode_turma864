package org.example;

import org.example.dominio.Aluno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlunosNoArquivo {
    private static Path caminho = Paths.get("/home/matheus/Documentos/Aulas/letscode/alunos.csv");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("José", "001", LocalDate.of(2021, 6, 9));
        Aluno aluno2 = new Aluno("José Mais Novo", "002", LocalDate.of(2021, 7, 1));
        Aluno aluno3 = new Aluno("Maria", "003", LocalDate.of(2021, 8, 9));
        Aluno aluno4 = new Aluno("Carla", "004", LocalDate.of(2022, 8, 9));
        Aluno aluno5 = new Aluno("Marcelo", "005", LocalDate.of(2022, 8, 9));

        try {

            escreverCSVAluno(aluno1);
            escreverCSVAluno(aluno2);
            lerCSVAluno();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Deu erro");
        }
    }

    public static void escreverCSVAluno(Aluno aluno) throws IOException {
        verificarExistenciaArquivo();
        try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardOpenOption.APPEND)) {

            String alunoTexto = String.format("%s,%s,%s", aluno.getNome(), aluno.getMatricula(), dateTimeFormatter.format(aluno.getNascimentoLocalDate()));
            writer.write(alunoTexto);
            writer.newLine();
        }
    }

    private static void verificarExistenciaArquivo() throws IOException {
        if (!caminho.toFile().exists()) {
            caminho.toFile().createNewFile();
        }
    }

    public static List<Aluno> lerCSVAluno() throws IOException {
        verificarExistenciaArquivo();
        BufferedReader reader = Files.newBufferedReader(caminho);
        String linha = null;
        List<Aluno> alunos = new ArrayList<>();
        while ((linha = reader.readLine()) != null) {
            String[] itens = linha.split(",");
            Aluno aluno = new Aluno(itens[0], itens[1], LocalDate.parse(itens[2], dateTimeFormatter));
            alunos.add(aluno);
            System.out.println(aluno.getNome());
        }
        return alunos;
    }


}
