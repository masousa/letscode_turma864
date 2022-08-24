package org.example;

import org.example.dominio.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ExemploFilter {
    /***
     * Listar os alunos de ano de nascimento = 2021
     * @param args
     */
    public static void main(String[] args) {

        final LocalDate localDate = LocalDate.of(2000, 8, 8);

        Aluno aluno = new Aluno("José", "001", localDate);
        Aluno alunoMaisNovo = new Aluno("José Mais Novo", "002", LocalDate.of(2021, 7, 1));

        // Equivalente
        List<Aluno> alunosListAntigo = new ArrayList<>();
        alunosListAntigo.add(aluno);
        alunosListAntigo.add(alunoMaisNovo);

        List<Aluno> alunos = Arrays.asList(aluno, alunoMaisNovo);
        for (Aluno alunoForEach : alunos) {
            if (alunoForEach.getNascimentoLocalDate().getYear() == 2021) {
                System.out.println(alunoForEach.getNome());
            }
        }
        alunos.forEach(alunosForEachStream -> {
            if (alunosForEachStream.getNascimentoLocalDate().getYear() == 2021) {
                System.out.println(alunosForEachStream.getNome());
            }
        });
        Stream<Aluno> alunosStreamComAnoIgual2021 = alunos.stream().filter(alunoFilter -> alunoFilter.getNascimentoLocalDate().getYear() == 2021);
        alunosStreamComAnoIgual2021.forEach(aluno1 -> System.out.println(aluno1.getNome()));

        alunos.stream().filter(alunoFilter -> alunoFilter.getNascimentoLocalDate().getYear() == 2021).forEach(alunoImpressao -> System.out.println(alunoImpressao.getNome()));

    }
}
