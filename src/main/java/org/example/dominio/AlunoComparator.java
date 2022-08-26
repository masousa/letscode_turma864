package org.example.dominio;

import java.util.Comparator;

public class AlunoComparator implements Comparator<Aluno> {
    @Override
    public int compare(Aluno o1, Aluno o2) {
        if (o1.getNumeroCadastro() < o2.getNumeroCadastro()) {
            return -1;
        } else if (o1.getNumeroCadastro() > o2.getNumeroCadastro()) {
            return 1;
        }
        return 0;
    }
}
