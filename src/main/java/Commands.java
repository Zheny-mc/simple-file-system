
public interface Commands {
	String CREATE_DIRECTORY = "mkdir";
	String CHANGE_DIRECTORY = "cd";

	String LIST_OF_FILES = "ls";
	String LIST_OF_FILES_WITH_SIZE = "ll";

	String CREATE_FILE = "touch";
	String DELETE_FILE = "rm";
	String COPY_FILE = "cp";
	String RENAME_FILE = "mv";

	String INFO = "man";
	String ESTIMATION_DISK_SPACE_USED = "df";
	String EXIT = "exit";
}
