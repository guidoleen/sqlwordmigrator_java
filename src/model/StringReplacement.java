package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReplacement 
{
	private static int ibegindef = 0;
	private static int tellr = 1; 
	private Matcher m;
	private Pattern p;
	public String replace(String inLine)
	{
		String strReplace = inLine;
		
		// To cursor replacement
		strReplace = this.cursorReplacement(strReplace, SqlWordConstants.BEGIN_DEF, "cursor c" + tellr + "_", " is");
		
		// Beginloop replacement
		strReplace = this.cursorReplacement(strReplace, SqlWordConstants.BEGIN_LOOP, SqlWordConstants.TAG_BEGIN + "for r1 in c00_", SqlWordConstants.TAG_END);
		
		// Remove End dev
		strReplace = this.defaultReplacement(strReplace, SqlWordConstants.END_DEF, "--");
		
		// Remove and replace endloop
		strReplace = this.defaultReplacement(strReplace, SqlWordConstants.END_LOOP, SqlWordConstants.TAG_END_LOOP + "\n");	
		
		// Remove & and replace to p_
		strReplace = this.defaultCharReplacement(strReplace, SqlWordConstants.C_PARAM_SIGN, SqlWordConstants.PARAM_PREFIX);
		
		// Remove [ to <%=r1.
		strReplace = this.defaultCharReplacement(strReplace, SqlWordConstants.C_BVAR_SIGN, SqlWordConstants.TAG_BEGIN_EXPRSN + "r1.");
		
		// Remove ] to %>
		strReplace = this.defaultCharReplacement(strReplace, SqlWordConstants.C_EVAR_SIGN, SqlWordConstants.TAG_END);
		
		return strReplace;
	}
	
	// Return a matcher
	private Matcher replaceThis(String _inLine, String _pattern)
	{
		this.p = Pattern.compile(_pattern);
		this.m = this.p.matcher( _inLine.toLowerCase() );
		
		return this.m;
	}
	
	// Default replacement
	private String defaultReplacement(String _inLine, String _pattern, String _replacement)
	{
		this.m = this.replaceThis(_inLine, _pattern);
		if(this.m.find())
		{
			return _replacement;
		}
		return _inLine;
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
	
	// Cursor replacement
	private String cursorReplacement(String _inLine, String _pattern, String _repl, String _endrepl)
	{
		String strRepl = "";
		this.m = this.replaceThis(_inLine, _pattern);
		if(this.m.find())
		{
			strRepl = this.m.replaceFirst(_repl);
			if( _pattern.equals(_pattern) )
			{
				strRepl = strRepl.replace(";", _endrepl);
				if(_pattern.contentEquals(SqlWordConstants.BEGIN_DEF))
				{
					if(this.ibegindef == 0)
						strRepl = SqlWordConstants.TAG_CURSOR_BEGIN + "\n" +
						SqlWordConstants.COMMENT + "\n" + strRepl;
					this.ibegindef++;
					tellr++;
				}
			}
			return strRepl;
		}
		return _inLine;
	}
}
