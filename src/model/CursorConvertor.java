package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CursorConvertor 
{
	private Matcher m;
	private Pattern p;
	private static int tellr = 1;

	private List<SqlWordCursor> cursors = new ArrayList();
	
	public String getCursors() 
	{
		String strReturn = SqlWordConstants.TAG_CURSOR_BEGIN + "\n";
		
		for (int i = 0; i < this.cursors.size(); i++) 
		{
			strReturn += SqlWordConstants.COMMENT + "\n";
				strReturn += this.cursors.get(i).getStrCursor();
			strReturn += SqlWordConstants.COMMENT + "\n";
		}
		return strReturn + SqlWordConstants.TAG_END;
	}
	
	// CONVERT the cursor
	public void cursorConvert(ArrayList<String> _arrL) // ArrayList lines of text from ReadTextFile
	{
		String strCursor = "";
		boolean isCursor = false;
		int begindef = 0;
		SqlWordCursor curs = new SqlWordCursor();
		
		for (int i = 0; i < _arrL.size(); i++) 
		{
			Matcher mBeginDef = this.getMatcher(_arrL.get(i), SqlWordConstants.BEGIN_DEF);
			Matcher mEndDef = this.getMatcher(_arrL.get(i), SqlWordConstants.END_DEF);
			
			if(mBeginDef.find())
			{
				curs = new SqlWordCursor();
				isCursor = true;
				begindef = 1; 
				
				// First line cursor eg. c1_name is
				strCursor += this.cursorReplacement(_arrL.get(i), SqlWordConstants.BEGIN_DEF, "cursor c" + tellr + "_", " is");
			}
			if(begindef == 1) // Overslaan first line
				i++;
			
			if(isCursor)
			{
				begindef++;
				
				if(mEndDef.find()) // End of line enddef
				{
					strCursor += this.defaultReplacement(_arrL.get(i), SqlWordConstants.END_DEF, "");
					curs.setStrCursor(strCursor);
					this.cursors.add(curs);
					
					isCursor = false;
					tellr++;
					begindef = 0;
					strCursor = "";
				}
				else // Remove & and replace to p_
					strCursor += this.defaultCharReplacement(_arrL.get(i), SqlWordConstants.C_PARAM_SIGN, SqlWordConstants.PARAM_PREFIX);
			}
		}
	}
	
	// Cursor replacement first line
	private String cursorReplacement(String _inLine, String _pattern, String _repl, String _endrepl)
	{
		String strRepl = "";
		this.m = this.getMatcher(_inLine, _pattern);
		if(this.m.find())
		{
			strRepl = this.m.replaceFirst(_repl);
			if( _pattern.equals(_pattern) )
			{
				strRepl = strRepl.replace(";", _endrepl);
			}
			return strRepl;
		}
		return _inLine;
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
	
	// Default Character replacement
	private String defaultCharReplacement(String _inline, char _charctr, String _replacement)
	{
		String strReturn = "";
		char[] cinline = _inline.toCharArray(); // new char[_inline.length() + (_replacement.length() -1 )];
		char[] repl = _replacement.toCharArray();

		for (int i = 0; i < cinline.length; i++) 
		{
			if(cinline[i] == _charctr)
			{
				for (int j = 0; j < repl.length; j++) 
				{
					strReturn += repl[j];
				}
				i++;
			}
			strReturn += cinline[i];
		}
		return strReturn;
	}
	
	// Default replacement
	private String defaultReplacement(String _inLine, String _pattern, String _replacement)
	{
		this.m = this.getMatcher(_inLine, _pattern);
		if(this.m.find())
		{
			return _replacement;
		}
		return _inLine;
	}
}
