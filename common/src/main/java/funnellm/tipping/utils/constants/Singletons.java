package funnellm.tipping.utils.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import funnellm.tipping.utils.gson.DateTimeAdapter;
import funnellm.tipping.utils.http.HttpUtil;
import funnellm.tipping.utils.http.RescriptResponseHandler;

import java.util.Date;

public class Singletons {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(Date.class, new DateTimeAdapter()).create();
    public static final HttpUtil HTTP_UTIL = new HttpUtil();
    public static final RescriptResponseHandler RESPONSE_HANDLER = new RescriptResponseHandler();
}