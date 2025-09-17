import br.com.dio.desafio.dominio.*;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        Curso curso1 = ConteudoFactory.criarCurso("Curso Java", " Curso java", 50);

        Curso curso2 = ConteudoFactory.criarCurso("Curso spring", " Curso spring", 20);

        Mentoria mentoria = ConteudoFactory.criarMentoria("Mentoria java", " Mentoria java", LocalDate.now());

        Bootcamp bootcamp = Bootcamp.builder()
                .nome("Bootcamp Java Developer")
                .descricao("Descrição Bootcamp Java Developer")
                .conteudo(curso1)
                .conteudo(curso2)
                .conteudo(mentoria)
                .build();

        Dev dev1 = Dev.builder()
                .nome("Jonas")
                .conteudoInscrito(bootcamp)
                .build();

        Dev dev2 = Dev.builder()
                .nome("João")
                .conteudoInscrito(bootcamp)
                .build();

        System.out.println("\n--- " + dev1.getNome() + " ---");
        System.out.println("Conteúdos inscritos: " + dev1.getConteudosInscritos());
        dev1.progredir();
        dev1.progredir();
        dev1.progredir();
        System.out.println("Conteúdos concluídos: " + dev1.getConteudosConcluidos());
        System.out.println("Conteúdos restantes: " + dev1.getConteudosInscritos());
        System.out.println("XP: " + dev1.calcularTotalXP());

        System.out.println("\n--- " + dev2.getNome() + " ---");
        System.out.println("Conteúdos inscritos: " + dev2.getConteudosInscritos());
        dev2.progredir();
        System.out.println("Conteúdos concluídos: " + dev2.getConteudosConcluidos());
        System.out.println("Conteúdos restantes: " + dev2.getConteudosInscritos());
        System.out.println("XP: " + dev2.calcularTotalXP());

    }
}