package com.rholua;

import com.rho.RhodesApp;
import com.rho.file.SimpleFile;
import com.rho.net.IHttpConnection;
import com.rho.net.RhoConnection;
import com.rho.net.URI;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.lcdui.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import com.rho.RhoClassFactory;
import com.rho.RhoEmptyLogger;
import com.rho.RhoLogger;
import com.rho.RhoRuby;

import com.xruby.runtime.builtin.ObjectFactory;
import com.xruby.runtime.builtin.RubyString;
import com.xruby.runtime.builtin.RubyFixnum;
import com.xruby.runtime.builtin.RubyInteger;
import com.xruby.runtime.lang.*;

public class Rholua implements Runnable {
	
	private static final RhoLogger LOG = RhoLogger.RHO_STRIP_LOG ? new RhoEmptyLogger() : 
		new RhoLogger("Rholua");

	public static RubyModule RholuaModule;    

	
	public String doProcessString( String str ) {
		return "<BB>" + str + "<BB>";
	}

	public int doCalcSumm( int x, int y ) {
		return (x+y);
	}

	


	public void run() {
		  LOG.INFO("$$$ Register Rholua Ruby class");
   	  	  // register Ruby Module
          RholuaModule = RubyAPI.defineModule("Rholua");        
          // register Ruby method
          RholuaModule.getSingletonClass().defineMethod("native_process_string", new RubyOneArgMethod() {
			protected RubyValue run(RubyValue receiver, RubyValue arg0, RubyBlock block) {
				if ( arg0 instanceof RubyString )
				{
					String str = arg0.toString();
					String result = doProcessString(str);
					return ObjectFactory.createString(result);
				}
				else {
					  throw new RubyException(RubyRuntime.ArgumentErrorClass, "in Rholua.native_process_string: wrong argument type.Should be String");
				}
			}
          });
          RholuaModule.getSingletonClass().defineMethod("calc_summ", new RubyTwoArgMethod() {
			protected RubyValue run(RubyValue receiver, RubyValue arg1, RubyValue arg2, RubyBlock block) {
				try {
					int x = 0;
					int y = 0;
					if (arg1 instanceof RubyFixnum) {
						RubyFixnum rv = (RubyFixnum)arg1;
						x = rv.toInt();
					}
					else {
						if (arg1 instanceof RubyInteger) {
							RubyInteger rv = (RubyInteger)arg1;
							x = rv.toInt();
						}
						else {
							if (arg1 instanceof RubySymbol) {
								RubySymbol rv = (RubySymbol)arg1;
								x = rv.toInt();
							}
							else {
								  throw new RubyException(RubyRuntime.ArgumentErrorClass, "in Rholua.calc_summ: wrong argument type.Should be Fixnum or Integer");
							}
						}
					}
					if (arg2 instanceof RubyFixnum) {
						RubyFixnum rv = (RubyFixnum)arg2;
						y = rv.toInt();
					}
					else {
						if (arg2 instanceof RubyInteger) {
							RubyInteger rv = (RubyInteger)arg2;
							y = rv.toInt();
						}
						else {
							if (arg2 instanceof RubySymbol) {
								RubySymbol rv = (RubySymbol)arg2;
								y = rv.toInt();
							}
							else {
								  throw new RubyException(RubyRuntime.ArgumentErrorClass, "in Rholua.calc_summ: wrong argument type.Should be Fixnum or Integer");
							}
						}
					}
					int res = doCalcSumm(x,y);
					return ObjectFactory.createInteger(res);
				} catch(Exception e) {
					e.printStackTrace();
					LOG.ERROR("Rholua.calc_summ failed with exception", e);
					throw (e instanceof RubyException ? (RubyException)e : new RubyException(e.getMessage()));
				}
			}
		});		
	}

}
