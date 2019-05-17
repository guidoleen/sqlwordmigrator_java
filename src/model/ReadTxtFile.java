package model;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadTxtFile 
{
	private ArrayList<String> arrList = new ArrayList();
	private static final String UTF_8 = "UTF-8";
	
	public ArrayList<String> getArrList() 
	{
		return arrList;
	}
	
	public String getTxtConverted(String FileName, IConvertor conv) throws IOException 
	{
		String strOut = "";
		File f = new File(FileName);

		Reader rdr = new BufferedReader(new InputStreamReader(new FileInputStream(f), UTF_8));
		  	
		    int ichr;
		    while ((ichr = rdr.read()) != -1) 
		    {
		    	strOut += (char) ichr;
		    }
		   rdr.close();

		   return conv.convertfromstring(strOut);
	}
	
	public Character[] getTxtConvertedToCharArray(String FileName, IConvertor conv) throws IOException 
	{
		File f = new File(FileName);
		Character[] chr = new Character[(int) f.length()];
		Reader rdr = new BufferedReader(new InputStreamReader(new FileInputStream(f), UTF_8));
		  	
			int i = 0;
		    int ichr;
		    while ((ichr = rdr.read()) != -1) 
		    {
		    	chr[i] = new Character( (char) ichr );
		    	i++;
		    }
		   rdr.close();

		   return conv.convertfromarray(chr);
	}
	
	public String getTxtConvertedToStringLines(String FileName, IConvertor conv) throws IOException 
	{
		String strChar = "";
		File f = new File(FileName);

		Reader rdr = new BufferedReader(new InputStreamReader(new FileInputStream(f), UTF_8));
		  	
		int iLine = 0;
		    int ichr;
		    while ((ichr = rdr.read()) != -1) 
		    {
		    	strChar += (char) ichr;
		    	if( (char) ichr == '\n' )
		    	{
		    		this.arrList.add( conv.convertfromstring(strChar) ); // Call convert and replace string
		    		strChar = "";
		    		iLine++;
		    	}
		    }
		   rdr.close();
		  System.out.println("Lines read " + iLine + ":" +  '\n');
		  
		  return readArrayListToString(arrList);
	}
	
	private String readArrayListToString(ArrayList<String> _arrL)
	{
		String strResult = "";
		for (int i = 0; i < _arrL.size(); i++) 
		{
			strResult += _arrL.get(i); // + "\n";
		}
		return strResult;
	}
}
