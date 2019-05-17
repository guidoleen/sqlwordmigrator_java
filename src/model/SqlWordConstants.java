package model;

public class SqlWordConstants 
{
	// Standard values
	public static final String PARAM_PREFIX = "p_";
	
	// Pattern values
	public static final String BEGIN_LOOP = "\\bbeginloop:\\b";
	public static final String END_LOOP = "\\bendloop:\\b";
	public static final String BEGIN_DEF = "\\bbegindef:\\b";
	public static final String END_DEF = "\\benddef:\\b";
	public static final char C_PARAM_SIGN = '&';
	public static final char C_BVAR_SIGN = '[';
	public static final char C_EVAR_SIGN = ']';

	// Converted values
	public static final String TAG_END_LOOP = "<%end loop;%>";
	public static final String TAG_BEGIN_EXPRSN = "<%=";
	public static final String TAG_BEGIN_PARAM = "<%@ plsql parameter=";
	public static final String TAG_BEGIN = "<%";
	public static final String TAG_CURSOR_BEGIN = "<%!";
	public static final String TAG_END = "%>";
	public static final String COMMENT = "--";
	
	// Parameters
	public static final String BEGIN_PARAM = "\\bbeginparam:\\b";
	public static final String END_PARAM = "\\bendparam:\\b";
	public static final String PROMPT = "\\bprompt=\\b";
	public static final String DATATYPE = "\\bdatatype=\\b";
	public static final String LOV = "\\blov=\\b";
}
