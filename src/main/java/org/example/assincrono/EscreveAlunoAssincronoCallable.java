package org.example.assincrono;

import org.example.dominio.Aluno;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class EscreveAlunoAssincronoCallable implements Callable<Aluno> {
    private static Path caminho = Paths.get("/home/matheus/Documentos/Aulas/letscode/alunos.csv");

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Aluno aluno;

    public EscreveAlunoAssincronoCallable(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public Aluno call() throws Exception {
        return escreverCSVAluno(aluno, 5000);
    }

    private Aluno escreverCSVAluno(Aluno aluno, Integer sleepTime) throws IOException {
        verificarExistenciaArquivo();
        try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardOpenOption.APPEND)) {
            System.out.printf("Assincrono 01: Escrevendo aluno %s mas primeiro vou dormir por 5s %n", aluno.getNome());
            Thread.sleep(sleepTime);
            String alunoTexto = String.format("%s,%s,%s", aluno.getNome(), aluno.getMatricula(), dateTimeFormatter.format(aluno.getNascimentoLocalDate()));
            writer.write(alunoTexto);
            writer.newLine();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Assincrono 02: Aluno %s foi escrito no arquivo %n", aluno.getNome());
        return aluno;
    }

    private void verificarExistenciaArquivo() throws IOException {
        if (!caminho.toFile().exists()) {
            caminho.toFile().createNewFile();
        }
    }
}
