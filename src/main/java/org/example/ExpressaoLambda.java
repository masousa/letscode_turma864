package org.example;

import org.example.dominio.Aluno;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ExpressaoLambda {

    public static void main(String[] args) {
        List<Aluno> alunos = Arrays.asList(
                new Aluno("Carlos", "004", LocalDate.of(2010, 2, 7)),
                new Aluno("Maria", "001", LocalDate.of(2000, 8, 8)),
                new Aluno("José", "002", LocalDate.of(2002, 7, 5)),
                new Aluno("Isabela", "003", LocalDate.of(2010, 9, 8))
        );

        // Classe Anonima
        alunos.sort(new Comparator<Aluno>() {
            @Override
            public int compare(Aluno o1, Aluno o2) {
                return o1.getNascimentoLocalDate().compareTo(o2.getNascimentoLocalDate());
            }
        });
        alunos.sort((Aluno o1, Aluno o2) -> o1.getNascimentoLocalDate().compareTo(o2.getNascimentoLocalDate()));

        // Equivalente
        alunos.sort(new Comparator<Aluno>() {
            @Override
            public int compare(Aluno o1, Aluno o2) {
                return o1.compareTo(o2);
            }
        });
        alunos.sort(Aluno::compareTo);

        alunos.forEach(aluno -> System.out.println(aluno));

    }
}
