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
        //������������
        this.setSize(GOL.SIZE*GOL.wNUM,GOL.SIZE*GOL.hNUM);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("������Ϸ");
        this.setDefaultCloseOperation(3);
        //jPanel��������
        this.add(jPanel);
        jPanel.setVisible(true);
        //StartAndEnd��ť����
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
        //���ܰ�ť����
        jPanel.add(Introduction);
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
        jPanel.add(Clear);
        Clear.setText("Clear");
        Clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gol.clear();
            }
        });
        //������ɰ�ť����
        jPanel.add(bRandom);
        bRandom.setText("Random");
        //�������
        //jPanel.add()
    }
}
