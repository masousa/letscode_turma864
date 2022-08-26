package org.example.excecao;

public class NaoTemAlunoException extends RuntimeException {
    public NaoTemAlunoException(String message) {
        super(message);
    }
}
