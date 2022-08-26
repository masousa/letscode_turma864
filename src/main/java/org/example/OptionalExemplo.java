package org.example;

import org.example.dominio.Aluno;
import org.example.excecao.NaoTemAlunoException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalExemplo {
    public static void main(String[] args) {

        Aluno aluno1 = new Aluno("José", "001", LocalDate.of(2021, 6, 9));
        Aluno aluno2 = new Aluno("José Mais Novo", "002", LocalDate.of(2021, 7, 1));
        Aluno aluno3 = new Aluno("Maria", "003", LocalDate.of(2021, 8, 9));
        Aluno aluno4 = new Aluno("Carla", "004", LocalDate.of(2022, 8, 9));
        Aluno aluno5 = new Aluno("Marcelo", "005", LocalDate.of(2022, 8, 9));

        List<Aluno> listaDeAlunos = Arrays.asList(aluno1, aluno2, aluno3, aluno4, aluno5);

        Optional<Aluno> optionalAluno = listaDeAlunos.stream().filter(aluno -> aluno.getNascimentoLocalDate().getYear() == 2022)
                .findAny();

        optionalAluno.orElseThrow(() -> new NaoTemAlunoException("Nao tem nada"));

        System.out.println(optionalAluno.get().getNome());
    }
}
