package org.example;

import org.example.dominio.Aluno;
import org.example.dominio.Curso;
import org.example.dominio.InscricaoCurso;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        Aluno aluno = new Aluno();

        Curso curso = new Curso();
        curso.setNome("Técnicas de programação 1");
        curso.setDuracao(6L);

        InscricaoCurso inscricaoCurso = new InscricaoCurso();

        inscricaoCurso.setCurso(curso);
        inscricaoCurso.setDataInicioDoCurso(LocalDate.now());

        aluno.setInscricaoCursoList(new ArrayList<>());
        aluno.getInscricaoCursoList().add(inscricaoCurso);


        aluno.setMatricula("qualquer");
        aluno.setNome("Ronald");
        LocalDate localDataNascimento = LocalDate.of(2000, 9, 01);
        aluno.setDataNascimento(
                Date.from(
                localDataNascimento.atStartOfDay()
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );

        System.out.println(aluno.apresentar());

        LocalDateTime.now(ZoneId.of("Europe/Paris"));





    }
}
