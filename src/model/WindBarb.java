package model;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.render.Offset;
import gov.nasa.worldwind.render.PointPlacemark;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;

import java.awt.Color;

public class WindBarb extends PointPlacemark {
  private String name;
  private double direction; // 0-360 degrees
  private double knots;
  private static  final double DECALAGE = 90.0;
  private static final Double MS_TO_KNOTS = 0.51444444444;

  public WindBarb(Position position) {
    this(position, 0, 0);
  }

  /**
   * Construit une barbule de vent.
   * @param position La position du barbule utilisée par l'API NWW
   * @param dir La direction du barbule
   * @param spd La vitesse du barbule
   */
  public WindBarb(Position position, double dir, double spd) {
    super(position);
    this.direction = dir + DECALAGE;
    this.knots = MS_TO_KNOTS * spd;
    initialize();
  }

  public WindBarb(Double latitude, Double longitude, double dir, double spd) {
    this(Position.fromDegrees(latitude, longitude), dir, spd);
  }

  private void initialize() {
    this.setLineEnabled(false);
    this.setAltitudeMode(WorldWind.CLAMP_TO_GROUND);
    PointPlacemarkAttributes attrs = new PointPlacemarkAttributes();

    attrs.setImageColor(Color.BLUE);// new Color(1f, 1f, 1f, 0.6f));
    attrs.setScale(1.0);
    attrs.setImageOffset(new Offset(0.5d, 0.5d, AVKey.FRACTION, AVKey.FRACTION));
    attrs.setLabelOffset(new Offset(0.9d, 0.6d, AVKey.FRACTION, AVKey.FRACTION));
    attrs.setHeading(0.0);
    attrs.setHeadingReference(AVKey.RELATIVE_TO_GLOBE);

    this.setAttributes(attrs);

    this.setDirection(direction + DECALAGE);
    this.setKnots(knots);
  }

  /**
   * Retourne la direction effective du barbule (en tenant compte du décalage).
   * @return the direction
   */
  public double getDirection() {
    return direction - DECALAGE;
  }

  public void setDirection(double direction) {
    PointPlacemarkAttributes attrs = this.getAttributes();
    this.direction = direction + DECALAGE;
  }

  /**
   * Retourne la vitesse du barbule en noeuds.
   * @return the knots
   */
  public double getKnots() {
    return knots;
  }

  /**
   * Fixe la vitesse du barbule en noeuds.
   * @param knots La nouvelle vitesse du barbule en noeuds.
   */
  public void setKnots(double knots) {
    PointPlacemarkAttributes attrs = this.getAttributes();
    int speed = (int) (Math.ceil(knots / 5.0) * 5);
    if (speed < 0) {
      speed = 0;
    }
    
    if (speed > 200) {
      speed = 200;
    }
    attrs.setImageAddress("barbs/wind_" + speed + ".png");

    this.knots = knots;
  }

}