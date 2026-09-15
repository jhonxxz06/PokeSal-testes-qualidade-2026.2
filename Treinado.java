public class Treinado {
    public String nome;
    public int idade;
    private Pokesal pokesal;

    public Treinador(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.pokesal = null;
    }

    public boolean escolherPokesal(Pokesal escolhido) {
        if (this.pokesal != null) {
            System.out.println(this.nome + " já escolheu um Pokésal!");
            return false;
        }
        this.pokesal = escolhido;
        return true;
    }

    public Pokesal getPokesal() {
        return this.pokesal;
        }
    }
}