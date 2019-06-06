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

//		if( args.length > 0 )
//			dirName = args[0];
	
		ReadTxtFile rdr = new ReadTxtFile();
		WriteTxtFile fw = new WriteTxtFile();
		try 
		{
			
			// System.out.print( strOut );
			// String str =  rdr.getArrList(); // For parameters
			String strOutDefault = rdr.getTxtConvertedToStringLines(dirName + strfIn, new ConvertToSqlWord11() );
			String strOutParam = "";
			String strOutParamAttr = "";
			String strOutCursor = "";
			
			// Parameter conversion
			ParameterConvertor pcnv = new ParameterConvertor();
			pcnv.parameterConvert(rdr.getArrList());
			strOutParam = pcnv.getParameters();
			strOutParamAttr = pcnv.getParametersAttributes();
			
			// Cursor conversion
			CursorConvertor cconv = new CursorConvertor();
			cconv.cursorConvert(rdr.getArrList());
			strOutCursor = cconv.getCursors();
			
			// Comment before code generator
			String strComments = new Comments().createInstructionComment();
			
			fw.writetoFile(strOutParamAttr + strComments + strOutParam + strOutCursor, dirName + strfOut); // strOutDefault
			
			// System.out.println(strOutParam + strOutParamAttr); // For dev purpose only
			// System.out.println(strOutCursor); // For dev purpose only
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
