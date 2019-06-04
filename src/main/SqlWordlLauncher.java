package main;

import java.io.IOException;

import model.*;

public class SqlWordlLauncher {

	public static void main(String[] args) 
	{
		String strfIn = "input.txt";
		String strfOut = "output.txt";
		String dirName = "";
		// dirName = "C:\\Users\\guido_leen\\Desktop\\OmzetingJava\\"; // TESTING...

		if( args.length > 0 )
			dirName = args[0];
	
		ReadTxtFile rdr = new ReadTxtFile();
		WriteTxtFile fw = new WriteTxtFile();
		try 
		{
			
			// System.out.print( strOut );
			// String str =  rdr.getArrList(); // For parameters
			String strOutDefault = rdr.getTxtConvertedToStringLines(dirName + strfIn, new ConvertToSqlWord11() );
			String strOutParam = "";
			String strOutCursor = "";
			
			// Parameter conversion
			ParameterConvertor pcnv = new ParameterConvertor();
			pcnv.parameterConvert(rdr.getArrList());
			strOutParam = pcnv.getParameters();
			
			// Cursor conversion
			CursorConvertor cconv = new CursorConvertor();
			cconv.cursorConvert(rdr.getArrList());
			strOutCursor = cconv.getCursors();
			
			fw.writetoFile(strOutParam + strOutCursor, dirName + strfOut); // strOutDefault
			
			System.out.println(strOutParam); // For dev purpose only
			System.out.println(strOutCursor); // For dev purpose only
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
