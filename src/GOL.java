import java.util.Random;

public class GOL {
    public static final int SIZE = 20;
    public static final int wNUM = 30;
    public static final int hNUM = 30;
    int width = SIZE*wNUM ;
    int height = SIZE*hNUM;


    private boolean [][]array = new boolean[hNUM][wNUM];
    GOL()
    {

    }
    public int getNeighborNum(int x,int y)
    {
        int num=0;
        for(int i=x-1;i<=x+1;x++)
        {
            for(int j=y-1;j<=y+1;j++)
            {
                if(i<0||i>wNUM||j<0||j>hNUM) continue;
                if(i==x&&j==y) continue;
                if(array[i][j]==true) num++;
            }
        }
        return num;
    }

    public void getNextState()
    {
        for(int i=0;i<wNUM;i++)
        {
            for(int j=0;j<hNUM;j++)
            {
                if(getNeighborNum(i,j)==3)
                {
                    array[i][j]=true;
                }
                else if(getNeighborNum(i,j)==2)
                {
                    continue;
                }
                else
                {
                    array[i][j]=false;
                }
            }
        }
    }
    public void getExample()
    {
        Random r = new Random();
        int num = r.nextInt(wNUM*hNUM/4);
        for(int i=0;i<num;i++){
            int x=r.nextInt(wNUM-1);
            int y=r.nextInt(hNUM-1);
            array[x][y]=!array[x][y];
        }
    }

    public void clear() {
        for(int i=0;i<wNUM;i++){
            for(int j=0;j<hNUM;j++)array[i][j]=false;
        }System.out.println("CLEAR!");
    }
}
