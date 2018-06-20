package com.kalman;


/**
 * @since 2018年6月10日
 * @author kalman03
 */
public class Application {

	public static void main(String[] args) {
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainFrame mainFrame = new MainFrame();
				mainFrame.setVisible(true);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setResizable(false);
			}
		});
	}
}
