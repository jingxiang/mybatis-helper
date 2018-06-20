package com.kalman.lang;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class FileChooserEvent {

	private final ActionEvent event;

	public FileChooserEvent(ActionEvent event) {
		this.event = event;
	}
	
	public String getFilePath(){
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		jfc.setFileFilter(new FileFilter() {
//			@Override
//			public String getDescription() {
//				return ".txt;.TXT";
//			}
//			@Override
//			public boolean accept(File file) {
//				String name = file.getName();
//				return file.isDirectory()
//						|| name.toLowerCase().endsWith(".txt"); // 仅显示目录和txt文件
//			}
//		});
		jfc.showDialog(new JLabel(), "选择");
		
		File file = jfc.getSelectedFile();
		if(file == null){
			return null;
		}
		if (!file.isDirectory()) {
			return null;
		}
		return file.getAbsolutePath();
	}

	public ActionEvent getEvent() {
		return event;
	}
	
	
}