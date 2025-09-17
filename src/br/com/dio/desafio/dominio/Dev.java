package br.com.dio.desafio.dominio;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Objects;

public class Dev {

    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public Dev(String nome, Set<Conteudo> conteudosInscritos, Set<Conteudo> conteudosConcluidos) {
        this.nome = nome;
        this.conteudosInscritos = conteudosInscritos;
        this.conteudosConcluidos = conteudosConcluidos;
    }

    public void inscreverBootcamp(Bootcamp bootcamp) {
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        }
        else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXP() {
        return this.conteudosConcluidos
                .stream()
                .mapToDouble(conteudo -> conteudo.calcularXp())
                .sum();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String nome;
        private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
        private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
        private Bootcamp bootcampParaInscrever;

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder conteudoInscrito(Bootcamp bootcamp) {
            this.conteudosInscritos.addAll(bootcamp.getConteudos());
            this.bootcampParaInscrever =  bootcamp;
            return this;
        }

        public Builder conteudoConcluido(Conteudo conteudo) {
            this.conteudosConcluidos.add(conteudo);
            return this;
        }

        public Dev build() {
            Dev dev = new Dev(nome, conteudosInscritos, conteudosConcluidos);
            if (bootcampParaInscrever != null)
                dev.inscreverBootcamp(bootcampParaInscrever);
            return dev;
        }

    }

}
