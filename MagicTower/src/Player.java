public class Player {
    public int attack;   //攻击力
    public int defence;  //防御力
    public int blood;    //血量
    public int state;    //状态
    public int exp;      //经验
    public int money;    //金币

    public String name;

    Player(int att, int def ,int hp, String name){
        this.attack = att;
        this.defence = def;
        this.blood = hp;
        this.exp = 0;
        this.money = 0;
        this.name = name;
    }

    public void addAttack(int num){
        if(this.attack+num<0) this.attack = 0;
        else this.attack += num;
    }

    public void addDefence(int num){
        if(this.defence+num<0) this.defence = 0;
        else this.defence += num;
    }

    public void addBlood(int num){
        this.blood += num;
    }

    public void setState(int num){
        this.state = num;
    }

    public void setName(String name){
        this.name = name;
    }


}
