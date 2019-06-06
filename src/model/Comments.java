package model;

public class Comments 
{
	public String createSqlWordComment()
	{
		// https://codepen.io/sakri/pen/Iklgx
		return "//............................................................................\r\n" + 
				"//...SSSSSSS...............lllllWWW..WWWWW...WWWo........................ddd..\r\n" + 
				"//..SSSSSSSSS..............lllllWWW..WWWWW..WWWW.........................ddd..\r\n" + 
				"//..SSSSSSSSSS.............lllllWWW..WWWWWW.WWWW.........................ddd..\r\n" + 
				"//.SSSSS..SSSS...qqqqqqqqq.lllllWWW.WWWWWWW.WWWW..oooooo...rrrrrrrd.dddddddd..\r\n" + 
				"//.SSSSS........qqqqqqqqqq.lllllWWW.WWWWWWW.WWWW.ooooooooo.rrrrrrr.ddddddddd..\r\n" + 
				"//..SSSSSSS.....qqqq.qqqqq.llll.WWWWWWWWWWW.WWW.ooooo.oooo.rrrrr..ddddd.dddd..\r\n" + 
				"//...SSSSSSSSS.Sqqq...qqqq.llll.WWWWWWW.WWWWWWW.oooo...oooorrrr...dddd..dddd..\r\n" + 
				"//.....SSSSSSS.Sqqq...qqqq.llll.WWWWWWW.WWWWWWW.oooo...oooorrrr...dddd...ddd..\r\n" + 
				"//........SSSSSSqqq...qqqq.llll.WWWWWWW.WWWWWWW.oooo...oooorrrr...dddd...ddd..\r\n" + 
				"//.SSSS....SSSSSqqq...qqqq.llll.WWWWWWW.WWWWWWW.oooo...oooorrrr...dddd..dddd..\r\n" + 
				"//.SSSSSSSSSSSS.qqqq.qqqqq.llll..WWWWW...WWWWW..ooooo.oooo.rrrr...ddddd.dddd..\r\n" + 
				"//..SSSSSSSSSS..qqqqqqqqqq.llll..WWWWW...WWWWW...ooooooooo.rrrr....ddddddddd..\r\n" + 
				"//...SSSSSSSS....qqqqqqqqq.llll..WWWWW...WWWWW....oooooo...rrrr.....dddddddd..\r\n" + 
				"//....................qqqq....................................................\r\n" + 
				"//....................qqqq....................................................\r\n" + 
				"//....................qqqq....................................................\r\n" + 
				"//....................qqqq....................................................\r\n" + 
				"//............................................................................";
	}
	
	public String createInstructionComment()
	{
		int iL = 100;
		return this.commentCode() + this.singleDotsCommentLine(iL) + this.newLine() +
		 this.commentCode() + this.singleTextCommentLine("SqlWord2 naar SqlWord11 transpiler".toUpperCase(), iL) + this.newLine() +
		 this.commentCode() + this.singleTextCommentLine("Versie: 1.0".toUpperCase(), iL) + this.newLine() +
		 this.commentCode() + this.singleTextCommentLine("Copy/paste de code hieronder en test deze voordat deze code wordt gebruikt", iL) + this.newLine() +
		 this.commentCode() + this.singleDotsCommentLine(iL) + this.newLine() +
		 this.newLine();
	}
	
	private String singleDotsCommentLine(int len)
	{
		String strLine = "";
		for (int i = 0; i < len; i++) 
		{
			strLine += ".";
		}
		return strLine;
	}
	
	private String singleTextCommentLine(String strLine, int len)
	{
		int ifirstDots = (int) Math.round( (len - strLine.length())/2 );
		int iSecondDots = (len - strLine.length()) - ifirstDots;
		
		return this.singleDotsCommentLine(ifirstDots) + strLine + this.singleDotsCommentLine(iSecondDots);
	}
	
	private String commentCode()
	{
		return "// ";
	}
	
	private String newLine()
	{
		return "\r\n";
	}
}
