import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;


public class WindowGOL extends JFrame {
    private GOL gol=new GOL();
    private JButton SE=new JButton();
    private JButton Introduction=new JButton();
    private JButton bRandom=new JButton();
    private JButton Clear=new JButton();
    private JPanel bottomPanel =new JPanel();

    private JPanel backPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel =new JPanel(new GridLayout(GOL.wNUM, GOL.hNUM));


    private GridLayout gridlayout = new GridLayout(GOL.wNUM, GOL.hNUM);
    private  JButton[][] TWorld;
    WindowGOL()
    {
        initWindowGOL();
    }

    public void initWindowGOL(){
        //窗口属性设置
        this.setSize(GOL.SIZE*GOL.wNUM,GOL.SIZE*GOL.hNUM);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("生命游戏");
        //this.setDefaultCloseOperation(3);
        //jPanel容器设置
        this.setContentPane(backPanel);
        backPanel.add(centerPanel,"Center");
        backPanel.add(bottomPanel,"South");
        //bottomPanel.setVisible(true);
        //this.pack();
        //jPanel.setLayout(null);
        int sizeLx,sizeLy;
        sizeLx=(GOL.wNUM)* GOL.SIZE;
        sizeLy=(GOL.hNUM)* GOL.SIZE;
        this.setSize(sizeLx,sizeLy);
        TWorld=new JButton[GOL.wNUM][GOL.hNUM];//二维按钮数组
        //添加网格
        class MyMouseListener extends MouseAdapter {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton buttonClicked = (JButton) e.getSource();
                buttonClicked.setBackground(Color.RED);
                //buttonClicked.
                //buttonClicked.getLocation().
                int x= (buttonClicked.getLocation().x-8)/ (GOL.SIZE-1);
                int y= (buttonClicked.getLocation().y-7)/ (GOL.SIZE-3);
                System.out.println(buttonClicked.getLocation().x);
                System.out.println(buttonClicked.getLocation().y);
                System.out.println(centerPanel.getLocation().x);
                System.out.println(centerPanel.getLocation().y);
                System.out.println(x+" "+y);
                gol.SetArray(y,x);
                showWorld();

            }
        }
        //jPanel.add()
        for (int i = 0; i < GOL.wNUM; i++) {
            for (int j = 0; j < GOL.hNUM; j++) {
                TWorld[i][j]=new JButton("");
                TWorld[i][j].setBackground(Color.white);
                TWorld[i][j].addMouseListener(new MyMouseListener());
                centerPanel.add(TWorld[i][j]);
            }
        }
        //StartAndEnd按钮设置
        bottomPanel.add(SE);

        SE.setText("Start");
        //SE.setBounds(600,600,10,10);
        SE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SE.getText()==("Start"))
                {
                    //SE.setText("End");
                    showWorld();
                    changeState();

                }
                else if(SE.getText()=="End")
                {
                    SE.setText("Start");
                }
                SE.repaint();
            }
        });
        //介绍按钮设置
        bottomPanel.add(Introduction);
        Introduction.setText("Introduction");
        //清除按钮设置
        bottomPanel.add(Clear);
        Clear.setText("Clear");
        Clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gol.clear();
                showWorld();
            }
        });
        //随机生成按钮设置
        bottomPanel.add(bRandom);
        bRandom.setText("Random");
        bRandom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gol.getExample();
                showWorld();
            }
        });


        //this.pack();



    }
    public void changeState()
    {
        //System.out.println("1111");
        gol.getNextState();
        //System.out.println("2222");
        showWorld();
    }
    //二维数组可视化显示
    public void showWorld(){
        for(int i=0;i<GOL.wNUM;i++){
            for(int j=0;j<GOL.hNUM;j++){
                if(gol.getArray(i,j)){
                    TWorld[i][j].setBackground(Color.red);
                }
                else{
                    TWorld[i][j].setBackground(Color.white);
                }
            }
        }
    }

}
