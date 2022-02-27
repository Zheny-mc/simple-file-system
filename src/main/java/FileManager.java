import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

	public void createDirectory(String directoryName) {
		String fileName = this.currentFolder + "\\" + directoryName;
		Path path = Paths.get(fileName);

		if (!Files.exists(path)) {
			try {
				Files.createDirectory(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Directory already exists");
		}
	}

	public void deleteFile(String fileName) {
		//удаление с использованием полного пути в файлу
		File file = new File(this.currentFolder + "\\" + fileName);
		if(!file.delete()){
			System.out.println("Файл не найден!");
		}
	}

	public void renameFile(String oldFileName, String newFileName) {
		File file = new File(this.currentFolder + "\\" + oldFileName);
		File newFile = new File(this.currentFolder + "\\" + newFileName);
		if (!file.renameTo(newFile)) {
			System.out.println("Файл не был переименован");
		}
	}
}
