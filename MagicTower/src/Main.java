import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Player p1;             //Player(int att, int def ,int hp, String name)
    private static Map[] maps;            //Map(int x,int y,String name)       Map(int x,int y,String name,int[][] input)
    private static Monster[] monsters;    //Monster(int num, int att, int def ,int hp, String name)
    private static int[] position;        //{No.map,x,y};
    private static int k;

    public static void main(String[] args){
        initial();
        printMap(maps[0]);
        Scanner in = new Scanner(System.in);
        String move = in.nextLine();
        while(!move.equals("0")){
            move = move.trim();
            moveTo(move);

            printState();
            printMap(maps[position[0]]);

            //游戏是否能继续
            if(p1.blood<=0){
                System.out.println("GAME OVER");
                move = "0";
            }else{
                move = in.nextLine();
            }
        }
    }

    public static void moveTo(String str){
        if(str.equals("w")){
            if(position[1]!=0 && maps[position[0]].map[position[1]-1][position[2]]!=-1){
                position[1]--;
                execute(position[0],position[1],position[2]);
            }
        }else if(str.equals("a")){
            if(position[2]!=0 && maps[position[0]].map[position[1]][position[2]-1]!=-1){
                position[2]--;
                execute(position[0],position[1],position[2]);
            }
        }else if(str.equals("s")){
            if(position[1]!=maps[position[0]].x-1 && maps[position[0]].map[position[1]+1][position[2]]!=-1){
                position[1]++;
                execute(position[0],position[1],position[2]);
            }
        }else if(str.equals("d")){
            if(position[2]!=maps[position[0]].y-1 && maps[position[0]].map[position[1]][position[2]+1]!=-1){
                position[2]++;
                execute(position[0],position[1],position[2]);
            }
        }
    }

    public static void execute(int no,int x,int y){
        int temp = maps[no].map[x][y];
        if(temp>0 && temp<k){
            fight(temp);
            maps[no].map[x][y] = 0;
        }else if(temp == -3 && position[0]<maps.length-1){
            position[0]++;
            int[] entrance = maps[position[0]].getEntrance();
            position[1] = entrance[0];
            position[2] = entrance[1];
        }else if(temp == -2 && position[0]>0){
            position[0]--;
            int[] exit = maps[position[0]].getExit();
            position[1] = exit[0];
            position[2] = exit[1];
        }else{
            int len = temp-k;
            mystic(len);
        }
    }

    public static void fight(int temp){
        Monster enemy = monsters[temp-1];
        if(p1.attack<=enemy.defence){
            p1.blood=-1;
        }else{
            p1.exp+= enemy.exp;
            p1.money+=enemy.money;

            int time = enemy.blood/(p1.attack-enemy.defence);
            if(enemy.blood%(p1.attack-enemy.defence)!=0) time++;
            p1.blood-= time * ((enemy.attack-p1.defence<=0)?0:(enemy.attack-p1.defence));
        }
    }

    public static void initial(){
        int k=100;
        int MonsterNum = 6;
        int MapNum = 3;
        p1 = new Player(6,1,200,"Player1");
        maps = new Map[MapNum];
        monsters = new Monster[MonsterNum];

        //初始化怪物种类
        monsters[0] = new Monster(1,1,0,5,1,0,"1");
        monsters[1] = new Monster(2,1,1,10,2,0,"2");
        monsters[2] = new Monster(3,2,0,20,3,0,"3");
        monsters[3] = new Monster(4,5,5,10,4,0,"4");
        monsters[4] = new Monster(5,10,0,10,5,0,"5");
        monsters[5] = new Monster(6,20,3,200,10,10,"6");

        //map 1
        int[][] map1 = new int[][]{
                {-2, 0,-1, 3,-1},
                {-1, 0,-1, 2,-1},
                {-1, 1,-1, 1,-1},
                {-1, 0, 0, 0,-1},
                {-1,-1,-1, 0,-3},
        };
        maps[0] = new Map(map1.length,map1[0].length,"level 1",map1);

        //map 2
        int[][] map2 = new int[][]{
                {-2,-1, 5, 1,-1,-3},
                { 0,-1, 4, 1,-1, 0},
                { 0,-1,-1, 3,-1, 0},
                { 0, 0, 0, 0, 0, 0},
                { 0,-1, 0, 0,-1, 0},
                { 0,-1, 1, 0,-1, 0}
        };
        maps[1] = new Map(map2.length,map2[0].length,"level 2",map2);

        //map 3
        int[][] map3 = new int[][]{
                {-2,-1, 0, 0, 2, 3, 2, 2},
                { 0,-1, 0, 0,-1,-1, 2, 1},
                { 0,-1,-1, 0, 0,-1, 2, 1},
                { 0, 0, 0, 0, 0,-1,-1,-1},
                { 2,-1, 5, 0, 0, 0, 4, 0},
                { 3,-1, 4, 5,-1, 0,-1, 2},
                { 4,-1, 1, 1,-1, 0,-1, 0},
                { 0,-1, 1, 1,-1, 0,-1,-3},
        };
        maps[2] = new Map(map3.length,map3[0].length,"level 3",map3);

        //初始化人物位置
        position = new int[3];
        int[] entrance = maps[0].getEntrance();
        position[1] = entrance[0];
        position[2] = entrance[1];
    }

    public static void printMap(Map a){
        System.out.println(a.name);
        System.out.println("--------------------------");
        for(int i=0;i<a.x;i++){
            StringBuffer temp = new StringBuffer();
            for(int j=0;j<a.y;j++){
                if(i==position[1] && j==position[2]) temp.append("X ");
                else if(a.map[i][j]<=0){
                    switch(a.map[i][j]){
                        case -2:
                            temp.append("< ");
                            break;
                        case -3:
                            temp.append("> ");
                            break;
                        case -1:
                            temp.append("# ");
                            break;
                        case 0:
                            temp.append("  ");
                            break;
                    }
                }else{
                    if(a.map[i][j]<10){
                        temp.append(a.map[i][j]);
                        temp.append(" ");
                    }else{
                        temp.append(a.map[i][j]);
                    }
                }
            }
            temp.append("|");
            System.out.println(temp);
        }
        //System.out.println("--------------------------");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
    }

    public static void printState(){
        System.out.println(p1.name + ": ");
        System.out.println("att:" + p1.attack
                +"  def:"+p1.defence
                +"  hp:"+p1.blood
                +"  money:"+p1.money
                +"  exp:"+p1.exp);
    }

    public static void mystic(int n){
        //100-200
        switch (n-k){
            case 0:
                p1.attack++;
            case 1:
                p1.defence++;
            case 2:
                p1.blood+=100;
            case 3:
                p1.money+=10;
        }
    }

}
