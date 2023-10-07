package ada.campeonatobrasileiro.domain;

public class Estado {


    private final String nome;

    public Estado(String nome) {
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }


    @Override

    public String toString () {
        return "Nome estado: " + nome;
    }
}
