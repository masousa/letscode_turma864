package org.example.dominio;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Aluno {

    private String nome;
    private String matricula;
    private Date dataNascimento;

    private List<InscricaoCurso> inscricaoCursoList;


    public String apresentar(){

        LocalDate dataNascimento = this.dataNascimento.toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        return String.format("Aluno: %s de matricula %s com data de nascimento %s (%d anos)" +
                        ", %n \t Cursos %n%s",
                this.getNome(), this.getMatricula(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy").format(dataNascimento),
                Period.between(dataNascimento,LocalDate.now()).getYears()
                ,getCursos());
    }

    public String apresentarDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataNascimento);
        String dataFormatada = String.format("%1$te/%1$tm/%1$tY", calendar);
        return String.format("Aluno: %s de matricula %s com data de nascimento %s " +
                        ", %n \t Cursos %n%s",
                this.getNome(), this.getMatricula(),
                dataFormatada
                ,getCursos());
    }

    private String getCursos() {
        StringBuilder builder = new StringBuilder();
        builder.append("Curso \t Data Inicio \t Data Término \n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (InscricaoCurso objetoInscricaoCurso: inscricaoCursoList) {
            final LocalDate dataTermino = objetoInscricaoCurso.getDataInicioDoCurso()
                    .plusMonths(objetoInscricaoCurso.getCurso().getDuracao());
            builder.append(String.format("%s \t %s \t %s %n",
                    objetoInscricaoCurso.getCurso().getNome()
                    ,formatter.format(objetoInscricaoCurso.getDataInicioDoCurso()),
                    formatter.format(dataTermino)));
        }

        return builder.toString();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<InscricaoCurso> getInscricaoCursoList() {
        return inscricaoCursoList;
    }

    public void setInscricaoCursoList(List<InscricaoCurso> inscricaoCursoList) {
        this.inscricaoCursoList = inscricaoCursoList;
    }
}
