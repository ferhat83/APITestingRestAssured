package apiTests;

import java.util.UUID;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import client.TweetsClient;
import io.restassured.response.Response;

public class TweetsAPiTest {
	protected TweetsClient tweetsClient;
	protected long tweetID ;
	
	@BeforeClass
	public void setUp() {
		this.tweetsClient = new TweetsClient();
	}
	@Test
	public void testGetUserTimeLine() {
		Response response = this.tweetsClient.getUserTimeLine();
		// following commented line can be used for validation as well
		// then is used for verification 
		// following is for validation (HttpStatus.SC_OK) can be replaced by (200)
		response.then().statusCode(HttpStatus.SC_OK);// verify status code
		//following to get tweet id to use in delete test
		this.tweetID = response.path("id");	
		System.out.println("****** twetID: "+tweetID);
	}
	@Test (dependsOnMethods = {"testGetUserTimeLine"})
	public void userCanDeleteTweet() {
		Response response = this.tweetsClient.deleteTweet(this.tweetID);
		response.then().statusCode(HttpStatus.SC_OK);	
	}
	
	@Test 
	public void testUserCanTweet() {
		//+UUID.randomUUID() => java class to generate universal unique ID 
		//that can be added to a string to make it unique
		String tweet = " hello earth "+UUID.randomUUID();		
		Response response = this.tweetsClient.CreateTweet(tweet);	
		response.then().statusCode(HttpStatus.SC_OK);  
		
	}
	@Test
	public void testUserCannotDuplicateTweet() {
		String tweet = " hello earth "+UUID.randomUUID();
		Response response = this.tweetsClient.CreateTweet(tweet);	
		response.then().statusCode(HttpStatus.SC_OK);
		String tweet2 = " hello earth "+UUID.randomUUID();
	    response = this.tweetsClient.CreateTweet(tweet2);	
		response.then().statusCode(HttpStatus.SC_OK);
		response = this.tweetsClient.CreateTweet(tweet);	
		response.then().statusCode(HttpStatus.SC_FORBIDDEN);
		
	}
	@Test 
	public void testUserCannotTweetSameTweetInARow() {		
		String tweet =" test number "+UUID.randomUUID(); 
		Response response = this.tweetsClient.CreateTweet(tweet);
		System.out.println("******* "+tweet);
		response.then().statusCode(HttpStatus.SC_OK);
		//tweet same tweet 
		response = this.tweetsClient.CreateTweet(tweet);
		//tweet value will be same (generated ones and used twice)
		System.out.println("******* "+tweet);
		response.then().statusCode(HttpStatus.SC_FORBIDDEN);
			
	}

}
