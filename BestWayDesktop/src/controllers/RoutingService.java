package controllers;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.GsonBuilder;

import models.ResponseRoutingApi;
import models.TravelServiceInfo;

public class RoutingService {
  public static TravelServiceInfo getTravelInfo(String latLong1, String latLong2) throws Exception {
    String baseRoutingUrl = "https://routing.openstreetmap.de/routed-car/route/v1/driving/";
    String path = latLong1 + ";" + latLong2;
    String params = "?overview=false&alternatives=true&steps=true";
    URL url;
    try {
      url = new URL(baseRoutingUrl + path + params);
    } catch (MalformedURLException e) {
      throw new RuntimeException("Parametros de rota invalidos");
    }
    try {
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      if (responseCode != 200) {
        throw new RuntimeException();
      }
      BufferedReader in = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();

      GsonBuilder builder = new GsonBuilder();
      com.google.gson.Gson gson = builder.create();
      ResponseRoutingApi res = gson.fromJson(response.toString(), ResponseRoutingApi.class);
      return new TravelServiceInfo(res.getDuration(), res.getDistance());
    } catch (Exception e) {
      throw new Exception("Erro ao conectar com o servidor de rotas");
    }
  }
}
