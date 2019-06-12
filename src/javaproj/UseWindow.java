package javaproj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// CardLayout을 통해 트리에서 메소드가 선택된 경우 메소드가 사용하는 변수의 목록을, 
// 클래스와 변수가 선택된 경우 빈 패널을 보여주는 클래스
public class UseWindow extends JPanel {

	private CardLayout card = new CardLayout();
	private CardMethodUse use;
	private CardNull n;
	
	// UseWindow(JPanel) 생성자
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


// 트리에서 메소드 클릭 시 왼쪽 하단에 표시되는 카드 CardMethodUse
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


// 트리에서 메소드 클릭 시를 제외하고 표시되는 빈 카드 CardNull
class CardNull extends JPanel {
	
	private JLabel label = new JLabel("");
	
	public CardNull() {
		this.add(label);
	}
	
}