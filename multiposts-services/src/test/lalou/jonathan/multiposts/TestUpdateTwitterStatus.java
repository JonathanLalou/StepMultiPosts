package lalou.jonathan.multiposts;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * User: Jonathan LALOU
 * Date: 07/04/13
 * Time: 00:59
 */
public class TestUpdateTwitterStatus {
    private static final Logger LOGGER = Logger.getLogger(TestUpdateTwitterStatus.class);

    @Test
    public void testToto() {
        Assert.assertTrue(true);
    }

    @Test
    public void testUpdateStatus() {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            try {
                // get request token.
                // this will throw IllegalStateException if access token is already available
                RequestToken requestToken = twitter.getOAuthRequestToken();
                LOGGER.info("Got request token.");
                LOGGER.info("Request token: " + requestToken.getToken());
                LOGGER.info("Request token secret: " + requestToken.getTokenSecret());
                AccessToken accessToken = null;

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                while (null == accessToken) {
                    LOGGER.info("Open the following URL and grant access to your account:");
                    LOGGER.info(requestToken.getAuthorizationURL());
                    System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
                    String pin = br.readLine();
                    try {
                        if (pin.length() > 0) {
                            accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                        } else {
                            accessToken = twitter.getOAuthAccessToken(requestToken);
                        }
                    } catch (TwitterException te) {
                        if (401 == te.getStatusCode()) {
                            LOGGER.info("Unable to get the access token.");
                        } else {
                            te.printStackTrace();
                        }
                    }
                }
                LOGGER.info("Got access token.");
                LOGGER.info("Access token: " + accessToken.getToken());
                LOGGER.info("Access token secret: " + accessToken.getTokenSecret());
            } catch (IllegalStateException ie) {
                // access token is already available, or consumer key/secret is not set.
                if (!twitter.getAuthorization().isEnabled()) {
                    LOGGER.info("OAuth consumer key/secret is not set.");
                    System.exit(-1);
                }
            }
            Status status = twitter.updateStatus("test using Twitter4J / OAuth");
            LOGGER.info("Successfully updated the status to [" + status.getText() + "].");
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            LOGGER.info("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            LOGGER.info("Failed to read the system input.");
            System.exit(-1);
        }
    }

}
