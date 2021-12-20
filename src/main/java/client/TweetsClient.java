package client;

import base.BaseTweeterClient;

import io.restassured.response.Response;

//following # classes need to be imported manually
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matcher.*;
import org.apache.http.HttpStatus;


public class TweetsClient extends BaseTweeterClient {
	//end points
	protected final static String STATUSES_USER_TIMELINE = "/statuses/home_timeline.json";
	protected final static String TWEET_ENDPOINT = "/statuses/update.json";
	protected final static String DISTROY_TWEET_PATH = "/statuses/destroy.json";
	
	public Response getUserTimeLine(){
		return given()
				  		.auth()
						.oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret )
			   .when()
						.get(this.baseURI+STATUSES_USER_TIMELINE);
		// following is for validation but we will validate on @ test class to make this method reusable 
//				.then()
//						.statusCode(200);		
	}
	public Response CreateTweet (String tweet) {
		return given()
					.auth()
					.oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret )
					.param("status", tweet)
				.when()
					.post(this.getfullURL(TWEET_ENDPOINT));				
		
	}
	public Response deleteTweet (Long tweetId) { 
		return given()
				.auth()
				.oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret )
				.queryParam("id", 	tweetId)
				.when()
				.post(this.getfullURL(DISTROY_TWEET_PATH ));
		
	}

}
