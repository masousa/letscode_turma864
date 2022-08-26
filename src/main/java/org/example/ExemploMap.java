package org.example;

import org.example.dominio.Aluno;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExemploMap {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno("José", "001", LocalDate.of(2021, 6, 9));
        Aluno aluno2 = new Aluno("José Mais Novo", "002", LocalDate.of(2021, 7, 1));
        Aluno aluno3 = new Aluno("Maria", "003", LocalDate.of(2021, 8, 9));
        Aluno aluno4 = new Aluno("Carla", "004", LocalDate.of(2022, 8, 9));
        Aluno aluno5 = new Aluno("Marcelo", "005", LocalDate.of(2022, 8, 9));

        List<Aluno> listaDeAlunos = Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5);

        for (Aluno alunoForEach : listaDeAlunos) {
            System.out.println(alunoForEach.getNome());
        }

        listaDeAlunos.forEach(alunoListaForEach -> System.out.println(alunoListaForEach.getNome()));

        List<Integer> nomes = new ArrayList<>();
        for (Aluno alunoForEach : listaDeAlunos) {
            nomes.add(alunoForEach.getNumeroCadastro());
        }

        listaDeAlunos.stream().map(aluno -> aluno.getNome()).forEach(nome -> System.out.println(nome + " Alguma coisa"));

        listaDeAlunos.stream().map(Aluno::getNome).forEach(System.out::println);
    }
}
