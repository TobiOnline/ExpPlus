package org.tpc.expplus.debug;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class DebugWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7537273874443513338L;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public DebugWindow() {
		super("ExpPlus - Debug");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(scrollPane = new JScrollPane(textArea = new JTextArea()));
		
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		setSize(1024, 512);
		setLocationRelativeTo(null);
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				setVisible(true);
			}
		});
	}

	public void debug(Object text) {
		textArea.append(text.toString());
		textArea.append("\r\n");
		
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
