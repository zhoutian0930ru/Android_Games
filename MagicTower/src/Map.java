public class Map {
    public int x;
    public int y;
    public String name;
    public int[][] map;

    Map(int x,int y,String name){
        this.x = x;
        this.y = y;
        this.name = name;
        this.map = new int[x][y];
    }
    Map(int x,int y,String name,int[][] input){
        this.x = x;
        this.y = y;
        this.name = name;
        this.map = new int[x][y];
        if(input.length==x && input.length!=0 && input[0].length==y){
            for(int i=0;i<x;i++){
                for(int j=0;j<y;j++){
                    map[i][j] = input[i][j];
                }
            }
        }
    }

    public void reset(int x, int y, int num){
        map[x][y] = num;
    }
    public void rename(String name){
        this.name = name;
    }
    public int[] getEntrance(){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(map[i][j]==-2){
                    int[] entrance = new int[]{i,j};
                    return entrance;
                }
            }
        }
        int[] deEntrance = new int[]{0,0};
        return deEntrance;
    }
    public int[] getExit(){
        for(int i=0;i<x;i++){
            for(int j=0;j<y;j++){
                if(map[i][j]==-3){
                    int[] exit = new int[]{i,j};
                    return exit;
                }
            }
        }
        int[] deExit = new int[]{0,0};
        return deExit;
    }
}
