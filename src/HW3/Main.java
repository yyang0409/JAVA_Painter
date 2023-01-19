/*��ޤGA 109403502 ���\��*/
package HW3;

import javax.swing.* ;
import java.awt.*;
import java.awt.event.*;


public class Main {
	public static void main(String [] args) {
		new MyJFrame();
	}
}

class MyJFrame extends JFrame{
	private JPanel contentPane,northPnl,draw,contentPane_tool,contentPane_button,rbPnl;
	private JButton btnColor,btnClean,btnEraser;
	private JLabel state,tool,brush,full;
	private JComboBox <Object> jComboBox ;
	private JRadioButton rbS,rbM,rbL;
	private JCheckBox checkBox;
	private Graphics g ;
	private String drawtoolSel ="����",paintsizes="�p";
	private Point firstpoint; 
	private  int paintsize = 6;
	private JColorChooser JCC=new JColorChooser();
	private  Color forecolor = Color.BLACK;
	private Color erasercolor =Color.white;
	private Color drawcolor;
	private static boolean eraser=false;
	private Cursor cu = new Cursor(Cursor.HAND_CURSOR); /*���ƹ����*/
	
	

	
	
	MyJFrame(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		setBounds(420, 50, 750,700); 
		setTitle("�p�e�a");
		setCursor(cu);//�i�R���
		
		System.out.println("���b�ϥ� "+paintsizes+"����");
/*�������O*/
		contentPane = new JPanel();                         
		setContentPane(contentPane); 
		contentPane.setLayout(new BorderLayout());
/*�W���U�ƪ�*/
		northPnl = new JPanel();
		draw=new JPanel();
		state=new JLabel();
        contentPane.add(northPnl,BorderLayout.NORTH);
        contentPane.add(draw, BorderLayout.CENTER);
        contentPane.add(state, BorderLayout.SOUTH);
/*�W�b������*/
		contentPane_tool= new JPanel();
		contentPane_tool.setLayout(new GridLayout(2,3));
/*�W�b���k��*/
		contentPane_button= new JPanel();
/*�W�b���ƪO*/																					
		northPnl.add(contentPane_tool);
	    northPnl.add(contentPane_button);
 /*contenPane_tool�̭�*/
        /*�T�j�ﶵ�� label*/
		tool=new JLabel("ø�Ϥu�� ");
		contentPane_tool.add(tool);
		
		brush=new JLabel("����j�p");
		contentPane_tool.add(brush);
		
		full=new JLabel("��");
		contentPane_tool.add(full);
		/*ø�Ϥu��ﶵ*/
		jComboBox = new JComboBox<>();
		contentPane_tool.add(jComboBox);
		/*����j�p*/
		rbPnl = new JPanel();
        rbPnl.setLayout(new GridLayout(1,3,6,0));
        contentPane_tool.add(rbPnl);
        /*�񺡮خ�*/
        checkBox = new JCheckBox("");
        contentPane_tool.add( checkBox);
        
/*contentPane_button��*/
        btnColor = new JButton("�����C��");
		contentPane_button.add(btnColor);
		
		btnClean = new JButton("�M���e��");
		contentPane_button.add(btnClean);
		
		btnEraser = new JButton("�����");
		contentPane_button.add(btnEraser);
		
/*�e����*/
		draw.setBackground(Color.WHITE);	
		
/*���Ц�m*/
		draw.addMouseMotionListener (new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	state.setText((String.format("���Ц�m:[%d,%d]", e.getX(),e.getY())));
            }
        });
	/*�����洫���C��:) */
		state.setOpaque(true);
		state.setBackground(Color.BLACK);
		state.setForeground(Color.WHITE);
		
/*���ø�Ϥu��*/
        jComboBox.addItem("����");
        jComboBox.addItem("���u");
        jComboBox.addItem("����");
        jComboBox.addItem("�x��");
           
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	eraser=false;
               drawtoolSel = (String) jComboBox.getSelectedItem();
                switch (drawtoolSel) {
                    case "����":
                    	System.out.println("���b�ϥ� "+paintsizes+"����");
                    	checkBox.setEnabled(false);
                        break;
                    case "���u":
                        System.out.println("���b�ϥ� "+paintsizes+"���u");
                        checkBox.setEnabled(true);
                        break;
                    case "����":
                        System.out.println("���b�ϥ� "+paintsizes+"����");
                        checkBox.setEnabled(true);
                        break;
                    case "�x��":
                        System.out.println("���b�ϥ� "+paintsizes+"�x��");
                        checkBox.setEnabled(true);
                        break;
                }
               
            }
        });
/*��ܵ���j�p*/
        rbS = new JRadioButton("�p",true);
        rbPnl.add(rbS);
        rbS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rbS.isSelected()) {
            		System.out.println("���b�ϥ� �p����");
            		paintsize = 6;
            		paintsizes="�p";

            	}
            }
        });
        rbM = new JRadioButton("��");
        rbPnl.add(rbM);
        rbM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rbM.isSelected()) {
            		System.out.println("���b�ϥ� ������");
            		paintsize = 10;
            		paintsizes="��";

            	}
            }
        });
        rbL = new JRadioButton("�j");
        rbPnl.add(rbL);
        rbL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rbL.isSelected()) {
            		System.out.println("���b�ϥ� �j����");
            		paintsize = 15;
            		paintsizes="�j";

            	}
            }
        });

