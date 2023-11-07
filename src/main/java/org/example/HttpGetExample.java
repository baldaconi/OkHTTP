package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.annotation.Documented;

public class HttpGetExample {
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url("https://jsoup.org/cookbook/input/parse-document-from-string")
                .build();
        try {
            Response response = client.newCall(getRequest).execute();
            if(response.code() == 200){
                System.out.println("Response code: " + response.code());
                System.out.println("Response content type: " + response.header("content-type"));
                //System.out.println("Response body: " + response.body().string());
                processHTML(response.body().string());
            }else{
                System.out.println("NOT FOUND");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processHTML(String htmlCode){
        Document doc = Jsoup.parse(htmlCode);

        Element elem = doc.selectFirst("title");

        System.out.println("Page title: " + elem.html());

        System.out.println(elem.attributesSize());

    }
}