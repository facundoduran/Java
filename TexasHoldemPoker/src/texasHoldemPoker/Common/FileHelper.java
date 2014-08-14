package texasHoldemPoker.Common;

import java.io.File;

public class FileHelper {
	
	private String filename;
	
	public FileHelper(String filename)
	{
		this.setFilename(filename);
	}
	
	public String getProjectPath()
	{
		String path = System.getProperty("user.dir");
		return path;
	}
	
	public boolean fileExists()
	{
		File file = new File(this.getFilename());
		return file.exists() ? true : false;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}
