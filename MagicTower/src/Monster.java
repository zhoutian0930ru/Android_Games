public class Monster {
    public int number;   //编号
    public int attack;   //攻击力
    public int defence;  //防御力
    public int blood;    //血量
    public int money;    //金币
    public int exp;      //经验

    public String name;

    Monster(int num, int att, int def ,int hp, int money, int exp, String name){
        this.number = num;
        this.attack = att;
        this.defence = def;
        this.blood = hp;
        this.money = money;
        this.exp = exp;
        this.name = name;
    }


}
