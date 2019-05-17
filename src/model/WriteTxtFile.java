package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTxtFile 
{
	public String writetoFile(String _txt, String _fname)
	{
		File f = new File(_fname);
		try 
		{
			FileWriter fw = new FileWriter(f);
			fw.write(_txt);
			fw.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			return e.toString();
		}
		return "Gelukt";
	}
}
