package models;

public class TravelServiceInfo {
  public float duration;
  public float distance;

  public TravelServiceInfo(float duration, float distance) {
    this.duration = duration;
    this.distance = distance;
  }

  public String toString() {
    return "Duration: " + duration + " Distance: " + distance;
  }
}
