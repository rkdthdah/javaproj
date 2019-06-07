package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class UseWindow extends JPanel {

	private CardLayout card = new CardLayout();
	private CardMethodUse use;
	private CardNull nul;
	
	public UseWindow() {
		this.setLayout(card);
		
		nul = new CardNull();
		add(nul, "CardNull");
		card.show(this, "CardNull");
	}
	
	public void showCard(Object o) {
		if (o instanceof MethodInfo) {
			//System.out.println("MethodUse");
			use = new CardMethodUse((MethodInfo)o);
			add(use, "CardMethodUse");
			card.show(this,  "CardMethodUse");
		}
		else {
			nul = new CardNull();
			add(nul, "CardNull");
			card.show(this, "CardNull");
		}
	}

}

class CardMethodUse extends JPanel {
	private MethodInfo methodinfo;
	private JTextArea field;
	private JScrollPane scroll;
	private JLabel label = new JLabel(" Use");
	
	public CardMethodUse(MethodInfo m) {
		this.setLayout(new BorderLayout());
		
		this.add(label, BorderLayout.WEST);
		methodinfo = m;
		field = new JTextArea();
		field.append(methodinfo.getUse());
		scroll = new JScrollPane(field);
		//System.out.println(methodinfo.getUse());
		scroll.setPreferredSize(new Dimension(100,170));
		this.add(scroll, BorderLayout.SOUTH);
	}
}

class CardNull extends JPanel {
	private JLabel label = new JLabel("");
	//private JScrollPane scroll;
	
	public CardNull() {
		//scroll = new JScrollPane();
		//scroll.setPreferredSize(new Dimension(190,190));
		//this.add(scroll);
		this.add(label);
	}
}