import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileManager {
	private String root;
	private String currentFolder;

	public FileManager(String currentFolder) {
		this.root = currentFolder;
		this.currentFolder = currentFolder;
	}

	public void listOfFiles(boolean withSize) {
		File currentFolderAsFile = new File(currentFolder);
		//получили все файлы текущей папки
		File files[] = currentFolderAsFile.listFiles();
		//пробегаем по всем файлам текущей директории
		for (File file: files) {
			if (file.isDirectory()) {
				if (withSize) {
					System.out.print(file.getName() + "\\ " + FileUtils.sizeOfDirectory(file));
				} else {
					System.out.print(file.getName() + "\\ ");
				}
			} else {
				if (withSize) {
					System.out.print(file.getName() + " " + file.length());
				} else {
					System.out.print(file.getName() + " ");
				}
			}
			System.out.println();
		}
	}

	public void copyFile(String sourceFileName, String destFileName) {
		File source = new File(currentFolder + "\\" + sourceFileName);
		File dest = new File(currentFolder + "\\" + destFileName);
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			System.err.println("Произошла ошибка!");
		}
	}

	public void createFile(String fileName) {
		File file = new File(currentFolder + "\\" + fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.err.println("Что-то пошло не так!");
		}
	}

	public void changeDirectory(String folderName) {
		// cd / -> root
		// cd .. -> на уровень выше
		// root\\document\\sub
		if (folderName.equals("/")) {
			this.currentFolder = this.root;
		} else if (folderName.equals("..")) {
			int startLastFolderPosition = this.currentFolder.lastIndexOf("\\");
			this.currentFolder = this.currentFolder.substring(0, startLastFolderPosition);
		} else {
			this.currentFolder += String.format("\\%s", folderName);
		}
	}
}
