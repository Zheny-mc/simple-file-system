import org.apache.commons.io.FileUtils;

import java.io.File;

public class Test {
	public static String root = "E:\\labs\\course_work_SPS\\simple_file_system\\root";

	public static void main(String[] args) {
		File file = new File(root);
		System.out.println("size = " + FileUtils.sizeOfDirectory(file));
	}
}
