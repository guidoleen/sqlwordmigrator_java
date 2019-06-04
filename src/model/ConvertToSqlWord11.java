package model;

public class ConvertToSqlWord11 implements IConvertor
{
	private StringReplacement strrepl = new StringReplacement();

	@Override
	public String convertfromstring(String _str) 
	{
		return _str; // this.strrepl.replace(_str);
	}

	@Override
	public Character[] convertfromarray(Character[] _chr) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String convertToParameter(String _str) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
