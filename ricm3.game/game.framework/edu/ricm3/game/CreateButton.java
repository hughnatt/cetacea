package edu.ricm3.game;



import java.awt.Dimension;


import javax.swing.*;



public class CreateButton extends JButton {

	private static final long serialVersionUID = 1L;
	String m_name;
	String m_icon;
	String m_icon_over;
	
	public CreateButton(String name, String icon, String icon_over) {
		
		super(name);
		this.m_name = name;
		this.m_icon = icon;
		this.m_icon_over = icon_over;
	}

	
	public void setButton() {
		
		setIcon(new ImageIcon(m_icon));
		setRolloverIcon(new ImageIcon(m_icon_over));
		setActionCommand(m_name);
		setOpaque(false);
		setContentAreaFilled(false);
		setVerticalTextPosition(JLabel.BOTTOM);
		setHorizontalTextPosition(JLabel.CENTER);
		setPreferredSize(new Dimension(300,300));
		setBorderPainted(false);
		
		
	}
	
	
}
