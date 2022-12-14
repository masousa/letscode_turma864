package org.example.dominio;

import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class Aluno implements Comparable<Aluno> {


    private String nome;
    private String matricula;
    private Date dataNascimento;

    private int numeroCadastro;
    private Collection<InscricaoCurso> inscricaoCursoList;

    public Aluno(String nome, String matricula, LocalDate dataNascimento) {
        this.nome = nome;
        this.matricula = matricula;

        this.dataNascimento = Date.from(dataNascimento.atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant());


    }

    public Aluno() {
    }

    public String apresentar() {

        LocalDate dataNascimento = getNascimentoLocalDate();

        return String.format("Aluno: %s de matricula %s com data de nascimento %s (%d anos)" +
                        ", %n \t Cursos %n%s",
                this.getNome(), this.getMatricula(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataNascimento),
                Period.between(dataNascimento, LocalDate.now()).getYears()
                , getCursos());
    }

    public LocalDate getNascimentoLocalDate() {
        return this.dataNascimento.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public String apresentarDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataNascimento);
        String dataFormatada = String.format("%1$te/%1$tm/%1$tY", calendar);
        return String.format("Aluno: %s de matricula %s com data de nascimento %s " +
                        ", %n \t Cursos %n%s",
                this.getNome(), this.getMatricula(),
                dataFormatada
                , getCursos());
    }

    private String getCursos() {
        StringBuilder builder = new StringBuilder();
        builder.append("Curso \t Data Inicio \t Data T??rmino \t Dias para come??ar o curso \n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (InscricaoCurso objetoInscricaoCurso : inscricaoCursoList) {
            final LocalDate dataTermino = objetoInscricaoCurso.getDataInicioDoCurso()
                    .plusMonths(objetoInscricaoCurso.getCurso().getDuracao());
            builder.append(String.format("%s \t %s \t %s \t %d %n",
                    objetoInscricaoCurso.getCurso().getNome()
                    , formatter.format(objetoInscricaoCurso.getDataInicioDoCurso()),
                    formatter.format(dataTermino),
                    Duration.between(LocalDate.now().atStartOfDay(), objetoInscricaoCurso.getDataInicioDoCurso().atStartOfDay())
                            .toDays()
            ));
        }

        return builder.toString();
    }

    public boolean containsCurso(String nomeCurso) {
        List<String> nomesCursos = getNomesDosCursos(this.getInscricaoCursoList());
        return nomesCursos.contains(nomeCurso);
    }

    private List<String> getNomesDosCursos(Collection<InscricaoCurso> inscricaoCursoList) {
        List<String> nomesDosCursos = new ArrayList<>();
        for (InscricaoCurso inscricaoCurso : inscricaoCursoList) {
            nomesDosCursos.add(inscricaoCurso.getCurso().getNome());
        }
        return nomesDosCursos;
    }


    @Override
    public int compareTo(Aluno o) {
        return this.getNascimentoLocalDate().compareTo(o.getNascimentoLocalDate());
    }
}
