package models;

import java.util.List;

public class ResponseRoutingApi {
  List<TravelServiceInfo> routes;

  public float getDistance() {
    return routes.get(0).distance;
  }

  public float getDuration() {
    return routes.get(0).duration;
  }
}
