package model.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.chat.Chat;

import java.util.ArrayList;
import java.util.List;

public class XY {
    public final int x;
    public final int y;

    public XY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static XY fromJson(String json) {
        return new Gson().fromJson(json, new TypeToken<XY>(){}.getType());
    }
    public static ArrayList<XY> fromJsonArrayList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<XY>>(){}.getType());
    }

    public static String toJson(ArrayList<XY> xyArrayList) {
        return new GsonBuilder().setPrettyPrinting().create().toJson(xyArrayList);
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
