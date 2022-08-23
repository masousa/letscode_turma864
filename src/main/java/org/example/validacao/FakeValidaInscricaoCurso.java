package org.example.validacao;

import org.example.dominio.Aluno;

public class FakeValidaInscricaoCurso implements ValidaInscricaoCurso{
    @Override
    public boolean isValid(Aluno aluno, String nomeCurso) {
        return true;
    }
}
