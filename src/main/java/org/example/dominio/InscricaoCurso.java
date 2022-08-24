package org.example.dominio;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.validacao.ValidaInscricaoCurso;

import java.time.LocalDate;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
public class InscricaoCurso {

    private Aluno aluno;
    private Curso curso;
    private LocalDate dataInicioDoCurso;

    public InscricaoCurso(Aluno aluno, Curso curso, LocalDate dataInicioDoCurso, String nomeDoCursoAnterior,
                          ValidaInscricaoCurso validaInscricaoCurso) {

        if (validaInscricaoCurso.isValid(aluno, nomeDoCursoAnterior)) {
            this.aluno = aluno;
            this.curso = curso;
            this.dataInicioDoCurso = dataInicioDoCurso;
        } else {
            throw new RuntimeException("Não é possível se inscrever no curso");
        }

    }

    public InscricaoCurso(Aluno aluno, Curso curso, LocalDate dataInicioDoCurso, Predicate<Aluno> alunoPredicate) {

        if (alunoPredicate.test(aluno)) {
            this.aluno = aluno;
            this.curso = curso;
            this.dataInicioDoCurso = dataInicioDoCurso;
        } else {
            throw new RuntimeException("Não é possível se inscrever no curso");
        }

    }


}
