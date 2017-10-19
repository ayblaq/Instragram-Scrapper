package Service;

import Factory.ObjectMapperFactory;
import Model.Item;
import Model.Json;
import Util.RequestUtil;
import Util.ValidationUtil;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DownloadService {
    private final Logger LOGGER = LoggerFactory.getLogger(DownloadService.class);


    public void downloadImages(String username) throws IOException {


        ValidationUtil.validatePropertyNotNullOrEmpty(username, username);
        String maxId = null;

        List<Item> items = new LinkedList<>();
        Json json;


        try {

             do {
                 String url = String.format("https://www.instagram.com/%s/media/?max_id=%s", username, maxId);
                 String jsonStr = RequestUtil.makeHttpRequest(url);
                 json = getJson(jsonStr);
                 maxId = getMax(json);
                 if(json != null && json.getItems() != null) {
                    items.addAll(json.getItems());
                 }

             }while (maxId != null);



                for (int i = 0; i < (items.size());i++) {

                String ImageUrl = items.get(i).getImages().getStandardResolution().getUrl();
                LOGGER.info("This is the image url %s at %d",ImageUrl, i);
                RequestUtil.downloadPost(ImageUrl, username);
            }
                        System.out.println((json.toString().length()));
        } catch (IOException ex) {
            LOGGER.error("Error downloading images. '{}'", ex.getMessage());
        }
    }

    private String getMax(Json json) {
        if (json == null)  {
            return null;
        }


        List<Item>  items = json.getItems();
        if (items == null|| items.size() == 0) {
            return null;
        }
        return items.get(items.size() - 1).getId();
    }

    private Json getJson(String jsonStr) throws IOException {
        ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
        try {
            return mapper.readValue(jsonStr,  Json.class);
        }catch (JsonMappingException ex) {
            LOGGER.error("Error mapping Json object. Exception: '{}'", ex.getMessage());
            throw ex;
        }catch (IOException ex) {
            LOGGER.error("IO exception while mapping Json object. Exception: '{}'", ex.getMessage());
            throw ex;
        }
    }
}