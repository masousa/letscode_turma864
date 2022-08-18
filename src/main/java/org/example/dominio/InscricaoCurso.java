package org.example.dominio;

import java.time.LocalDate;

public class InscricaoCurso {

    private Aluno aluno;
    private Curso curso;
    private LocalDate dataInicioDoCurso;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public LocalDate getDataInicioDoCurso() {
        return dataInicioDoCurso;
    }

    public void setDataInicioDoCurso(LocalDate dataInicioDoCurso) {
        this.dataInicioDoCurso = dataInicioDoCurso;
    }
}
