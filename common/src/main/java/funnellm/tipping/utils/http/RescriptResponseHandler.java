package funnellm.tipping.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import static funnellm.tipping.utils.constants.Constants.ENCODING_UTF8;
import static funnellm.tipping.utils.constants.Constants.STATUS_CODE_OK;

public class RescriptResponseHandler implements ResponseHandler<String> {
    public String handleResponse(HttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() != STATUS_CODE_OK) {
            String s = entity == null ? null : EntityUtils.toString(entity, ENCODING_UTF8);
            System.out.println("Call failed\n");
            System.out.println(s);
        }

        return entity == null ? null : EntityUtils.toString(entity,ENCODING_UTF8);
    }
}