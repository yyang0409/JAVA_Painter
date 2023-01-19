/*資管二A 109403502 楊珮綾*/
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
	private String drawtoolSel ="筆刷",paintsizes="小";
	private Point firstpoint; 
	private  int paintsize = 6;
	private JColorChooser JCC=new JColorChooser();
	private  Color forecolor = Color.BLACK;
	private Color erasercolor =Color.white;
	private Color drawcolor;
	private static boolean eraser=false;
	private Cursor cu = new Cursor(Cursor.HAND_CURSOR); /*手手滑鼠游標*/
	
	

	
	
	MyJFrame(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		setBounds(420, 50, 750,700); 
		setTitle("小畫家");
		setCursor(cu);//可愛游標
		
		System.out.println("正在使用 "+paintsizes+"筆刷");
/*底部面板*/
		contentPane = new JPanel();                         
		setContentPane(contentPane); 
		contentPane.setLayout(new BorderLayout());
/*上中下排版*/
		northPnl = new JPanel();
		draw=new JPanel();
		state=new JLabel();
        contentPane.add(northPnl,BorderLayout.NORTH);
        contentPane.add(draw, BorderLayout.CENTER);
        contentPane.add(state, BorderLayout.SOUTH);
/*上半部左邊*/
		contentPane_tool= new JPanel();
		contentPane_tool.setLayout(new GridLayout(2,3));
/*上半部右邊*/
		contentPane_button= new JPanel();
/*上半部排板*/																					
		northPnl.add(contentPane_tool);
	    northPnl.add(contentPane_button);
 /*contenPane_tool裡面*/
        /*三大選項的 label*/
		tool=new JLabel("繪圖工具 ");
		contentPane_tool.add(tool);
		
		brush=new JLabel("筆刷大小");
		contentPane_tool.add(brush);
		
		full=new JLabel("填滿");
		contentPane_tool.add(full);
		/*繪圖工具選項*/
		jComboBox = new JComboBox<>();
		contentPane_tool.add(jComboBox);
		/*筆刷大小*/
		rbPnl = new JPanel();
        rbPnl.setLayout(new GridLayout(1,3,6,0));
        contentPane_tool.add(rbPnl);
        /*填滿框框*/
        checkBox = new JCheckBox("");
        contentPane_tool.add( checkBox);
        
/*contentPane_button裡*/
        btnColor = new JButton("筆刷顏色");
		contentPane_button.add(btnColor);
		
		btnClean = new JButton("清除畫面");
		contentPane_button.add(btnClean);
		
		btnEraser = new JButton("橡皮擦");
		contentPane_button.add(btnEraser);
		
/*畫布區*/
		draw.setBackground(Color.WHITE);	
		
/*指標位置*/
		draw.addMouseMotionListener (new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
            	state.setText((String.format("指標位置:[%d,%d]", e.getX(),e.getY())));
            }
        });
	/*指標欄換個顏色:) */
		state.setOpaque(true);
		state.setBackground(Color.BLACK);
		state.setForeground(Color.WHITE);
		
/*選擇繪圖工具*/
        jComboBox.addItem("筆刷");
        jComboBox.addItem("直線");
        jComboBox.addItem("橢圓形");
        jComboBox.addItem("矩形");
           
        jComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	eraser=false;
               drawtoolSel = (String) jComboBox.getSelectedItem();
                switch (drawtoolSel) {
                    case "筆刷":
                    	System.out.println("正在使用 "+paintsizes+"筆刷");
                    	checkBox.setEnabled(false);
                        break;
                    case "直線":
                        System.out.println("正在使用 "+paintsizes+"直線");
                        checkBox.setEnabled(true);
                        break;
                    case "橢圓形":
                        System.out.println("正在使用 "+paintsizes+"橢圓形");
                        checkBox.setEnabled(true);
                        break;
                    case "矩形":
                        System.out.println("正在使用 "+paintsizes+"矩形");
                        checkBox.setEnabled(true);
                        break;
                }
               
            }
        });
/*選擇筆刷大小*/
        rbS = new JRadioButton("小",true);
        rbPnl.add(rbS);
        rbS.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rbS.isSelected()) {
            		System.out.println("正在使用 小筆刷");
            		paintsize = 6;
            		paintsizes="小";

            	}
            }
        });
        rbM = new JRadioButton("中");
        rbPnl.add(rbM);
        rbM.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rbM.isSelected()) {
            		System.out.println("正在使用 中筆刷");
            		paintsize = 10;
            		paintsizes="中";

            	}
            }
        });
        rbL = new JRadioButton("大");
        rbPnl.add(rbL);
        rbL.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (rbL.isSelected()) {
            		System.out.println("正在使用 大筆刷");
            		paintsize = 15;
            		paintsizes="大";

            	}
            }
        });

/*筆刷大小單選限制*/
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rbS); 
        btnGroup.add(rbM);
        btnGroup.add(rbL);
/*是否填滿*/
        checkBox.setEnabled(false);
        checkBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 if (checkBox.isSelected()) {
            		 System.out.println("選擇 填滿");
            	 }else {
            		 System.out.println("取消 填滿");
            	 }
            }
        });
/*筆刷按鈕*/
		btnColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("點選 筆刷顏色");
            	forecolor=JCC.showDialog(null,"JColorChooser",Color.BLACK);
            }
        });
/*清除畫面按鈕*/
		btnClean.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("點選 清除畫面");
            	draw.repaint();
            }
        });
/*橡皮擦*/
		btnEraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("現在正在使用 "+drawtoolSel+"橡皮擦");
            	eraser=true;
            }
        });
		
/*draw版的滑鼠監聽 */
	/*滑鼠拖移*//*筆刷拖移*/
		draw.addMouseMotionListener( new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				
				g =draw.getGraphics();
				if(drawtoolSel=="筆刷"&&eraser==false) {
				
					g.setColor(forecolor);
					g.fillOval(e.getX(),e.getY() , paintsize , paintsize);
				
				}else if(drawtoolSel=="筆刷"&&eraser!=false){
					g.setColor(erasercolor);
					g.fillOval(e.getX(), e.getY(), paintsize , paintsize);
					
				}
				
			}
		});
	
/*draw版的監聽 按滑鼠*/
		draw.addMouseListener(new MouseAdapter(){
			/*點擊的第一下*//*筆刷單擊*/
			public void mousePressed(MouseEvent e) {
					firstpoint = e.getPoint();
					g =draw.getGraphics();
					if(drawtoolSel=="筆刷"&&eraser==false) {
						g.setColor(forecolor);
						g.fillOval(e.getX(), e.getY(), paintsize , paintsize);
					}else if(drawtoolSel=="筆刷"&&eraser!=false){
						g.setColor(erasercolor);
						g.fillOval(e.getX(), e.getY(), paintsize , paintsize);
				}
			}
		    /*點擊的第二下*/ 
			public void mouseReleased(MouseEvent e) {
				if(eraser==false) {
					drawcolor=forecolor;
				}else {
					drawcolor=erasercolor;
				}
					g =draw.getGraphics();
					Graphics2D g2 = (Graphics2D) g;
					switch(drawtoolSel) {
						case"直線":
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
						case"橢圓形":
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
						case"矩形":
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
		
 /*迎賓訊息*/       
        ImageIcon icon= new ImageIcon("C:/Users/User/eclipse-workspace/HW2_109403502/src/HW2/java logo.jpg");
		JOptionPane.showMessageDialog(null,"Welcome","訊息",JOptionPane.INFORMATION_MESSAGE,icon);
		
					   

	 
	 } /*End MyJFrame的建構子*/
}/*End MyJFrame.class*/

