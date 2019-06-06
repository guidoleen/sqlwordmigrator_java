package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterConvertor 
{
	private Matcher m;
	private Pattern p;

	private List<SqlWordParameter> parameters = new ArrayList();
	
	// Parameters with attributes based on sql query 
	// Insert into SQLWORD_REPORT_PARAMETER (PROCEDURE_NAME,PARAMETER_NAME,PROMPT,DESCRIPTION,LOV) values ('VERG02','P_ADMIN_NR'      ,'Klant id'                    ,null,null);
	public String getParametersAttributes()
	{
		String strReturn = "";
		for (int i = 0; i < this.parameters.size(); i++) 
		{
			strReturn += "Insert into SQLWORD_REPORT_PARAMETER (PROCEDURE_NAME,PARAMETER_NAME,PROMPT,DESCRIPTION,LOV) values ('DOCNAME','";
			strReturn += SqlWordConstants.PARAM_PREFIX + this.parameters.get(i).getParameterName();
			strReturn += "', '";
			strReturn += this.parameters.get(i).getPrompt();
			strReturn += "', null, null);";
			strReturn += "\n";
		}
		return strReturn + "\n";
	}
	
	// The real Parameters
	public String getParameters()
	{
		String strOnlyParam = "";
		for (int i = 0; i < this.parameters.size(); i++) 
		{
			strOnlyParam += this.parameters.get(i).toStringParam();
		}
		return strOnlyParam;
	}
	
	public void parameterConvert(ArrayList<String> _arrL) // ArrayList lines of text from ReadTextFile
	{
		Matcher mBegin;
		Matcher mEnd;
		Matcher mPrompt;
		Matcher mDatatype;
		Matcher mLov;
		SqlWordParameter sqlParm = new SqlWordParameter("");
		
		boolean isLov = false;
		int iLov = 0; // Index the first lov line
		String strSetPrompt = "";
		String strDatatype = "";
		String strLov = "";

		for (int i = 0; i < _arrL.size(); i++) 
		{
			mBegin = this.getMatcher(_arrL.get(i), SqlWordConstants.BEGIN_PARAM);
			mEnd = this.getMatcher(_arrL.get(i), SqlWordConstants.END_PARAM);
			mPrompt = this.getMatcher(_arrL.get(i), SqlWordConstants.PROMPT);
			mDatatype = this.getMatcher( this.deleteAllWhiteSpaces(_arrL.get(i)), SqlWordConstants.DATATYPE );
			mLov = this.getMatcher(_arrL.get(i), SqlWordConstants.LOV);
						
			if(mBegin.find())
			{
				sqlParm = new SqlWordParameter("");
				sqlParm.setParameterName(mBegin.replaceFirst("").replace(";", ""));
			}
			if(mPrompt.find())
			{
				strSetPrompt = _arrL.get(i).toLowerCase();
				strSetPrompt = strSetPrompt.replaceAll(SqlWordConstants.PROMPT, "");
				strSetPrompt = strSetPrompt.substring(0, 1).toUpperCase() + strSetPrompt.substring(1);
				
				sqlParm.setPrompt( strSetPrompt.replace(":", "") ); // mPrompt.replaceFirst("").replace(":", ""));
			}
			if(mDatatype.find())
			{
				sqlParm.setDataType(mDatatype.replaceFirst(""));
			}
			if(mLov.find()) // First line of lov // Add return_value and display_value
			{
				strLov = this.strAddReturnValue( mLov.replaceFirst("") ); // First line of lov // Put the extra value
				iLov = 1; // Set the first line index
				isLov = true;
			}
			if(isLov) // Add the lines to the LOV param part > _arrL.get(i)
			{
				if(iLov == 1) // Ignore the first line
				{
					iLov = 0;
				}
				else
					strLov += _arrL.get(i);				
			}
			if(mEnd.find())
			{
				// Set Lov in the parm object
				sqlParm.setLov(strLov);
				
				// Init the flag vars
				isLov = false;
				strLov = "";
				iLov = 0;
				
				// Add to the parameters list
				this.parameters.add(sqlParm);
				// i++;
			}
		}
	}
	
	// Return a matcher without whitespaces
	private Matcher getMatcher(String _inLine, String _pattern)
	{
		this.p = Pattern.compile(_pattern);
		this.m = this.p.matcher( this.deleteWhiteSpaces( _inLine.toLowerCase() ) );
				
		return this.m;
	}
	
	// Delete whitespaces begin and end string > For the LOV's
	private String deleteWhiteSpaces(String _strIn)
	{
		int iTel = 0;
		String result = "";
		char[] cIn = _strIn.toCharArray();
		
		for (int i = 0; i < cIn.length; i++) 
		{
			if(cIn[i] == ' ' && iTel == 0)
			{
				i++;
			}
			iTel++;
			
			result += cIn[i];
		}
		return result;
	}
	
	// Delete all whitespaces > for the datatypes
	private String deleteAllWhiteSpaces(String _strIn)
	{
		String result = "";
		char[] cIn = _strIn.toCharArray();

		for (int i = 0; i < cIn.length; i++) 
		{
			if(cIn[i] == ' ')
			{
				i++;
			}

			result += cIn[i];
		}
		return result;
	}
	
	// Add Extra vals to the lov first line
	private String strAddReturnValue(String _input)
	{
		String output = "";
		int iIndx = 0;
		int iTelr = 0;
		char[] cinput = _input.toCharArray();
		for (int i = 0; i < cinput.length; i++) 
		{
			if(cinput[i] == ',' && iIndx == 0 ) // Only the first line // and first part
			{
				output += " return_value ,";
				iIndx = 1;
				i++;
			}
			if( iIndx == 1 ) // Second part after the line
			{
				if(cinput[i] == '\'')
				{
					iTelr++;
				}
				if(iTelr == 2)
				{
					output += "' display_value \n";
					iIndx++;
					i++;
					// break;
				}
			}
			output += cinput[i];
		}
		return output;
	}
	
	// Check if char is number
	private boolean checkIfNumber(String strIn)
	{
		try
		{
			Integer.parseInt(strIn);
		}
		catch(NumberFormatException ee)
		{
			return false;
		}
		
		return true;
	}
	
	// Change the first number with ''
//	private String changeWithSingleQuotes(strLine)
//	{
//		checkIfNumber(String strIn);
//	}
}
