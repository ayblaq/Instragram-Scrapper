import Util.RequestUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;


public class RequestUtilTest {
    @Before
    public void setup() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void makeHttpRequest_NullParam_NullPointerExceptionExpected() throws IOException {
        RequestUtil.makeHttpRequest(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeHttpRequest_EmptyStringParam_NullPointerExceptionExpected() throws IOException {
        RequestUtil.makeHttpRequest(" ");
    }

    @Test(expected = Exception.class)
    public void makeHttpRequest_InvalidInstagramMediaUrl_ExceptionExpected() throws IOException {
        String url = "instagram.com/smena8m/media/?max_id=";
        String jsonStr = RequestUtil.makeHttpRequest(url);
    }

    @Test
    public void makeHttpRequest_NonJsonUrl_StringResultExpected() throws IOException {
        String url = "https://www.google.com/";
        String jsonStr = RequestUtil.makeHttpRequest(url);
        Assert.assertNotNull(jsonStr);
        Assert.assertTrue(jsonStr.length() > 0);
    }

    @Test
    public void makeHttpRequest_ValidInstagramMediaUrl_StringMediaResultExpected() throws IOException {
        String url = "https://www.instagram.com/smena8m/media/?max_id=";
        String jsonStr = RequestUtil.makeHttpRequest(url);
        Assert.assertNotNull(jsonStr);
        Assert.assertTrue(jsonStr.length() > 0);
    }


    @Test(expected = IllegalArgumentException.class)
    public void downloadPost_NullParam_NullPointerExceptionExpected() throws IOException {
        RequestUtil.downloadPost(null,  null);
    }

    @Test(expected = Exception.class)
    public void downloadPost__NullParam_NullPointerExceptionExpected()  throws IOException {
        String url = "www.instagram.com/smena8m/media/?max_id=";
        String jsonStr = RequestUtil.downloadPost(url, null);
    }


    @Test
    public void downloadPost_ValidInstagramMediaUrl_StringMediaResultExpected() throws IOException {
        String url = "https://scontent-arn2-1.cdninstagram.com/t51.2885-15/s150x150/e35/c0.135.1080.1080/22429801_282735898906918_860725563675901952_n.jpg";
        String path = "smena8m";
        String result = RequestUtil.downloadPost(url,path);
        Assert.assertTrue(result == "success");
    }

    @After
    public void tearDown(){

    }
}
