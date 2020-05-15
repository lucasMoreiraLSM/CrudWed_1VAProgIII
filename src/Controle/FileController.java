package Controle;

import java.io.File;
import java.nio.file.*;

public class FileController {
	String fileName;
	String referencePath;
	String fileAsString;
	public FileController(String fileName, String referencePath) {
		this.fileName =  fileName;
		this.referencePath = referencePath;
	}
	public String getFileFromDisk () 
	{
		fileAsString = "";
		try {
			Path path = getPath();
			fileAsString = new String(Files.readAllBytes(path));
		} catch (Exception e) {
			System.out.println("some error to read this file");
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return fileAsString;
	}
	public Path getPath() throws Exception {
		String currentDirectory = this.referencePath;
		String fullPath = currentDirectory+File.separator+"templates"+File.separator+this.fileName;
		System.out.println(fullPath);
		Path path = Paths.get(fullPath);
		return path;
	}

}
