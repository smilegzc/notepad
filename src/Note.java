import java.awt.*;
import javax.swing.*;

public class Note {
	static final int HEIGHT = 600;
	static final int WIDTH = 900;
	
	public static void main(String[] args) {
		String fileName = "Untitled.txt";
		Note notePad = new Note();
		notePad.launch(fileName);
	}
	
	private void launch(String title) {
		JFrame noteFrame = new NoteFrame(title, HEIGHT, WIDTH);
		noteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add edit panel
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		noteFrame.add(scrollPane, BorderLayout.CENTER);
		
		//Add menu
		NoteMenu noteMenu = new NoteMenu(noteFrame);
		JMenu fileMenu = noteMenu.addMenu("File");
		noteMenu.addMenuItem("Open", fileMenu);
		noteMenu.addMenuItem("Save", fileMenu);
		JMenu helpMenu = noteMenu.addMenu("Help");
		noteMenu.addMenuItem("Help", helpMenu);
		noteMenu.addMenuItem("About", helpMenu);
		
		noteFrame.setVisible(true);
	}
}
//基础界面框架
class NoteFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//构造函数。对界面进行初始化
	public NoteFrame(String title, int height, int width) {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension size = kit.getScreenSize();
		
		setTitle(title);
		setSize(width, height);
		setLocation((size.width - width)/2, (size.height - height)/2);
	}
}