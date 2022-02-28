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
		//проверка: существует ли файл
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
			//проверка на существование папки
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

	public void estimationDiskSpaceUsed() {
		File file = new File(this.root);
		var usageMemory = FileUtils.sizeOfDirectory(file); // использовано памяти
		var freeSpace = file.getFreeSpace(); // свободная память

		System.out.println("Total size(bytes) / Space free(bytes) =  " + usageMemory / freeSpace + "%");
		System.out.println(String.format("%17d / %11d", usageMemory, freeSpace));
	}

	public void info() {
		String infomation =
				"\t CREATE_DIRECTORY = \"mkdir\";\n" +
				"\t CHANGE_DIRECTORY = \"cd\";\n" +
				"\n" +
				"\t LIST_OF_FILES = \"ls\";\n" +
				"\t LIST_OF_FILES_WITH_SIZE = \"ll\";\n" +
				"\n" +
				"\t CREATE_FILE = \"touch\";\n" +
				"\t DELETE_FILE = \"rm\";\n" +
				"\t COPY_FILE = \"cp\";\n" +
				"\t RENAME_FILE = \"mv\";\n" +
				"\n" +
				"\t INFO = \"man\";\n" +
				"\t ESTIMATION_DISK_SPACE_USED = \"df\";\n" +
				"\t EXIT = \"exit\";";

		System.out.println(infomation);
	}
}