/*����j�p��ﭭ��*/
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rbS); 
        btnGroup.add(rbM);
        btnGroup.add(rbL);
/*�O�_��*/
        checkBox.setEnabled(false);
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 if (checkBox.isSelected()) {
            		 System.out.println("��� ��");
            	 }else {
            		 System.out.println("���� ��");
            	 }
            }
        });
/*������s*/
		btnColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("�I�� �����C��");
            	forecolor=JCC.showDialog(null,"JColorChooser",Color.BLACK);
            }
        });
/*�M���e�����s*/
		btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("�I�� �M���e��");
            	draw.repaint();
            }
        });
/*�����*/
		btnEraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("�{�b���b�ϥ� "+drawtoolSel+"�����");
            	eraser=true;
            }
        });
		
/*draw�����ƹ���ť */
	/*�ƹ��첾*//*����첾*/
		draw.addMouseMotionListener( new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				
				g =draw.getGraphics();
				if(drawtoolSel=="����"&&eraser==false) {
				
					g.setColor(forecolor);
					g.fillOval(e.getX(),e.getY() , paintsize , paintsize);
				
				}else if(drawtoolSel=="����"&&eraser!=false){
					g.setColor(erasercolor);
					g.fillOval(e.getX(), e.getY(), paintsize , paintsize);
					
				}
				
			}
		});
	
/*draw������ť ���ƹ�*/
		draw.addMouseListener(new MouseAdapter(){
			/*�I�����Ĥ@�U*//*�������*/
			public void mousePressed(MouseEvent e) {
					firstpoint = e.getPoint();
					g =draw.getGraphics();
					if(drawtoolSel=="����"&&eraser==false) {
						g.setColor(forecolor);
						g.fillOval(e.getX(), e.getY(), paintsize , paintsize);
					}else if(drawtoolSel=="����"&&eraser!=false){
						g.setColor(erasercolor);
						g.fillOval(e.getX(), e.getY(), paintsize , paintsize);
				}
			}
		    /*�I�����ĤG�U*/ 
			public void mouseReleased(MouseEvent e) {
				if(eraser==false) {
					drawcolor=forecolor;
				}else {
					drawcolor=erasercolor;
				}
					g =draw.getGraphics();
					Graphics2D g2 = (Graphics2D) g;
					switch(drawtoolSel) {
						case"���u":
							if(checkBox.isSelected()) {
								g2.setColor(drawcolor);
								g2.setStroke(new BasicStroke(paintsize));
								g2.drawLine(firstpoint.x,firstpoint.y,e.getX(),e.getY());
							}else {
								g2.setColor(drawcolor);
								g2.setStroke(new BasicStroke(paintsize, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
								g2.drawLine(firstpoint.x,firstpoint.y,e.getX(),e.getY());
							}
							break;
						case"����":
							if(checkBox.isSelected()) {
								g2.setColor(drawcolor);
								g2.setStroke(new BasicStroke(paintsize));
								g2.drawOval(Math.min(firstpoint.x,e.getX()),Math.min(firstpoint.y,e.getY()),Math.abs(firstpoint.x-e.getX()),Math.abs(firstpoint.y-e.getY()));
								g2.fillOval(Math.min(firstpoint.x,e.getX()),Math.min(firstpoint.y,e.getY()),Math.abs(firstpoint.x-e.getX()),Math.abs(firstpoint.y-e.getY()));
							}else {
								g2.setColor(drawcolor);
								g2.setStroke(new BasicStroke(paintsize));
								g2.drawOval(Math.min(firstpoint.x,e.getX()),Math.min(firstpoint.y,e.getY()),Math.abs(firstpoint.x-e.getX()),Math.abs(firstpoint.y-e.getY()));
							}
							break;
						case"�x��":
							if(checkBox.isSelected()) {
								g2.setColor(drawcolor);
								g2.setStroke(new BasicStroke(paintsize));
								g2.drawRect(firstpoint.x,firstpoint.y,Math.abs(firstpoint.x-e.getX()),Math.abs(firstpoint.y-e.getY()));
								g2.fillRect(firstpoint.x,firstpoint.y,Math.abs(firstpoint.x-e.getX()),Math.abs(firstpoint.y-e.getY()));
							}else {
								g2.setColor(drawcolor);
								g2.setStroke(new BasicStroke(paintsize));
								g2.drawRect(firstpoint.x,firstpoint.y,Math.abs(firstpoint.x-e.getX()),Math.abs(firstpoint.y-e.getY()));
							}
							break;
						}
			}
		});

		setVisible(true);
		
 /*�ﻫ�T��*/       
        ImageIcon icon= new ImageIcon("C:/Users/User/eclipse-workspace/HW2_109403502/src/HW2/java logo.jpg");
		JOptionPane.showMessageDialog(null,"Welcome","�T��",JOptionPane.INFORMATION_MESSAGE,icon);
		
					   

	 
	 } /*End MyJFrame���غc�l*/
}/*End MyJFrame.class*/

