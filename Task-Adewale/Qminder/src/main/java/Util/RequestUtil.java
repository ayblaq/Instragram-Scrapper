package Util;

import com.sun.org.apache.xerces.internal.util.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

public class RequestUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    public static String makeHttpRequest(String url) throws IOException {
        LOGGER.info("Make Http request to '{}' url", url);
        ValidationUtil.validatePropertyNotNullOrEmpty(url, "url");
        try {
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();

            HttpGet request = new HttpGet(url);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            return EntityUtils.toString(result.getEntity(), "UTF-8");
        } catch (IOException ex) {
            LOGGER.error("Error making Http request to '{}' url", url);
            throw ex;
        }
    }

    public static String downloadPost(String url, String path) throws IOException {
        LOGGER.info("download image from this url '{}' url", url );
        ValidationUtil.validatePropertyNotNullOrEmpty(url, url);

        ValidationUtil.validatePropertyNotNullOrEmpty(path, "path");

        try {

            makeDir(path);

            String Path = Paths.get(new URI(url).getPath()).getFileName().toString();
            URL Imageurl = new URL(url);
            InputStream is = Imageurl.openStream();
            OutputStream os = new FileOutputStream(path+"/"+Path);
            byte[] b = new byte[2048];

            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b,0,length);

            }
            is.close();
            os.close();
            return "success";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return "failed";
    }

    public static void makeDir(String dir) throws IOException {
        File directory = new File(dir);
        if (directory.exists())
        {
            LOGGER.info("Folder already exist");
        }
        else
        {
                directory.mkdir();
                LOGGER.info("Folder created succesfully");

        }
    }

}