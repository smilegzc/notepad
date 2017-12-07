import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;


public class NoteMenu {
	
	JFrame frame = null;
	JMenuBar menuBar = null;
	MenuListener menuListener = null;
	
	public NoteMenu(JFrame frame) {
		this.menuBar = new JMenuBar();
		frame.add(this.menuBar, BorderLayout.NORTH);
		this.frame = frame;
		menuListener = new MenuListener();
	}
	
	public JMenu addMenu(String menuTitle) {
		JMenu m = new JMenu(menuTitle);
		menuBar.add(m);
		return m;
		//m.addActionListener(menuListener);
	}
	
	public void addMenuItem(String menuItemTitle, JMenu menu) {
		JMenuItem menuItem = new JMenuItem(menuItemTitle);
		menuItem.addActionListener(menuListener);
		menu.add(menuItem);
	}
	
	//菜单按钮事件响应
	class MenuListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			String command = event.getActionCommand();
			JScrollPane sp = (JScrollPane) frame.getRootPane().getContentPane().getComponent(0);
			JTextArea ta = (JTextArea) sp.getViewport().getView();
			
			if(command.equals("Save")) {
				String path = null;
				if(new File(frame.getTitle()).isFile()) {
					path = frame.getTitle();
				} else {
					path = save();
				}
				saveOnPath(path, ta);
				
				messageDialog("保存成功");
	            
			} else if(command.equals("Open")) {
				
				String path = open();
				openOnPath(path, ta);
				frame.setTitle(path);
				
			} else if(command.equals("Help")) {
				messageDialog("点击相应按钮即可");
			} else if(command.equals("About")) {
				messageDialog("版权没有，盗版不究");
			}
		}
		
		private void messageDialog(String message) {
			JDialog dialog = new JDialog(frame, "系统消息", true); 
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            JLabel la = new JLabel(message);
            la.setFont(new Font("Dialog", 1, 20));
            la.setHorizontalAlignment(JLabel.CENTER);
            dialog.add(la);
            dialog.setSize(300,200);
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
		}

		public String save() {
			String filePath = null;
			JFileChooser saveChooser = new JFileChooser();
			int result = saveChooser.showSaveDialog(null);
			if(result == JFileChooser.APPROVE_OPTION) {
				filePath = saveChooser.getSelectedFile().getPath();
			}
			return filePath + ".txt";
		}
		
		public void saveOnPath(String path, JTextArea ta) {
			File file = new File(path);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(ta.getText());
				bw.flush();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			frame.setTitle(path);
		}
		
		public String open() {
			String filePath = null;
			
			JFileChooser openChooser = new JFileChooser();
			int result = openChooser.showOpenDialog(frame);
			if(result == JFileChooser.APPROVE_OPTION) {
				filePath = openChooser.getSelectedFile().getPath();
			}
			return filePath;

		}
		
		public void openOnPath(String path, JTextArea ta) {
			String buff = null;
			File file = new File(path);
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				ta.setText("");
				while((buff = br.readLine()) != null) {
					ta.append(buff+"\n");
				}
				ta.setCaretPosition(0);
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
