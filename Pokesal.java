public class Pokesal {
    public int atk, def, hp, spd;
    public String nome;
    public TiposElemental tiposElemental;
    public Pokesal(int atk,int def,int hp,int spd, TiposElemental tiposElemental,String nome){
        this.atk = atk;
        this.def = def;
        this.hp = hp;
        this.spd = spd;
        this.tiposElemental = tiposElemental;
        this.nome = nome;
    }

}