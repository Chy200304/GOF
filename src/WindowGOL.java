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
        //������������
        this.setSize(GOL.SIZE*GOL.wNUM,GOL.SIZE*GOL.hNUM);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("������Ϸ");
        this.setDefaultCloseOperation(3);
        //jPanel��������
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
        TWorld=new JButton[GOL.wNUM][GOL.hNUM];//��ά��ť����
        //�������
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
        //StartAndEnd��ť����
        bottomPanel.add(SE);

        SE.setText("Start");
        //SE.setBounds(600,600,10,10);
        SE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SE.getText()==("Start"))
                {
                    SE.setText("End");
                    //���������̷߳���
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
        //���ܰ�ť����
        bottomPanel.add(Introduction);
        Introduction.setText("Introduction");
        Introduction.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String introductionMessage="<html>�����������zy2101���Լ����ͳº���������<br>" +
                        "����0����Ϸ�������ճ�ʼ�����ã���Ϸ�Զ��ݻ���<br>" +
                        "������Χ��������У�ÿһ�����ӿ����ǿո���ߴ���һ������/ϸ��/Cell��<br>" +
                        "ÿһ��������8�����ڵĸ��ӣ����ڵĸ����д�������������Ϊ���ھ�(neighbor)����<br>" +
                        "����������ʱ�����еĸ��Ӹ������ھ�����������������Cell���ִ�����Cell������<br>" +
                        "�����ݻ�����B3/S23<br>" +
                        "һ���������ǡ����2����3���ھӣ��������һ�����������򣬻���Ϊ�¶���ӵ����������<br>" +
                        "һ���ո����ǡ����3���ھӣ�����һ����������<html>";
                JDialog jDialog=new JDialog();
                jDialog.setTitle("����");
                JLabel jLabel=new JLabel(introductionMessage);
                jDialog.add(jLabel);
                jDialog.setSize(480,260);
                jDialog.setLocationRelativeTo(null);
                jDialog.setVisible(true);
            }
        });
        //�����ť����
        bottomPanel.add(Clear);
        Clear.setText("Clear");
        Clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gol.clear();
                showWorld();
            }
        });
        //������ɰ�ť����
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
    //��ά������ӻ���ʾ
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

        //����һ��Ĭ��ʱ��ֵ
        private static final int DEFULETIME = 1000;

        public timerWork() {
            this.lock = new Object();
            this.dealyTime = DEFULETIME;
            this.goon = false;
        }

        //�����������ӳ�ʱ��
        public timerWork setDealyTime(long dealyTime) {
            this.dealyTime = dealyTime;
            return this;
        }

        //����һ�����󷽷���������ʵ����������û�ʵ�־������ɹ���
        public void work() {
            showWorld();
            changeState();
        }

        //�����߳�
        public void startThread() {
            if (goon) {
                return;
            }
            goon = true;
            //���������߳�
            new Thread(new Action(), "����").start();
            //������ʱ�߳�
            new Thread(this, "��ʱ��").start();

        }

        //�����߳�
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
                        //�����߳�����������ʱ�䣬��λ����
                        lock.wait(dealyTime);
                        //���ѹ����߳�
                        lock.notify();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //����һ���ڲ��࣬��ɾ���Ĺ���
        class Action implements Runnable {

            @Override
            public void run() {
                try {
                    while (goon) {
                        synchronized (lock) {
                            //���������߳�
                            lock.wait();
                        }
                        //���ó���
                        work();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}
