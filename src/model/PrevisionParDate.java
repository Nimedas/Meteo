package model;

import java.util.Date;

public class PrevisionParDate {
  private Vent[][] vents;

  Date date;

  public PrevisionParDate(Date date, int nbx, int nby) {
    this.date = date;
    vents = new Vent[nby][nbx];
  }

  public void addVent(int x, int y, Vent vent) {
    vents[y][x] = vent;
  }

  public Date getDate() {
    return date;
  }

  /**
   * Retourne vrai si la date passée en paramètre est égale à la date de la
   * prévision
   * 
   * @param date
   * @return
   */
  public boolean estLaMemeDate(Date date) {
    return this.date.equals(date);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder(date.toString() + " - Grille de taille [");
    sb.append(vents.length).append(",").append(vents[0].length).append("]\n");

    return sb.toString();
  }

  public Vent[][] getVents() {
    return vents;
  }
}