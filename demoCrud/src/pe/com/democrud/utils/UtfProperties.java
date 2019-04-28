package pe.com.democrud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class UtfProperties extends ResourceBundle{

	protected static final String BUNDLE_NAME="System";
	protected static final String BUNDLE_EXTENSION="properties";
	protected static final Control UTF8_CONTROL=new Utf8Control();
	
	
	public UtfProperties() {
		// TODO Auto-generated constructor stub
		setParent(ResourceBundle.getBundle(BUNDLE_NAME,
		FacesContext.getCurrentInstance().getExternalContext().getRequestLocale(),UTF8_CONTROL));
	}
		
	
	@Override
	protected Object handleGetObject(String key) {
		// TODO Auto-generated method stub
		return parent.getObject(key);
	}

	@Override
	public Enumeration<String> getKeys() {
		// TODO Auto-generated method stub
		return parent.getKeys();
	}
	
	protected static class Utf8Control extends Control{
		public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException{
			String bundleName = toBundleName(baseName, locale);
			String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
			ResourceBundle bundle = null;
            InputStream stream = null;
            if(reload) {
            	URL url=loader.getResource(resourceName);
            	if(url !=null) {
            		URLConnection connection=url.openConnection();
            		if(connection !=null) {
            			connection.setUseCaches(false);
            			stream=connection.getInputStream();
            		}
            	}
            }else {
            	stream=loader.getResourceAsStream(resourceName);
            }
            
            if(stream !=null) {
            	try {
					bundle=new PropertyResourceBundle(new InputStreamReader(stream,"UTF-8"));
				} finally {
					// TODO: handle exception
					stream.close();
				}
            }
            return bundle;
			
		}
	}

}
