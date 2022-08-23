package org.example.validacao;

import org.example.dominio.Aluno;

public interface ValidaInscricaoCurso {
    boolean isValid(Aluno aluno, String nomeCurso);
}
