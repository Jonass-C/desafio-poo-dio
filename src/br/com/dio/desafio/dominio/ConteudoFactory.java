package br.com.dio.desafio.dominio;

import java.time.LocalDate;

public class ConteudoFactory {

    public static Curso criarCurso(String titulo, String descricao, int cargaHoraria) {
        return new Curso(titulo, descricao, cargaHoraria);
    }

    public static Mentoria criarMentoria(String titulo, String descricao, LocalDate data) {
        return new Mentoria(titulo, descricao, data);
    }

}
