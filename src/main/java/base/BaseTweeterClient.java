package base;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BaseTweeterClient {
	protected String apiKey;
	protected String apiSecretKey;
	protected String accessToken;
	protected String accessTokenSecret;
	//following is object imported from java.util to read from properties file
	private Properties prop = new Properties();
	//following is object imported from java.io to read from properties file
	private InputStream inputStream = null; // will be initiated using constructor
	protected String baseURI;
	
	public BaseTweeterClient() {
		
		this.baseURI = "https://api.twitter.com/1.1";
		try {
			this.inputStream = new FileInputStream("C:\\Users\\saadi\\eclipse-workspace\\seleniumAutomation\\restAssuredDemoOnTwitter\\authentification\\auth.properties");
			this.prop.load(inputStream);// load properties file
			//set the keys(should be exact same as declared in properties file)
			this.apiKey=this.prop.getProperty("apiKey");
			this.apiSecretKey=this.prop.getProperty("apiSecretKey");
			this.accessToken=this.prop.getProperty("accessToken");
			this.accessTokenSecret=this.prop.getProperty("accessTokenSecret");
		}catch(IOException e) {
			e.printStackTrace();
		}finally	{// close properties file
			if(this.inputStream != null) {
				try {
					this.inputStream.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
	public String getfullURL(String endpoint) {
		return this.baseURI + endpoint;
		
	}

}
