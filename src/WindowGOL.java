import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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

    private timerWork k=new timerWork();
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
        this.setDefaultCloseOperation(3);
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
                /*System.out.println(buttonClicked.getLocation().x);
                System.out.println(buttonClicked.getLocation().y);
                System.out.println(centerPanel.getLocation().x);
                System.out.println(centerPanel.getLocation().y);*/
                //System.out.println(x+" "+y);
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
                    SE.setText("End");
                    //调用启动线程方法
                    k.startThread();
                }
                else if(SE.getText()=="End")
                {
                    k.stop();
                    System.out.println("STOP!");
                    SE.setText("Start");
                }
                SE.repaint();
            }
        });
        //介绍按钮设置
        bottomPanel.add(Introduction);
        Introduction.setText("Introduction");
        Introduction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String introductionMessage="<html>本程序由软件zy2101班赵佳鹏和陈宏扬设计完成<br>" +
                        "这是0人游戏，即按照初始的设置，游戏自动演化。<br>" +
                        "在类似围棋的棋盘中，每一个格子可以是空格或者存在一个生命/细胞/Cell；<br>" +
                        "每一个格子有8个相邻的格子，相邻的格子中存活的生命数量称为其邻居(neighbor)数。<br>" +
                        "在世代交替时，所有的格子根据其邻居数，诞生新生命、Cell保持存活或者Cell死亡。<br>" +
                        "生命演化规则：B3/S23<br>" +
                        "一个生命如果恰好有2个或3个邻居，它会存活到下一个世代；否则，会因为孤独或拥挤而死亡。<br>" +
                        "一个空格，如果恰好有3个邻居，则诞生一个新生命。<html>";
                JDialog jDialog=new JDialog();
                jDialog.setTitle("介绍");
                JLabel jLabel=new JLabel(introductionMessage);
                jDialog.add(jLabel);
                jDialog.setSize(480,260);
                jDialog.setLocationRelativeTo(null);
                jDialog.setVisible(true);
            }
        });
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
    public  class timerWork implements Runnable {
        private Object lock;
        private volatile boolean goon;
        private long dealyTime;

        //给定一个默认时间值
        private static final int DEFULETIME = 1000;

        public timerWork() {
            this.lock = new Object();
            this.dealyTime = DEFULETIME;
            this.goon = false;
        }

        //由外面设置延迟时间
        public timerWork setDealyTime(long dealyTime) {
            this.dealyTime = dealyTime;
            return this;
        }

        //给定一个抽象方法，有外面实例化该类的用户实现具体的完成工作
        public void work() {
            showWorld();
            changeState();
        }

        //启动线程
        public void startThread() {
            if (goon) {
                return;
            }
            goon = true;
            //启动工作线程
            new Thread(new Action(), "工作").start();
            //启动定时线程
            new Thread(this, "计时器").start();

        }

        //结束线程
        public void stop() {
            if (!goon) {
                return;
            }
            goon = false;
        }

        @Override
        public void run() {
            try {
                while (goon) {
                    synchronized (lock) {
                        //将该线程阻塞给定的时间，单位毫秒
                        lock.wait(dealyTime);
                        //唤醒工作线程
                        lock.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //定义一个内部类，完成具体的工作
        class Action implements Runnable {

            @Override
            public void run() {
                try {
                    while (goon) {
                        synchronized (lock) {
                            //阻塞工作线程
                            lock.wait();
                        }
                        //调用抽象
                        work();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}
