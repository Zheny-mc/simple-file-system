import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scaner = new Scanner(System.in);

		FileManager fileManager =
				new FileManager("E:\\labs\\course_work_SPS\\simple_file_system\\root");

		System.out.println("welcome to the console");
		String input = scaner.nextLine();


		while(!input.equals(Commands.EXIT)) {
			String token[] = input.split(" ");
			String command = token[0];

			switch (command) {
				case Commands.LIST_OF_FILES:
					fileManager.listOfFiles(false);
					break;
				case Commands.LIST_OF_FILES_WITH_SIZE:
					fileManager.listOfFiles(true);
					break;
				case Commands.COPY_FILE:
					String sourceFileName = token[1];
					String destFileName = token[2];
					fileManager.copyFile(sourceFileName, destFileName);
					break;
				case Commands.CREATE_FILE:
					String fileName = token[1];
					fileManager.createFile(fileName);
					break;
				case Commands.CHANGE_DIRECTORY:
					String folderName = token[1];
					fileManager.changeDirectory(folderName);
					break;
			}
			//---------------ввод следующей команды----------------------
			input = scaner.nextLine();
		}

	}

}
