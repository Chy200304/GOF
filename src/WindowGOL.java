import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowGOL extends JFrame {
    private GOL gol=new GOL();
    private JButton SE=new JButton();
    private JButton Introduction=new JButton();
    private JButton bRandom=new JButton();
    private JButton Clear=new JButton();
    private JPanel jPanel=new JPanel();

    private GridLayout gridlayout = new GridLayout(GOL.wNUM, GOL.hNUM);

    WindowGOL(){
        //窗口属性设置
        this.setSize(GOL.SIZE*GOL.wNUM,GOL.SIZE*GOL.hNUM);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("生命游戏");
        this.setDefaultCloseOperation(3);
        //jPanel容器设置
        this.add(jPanel);
        jPanel.setVisible(true);
        //StartAndEnd按钮设置
        jPanel.add(SE);
        SE.setText("Start");
        SE.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SE.getText()==("Start"))SE.setText("End");
                else if(SE.getText()=="End")SE.setText("Start");
                SE.repaint();
            }
        });
        //介绍按钮设置
        jPanel.add(Introduction);
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
        jPanel.add(Clear);
        Clear.setText("Clear");
        Clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gol.clear();
            }
        });
        //随机生成按钮设置
        jPanel.add(bRandom);
        bRandom.setText("Random");
        //添加网格
        //jPanel.add()
    }
}
