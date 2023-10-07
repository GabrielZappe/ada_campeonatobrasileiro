package ada.campeonatobrasileiro.domain;

public class Time {
    private final String nome;

    private final Estado estado;

    public Time (String nome, Estado estado) {
        this.nome = nome;
        this.estado = estado;

    }


    public String getNome() {
        return nome;
    }

    public Estado getEstado() {
        return estado;
    }

    @Override
    public String toString () {
        return "Nome time: " + nome + " " + estado;
    }



}
