package org.example;

import org.example.assincrono.EscreveAlunoAssincronoCallable;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AlunosNoArquivo {
    private static final ExecutorService pool = Executors.newFixedThreadPool(3);
    private static Path caminho = Paths.get("/home/matheus/Documentos/Aulas/letscode/alunos.csv");
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("José", "001", LocalDate.of(2021, 6, 9));
        Aluno aluno2 = new Aluno("José Mais Novo", "002", LocalDate.of(2021, 7, 1));
        Aluno aluno3 = new Aluno("Maria", "003", LocalDate.of(2021, 8, 9));
        Aluno aluno4 = new Aluno("Carla", "004", LocalDate.of(2022, 8, 9));
        Aluno aluno5 = new Aluno("Marcelo", "005", LocalDate.of(2022, 8, 9));

        try {

            System.out.println("Começando os cadastros");

            EscreveAlunoAssincronoCallable escreveAlunoAssincronoCallable = new EscreveAlunoAssincronoCallable(aluno3);
            Future<Aluno> alunoFuture3 = pool.submit(escreveAlunoAssincronoCallable);

            CompletableFuture<Aluno> alunoCompletableFuture4 = CompletableFuture.supplyAsync(() -> {
                Aluno aluno = null;
                try {
                    aluno = escreverCSVAluno(aluno4, 2000);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return aluno;
            });

            System.out.println("Finalizando o trabalho");

            while (!alunoCompletableFuture4.isDone() || !alunoFuture3.isDone()) {
                System.out.println("Future 3 " + alunoFuture3.isDone());
                System.out.println("Future 4 " + alunoCompletableFuture4.isDone());
                Thread.sleep(1000);
            }
            System.out.println(alunoFuture3.get().getNome());
            lerCSVAluno();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Deu erro");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            System.exit(0);
        }

    }

    public static Aluno escreverCSVAluno(Aluno aluno, Integer sleepTime) throws IOException {
        verificarExistenciaArquivo();
        try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardOpenOption.APPEND)) {
            Thread.sleep(sleepTime);
            String alunoTexto = String.format("%s,%s,%s", aluno.getNome(), aluno.getMatricula(), dateTimeFormatter.format(aluno.getNascimentoLocalDate()));
            writer.write(alunoTexto);
            writer.newLine();
            System.out.printf("Aluno inserido %s no arquivo pelo método escreverCSVAluno e dormiu por 2s %n", aluno.getNome());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return aluno;
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
        }
        return alunos;
    }


}
