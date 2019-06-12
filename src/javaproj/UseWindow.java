package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// CardLayout�� ���� Ʈ������ �޼ҵ尡 ���õ� ��� �޼ҵ尡 ����ϴ� ������ �����, 
// Ŭ������ ������ ���õ� ��� �� �г��� �����ִ� Ŭ����
public class UseWindow extends JPanel {

	private CardLayout card = new CardLayout();
	private CardMethodUse use;
	private CardNull n;
	
	// UseWindow(JPanel) ������
	public UseWindow() {
		this.setLayout(card);
		n = new CardNull();
		add(n, "CardNull");
		card.show(this, "CardNull");
	}
	
	public void showCard(Object o) {
		if (o instanceof MethodInfo) {
			use = new CardMethodUse((MethodInfo)o);
			add(use, "CardMethodUse");
			card.show(this,  "CardMethodUse");
		}
		else {
			card.show(this, "CardNull");
		}
	}
	
}


// Ʈ������ �޼ҵ� Ŭ�� �� ���� �ϴܿ� ǥ�õǴ� ī�� CardMethodUse
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
		field.setFont(new Font("SansSerif", Font.PLAIN, 13));
		field.append(methodinfo.getUse());
		scroll = new JScrollPane(field);
		scroll.setPreferredSize(new Dimension(100,170));
		this.add(scroll, BorderLayout.SOUTH);
	}
	
}


// Ʈ������ �޼ҵ� Ŭ�� �ø� �����ϰ� ǥ�õǴ� �� ī�� CardNull
class CardNull extends JPanel {
	
	private JLabel label = new JLabel("");
	
	public CardNull() {
		this.add(label);
	}
	
}