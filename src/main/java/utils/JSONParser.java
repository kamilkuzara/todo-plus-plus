package utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import model.ListModel;

public class JSONParser{
    public static ArrayList<ListModel> loadFromJSON()
    {
        try
        {
            String json = SessionFileIO.instance().readSessionFile();

            Gson gson = new Gson();
            Type collectionType = new TypeToken<ArrayList<ListModel>>(){}.getType();
            ArrayList<ListModel> newLists = gson.fromJson(json, collectionType);

            return newLists;
        }
        catch(JsonSyntaxException exception)
        {
            System.out.println("The file is not in valid JSON format");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<ListModel>();
        }
        catch(JsonParseException exception)
        {
            System.out.println("A parsing error occurred");
            exception.printStackTrace();

            // return an empty list so that nothing gets created and the calling
            // method doesn't produce errors
            return new ArrayList<ListModel>();
        }
    }

    public static void saveToJSON(List<ListModel> lists)
    {
        Gson gson = new Gson();
        String json = gson.toJson(lists);

        SessionFileIO.instance().writeSessionFile(json);
    }
}
