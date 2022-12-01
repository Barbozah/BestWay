package controllers;

public class TimeUtils {

  public static String getFormattedTime(long millis) {
    long seconds = millis / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;

    seconds = seconds % 60;
    minutes = minutes % 60;
    hours = hours % 24;

    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
  }

  public static String getFormattedDate(long millis) {
    long seconds = millis / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;
    long days = hours / 24;
    long months = days / 30;
    long years = months / 12;

    seconds = seconds % 60;
    minutes = minutes % 60;
    hours = hours % 24;
    days = days % 365;
    months = months % 12;
    years = years % 100;

    return String.format("%4d:%02d:%02d:%02d:%02d:%02d", years, months, days, hours, minutes, seconds);
  }

  public static long getCurrentTime() {
    return System.currentTimeMillis();
  }

  public static long addTime(long start, float minutes) {
    return start + (long) (minutes * 60 * 1000);
  }

}
