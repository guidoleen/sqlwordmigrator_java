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
	
	public String getParameters() 
	{
		String strReturn = "";
		String strOnlyParam = "";
		for (int i = 0; i < this.parameters.size(); i++) 
		{
			strReturn += this.parameters.get(i).toString();
			strReturn += "\n";
			
			strOnlyParam += this.parameters.get(i).toStringParam();
			// strOnlyParam += "\n";
		}
		return strReturn + strOnlyParam;
	}
	
	public void parameterConvert(ArrayList<String> _arrL) // ArrayList lines of text from ReadTextFile
	{
		Matcher mBegin;
		Matcher mEnd;
		Matcher mPrompt;
		Matcher mDatatype;
		Matcher mLov;
		SqlWordParameter sqlParm = new SqlWordParameter("");
		
		String strLov = "";
		boolean isLov = false;
		
		for (int i = 0; i < _arrL.size(); i++) 
		{
			mBegin = this.getMatcher(_arrL.get(i), SqlWordConstants.BEGIN_PARAM);
			mEnd = this.getMatcher(_arrL.get(i), SqlWordConstants.END_PARAM);
			mPrompt = this.getMatcher(_arrL.get(i), SqlWordConstants.PROMPT);
			mDatatype = this.getMatcher(_arrL.get(i), SqlWordConstants.DATATYPE);
			mLov = this.getMatcher(_arrL.get(i), SqlWordConstants.LOV);
			
			if(mBegin.find())
			{
				sqlParm = new SqlWordParameter("");
				sqlParm.setParameterName(mBegin.replaceFirst("").replace(";", ""));
			}
			if(mPrompt.find())
			{
				sqlParm.setPrompt(mPrompt.replaceFirst("").replace(":", ""));
			}
			if(mDatatype.find())
			{
				sqlParm.setDataType(mDatatype.replaceFirst(""));
			}
			if(mLov.find())
			{
				strLov = mLov.replaceFirst("");
				isLov = true;
			}
			if(isLov)
			{
				strLov += _arrL.get(i);
			}
			if(mEnd.find())
			{
				sqlParm.setLov(strLov);
				isLov = false;
				strLov = "";
				this.parameters.add(sqlParm);
				i++;
			}
		}
	}
	
	// Return a matcher
		private Matcher getMatcher(String _inLine, String _pattern)
		{
			this.p = Pattern.compile(_pattern);
			this.m = this.p.matcher( _inLine.toLowerCase() );
			
			return this.m;
		}
}
