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
