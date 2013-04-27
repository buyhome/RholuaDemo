package com.rholua;

import org.keplerproject.luajava.*;

public class Rholua {

	public static String processString(String str) {
        
		
		final String strlua = "a = 'campo a';" + 
	    "b = 'campo b';" +
	    "c = 'campo c';" +
	    "tab= { a='tab a'; b='tab b'; c='tab c', d={ e='tab d e'} } ;"+
	    "function imprime (str) print(str); return 'joao', 1  end;" + 
	    "luaPrint={implements='org.keplerproject.luajava.test.Printable', print=function(str)print('Printing from lua :'..str)end  }";
		
	    LuaState L = LuaStateFactory.newLuaState();
	    L.openLibs();
	    L.LdoString("text = 'Hello Android, I am Lua.'");
	    L.getGlobal("text");
	    String text = L.toString(-1);
		
		
		return "<Android>" + str + text;
		//return "<Android>" + str;
        
	}

	
}
