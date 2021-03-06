package model;

public class Vent {
  private double direction;
  private double vitesse;
  
  public Vent(double ventU, double ventV) {
    this.direction = Vent.calculerDirection(ventU, ventV);
    this.vitesse = Vent.calculerVitesse(ventU, ventV);
  }
  
  public double getDirection() {
    return direction;
  }
  
  public double getVitesse() {
    return vitesse;
  }
  
  public static double calculerDirection(double vecteurU, double vecteurV) {
    return (Math.atan2(vecteurU, vecteurV) + Math.PI) * 180 / Math.PI;
  }
  
  public static double calculerVitesse(double vecteurU, double vecteurV) {
    return Math.sqrt(Math.pow(vecteurU, 2) + Math.pow(vecteurV, 2));
  }
  
  /**
   * Retourne une représentation en chaine de caractère de ce vent.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("Direction=");
    
    sb.append(direction).append(" | Vitesse=").append(vitesse);
    
    return sb.toString();
  }
  
  public void setVitesse(double vitesse) {
    
    this.vitesse = vitesse;
  }
}
