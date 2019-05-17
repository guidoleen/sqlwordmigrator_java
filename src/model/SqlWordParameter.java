package model;

public class SqlWordParameter 
{
	private String parameterName;
	private String prompt;
	private String dataType;
	private String lov;
	
	public String getParameterName() {
		return parameterName;
	}
	public String getPrompt() {
		return prompt;
	}
	public String getDataType() {
		return dataType;
	}
	public String getLov() {
		return lov;
	}
	
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public void setLov(String lov) {
		this.lov = lov;
	}
	
	//Constructors
	public SqlWordParameter(String _parameterName)
	{
		this.parameterName = _parameterName;
	}
	public SqlWordParameter(String _parameterName, String _prompt, String _dataType, String _lov)
	{
		this.parameterName = _parameterName;
		this.prompt = _prompt;
		this.dataType = _dataType;
		this.lov = _lov;
	}
	
	@Override
	public String toString()
	{
		String strParam = this.getNewParameter().replace("\r", "").replace("\n", "") + "\n";
		String strPrompt = this.prompt + "\n";
		String strLov = this.lov;
		return strParam + strPrompt + strLov;

	}
	
	// Just parameters together
	public String toStringParam()
	{
		return this.getNewParameter().replace("\r", "").replace("\n", "") + "\n";
	}
	
	// <%@ plsql parameter="p_reden" type="number"%>
	private String getNewParameter()
	{
		return SqlWordConstants.TAG_BEGIN_PARAM + "\"" + SqlWordConstants.PARAM_PREFIX + 
				this.parameterName + "\" type=\"" + this.dataType + "\"" +
				SqlWordConstants.TAG_END;
	}
}
