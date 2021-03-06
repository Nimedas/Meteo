package facade;

import grib.parser.GribParser;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.render.PointPlacemarkAttributes;
import model.Prevision;
import model.PrevisionParDate;
import model.Vent;
import model.WindBarb;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;
import view.FlatWorld;
import view.FlatWorld.AppFrame;

public class MeteoFacade {
  private static MeteoFacade instance;
  GribParser parser;
  AppFrame appframe;

  private Prevision prevision;
  private Date currentDate;
  
  private MeteoFacade() {
  }

  /**
   * Singleton de la classe.
   * @return Le singleton de la classe
   */
  public static MeteoFacade getInstance() {
    if (instance == null) {
      instance = new MeteoFacade();
      instance.init();
    }

    return instance;
  }

  private void init() {
    FlatWorld.main(null);
    this.appframe = (AppFrame) FlatWorld.getFrame();
    this.parser = new GribParser();
  }

  /**
   * Charge un fichier Grib.
   * @param filename le nom du fichier à charger
   * @return L'objet Prevision parsé à partir du fichier
   */
  public Prevision loadGrib(String filename) {
    long start = System.currentTimeMillis();

    try {
      prevision = parser.parsePrevisionFromGrib(filename);
      System.out.println(prevision.toString());
    } catch (NoSuchElementException | IOException 
        | NoValidGribException | NotSupportedException e) {
      e.printStackTrace();
    }
    
    System.out.println("Temps d'exécution : " + (System.currentTimeMillis() - start));
    
    currentDate = prevision.getListeDates().get(0);
    this.displayDate(prevision, currentDate);
    return prevision;
  }

  public List<Date> getDates(Prevision prevision) {
    return prevision.getListeDates();
  }

  /**
   * Affiche les données des vents à la date sélectionnée en paramètre.
   * @param prev L'objet Prevision du fichier en cours
   * @param date La date sélectionnée
   */
  public void displayDate(Prevision prev, Date date) {
    PrevisionParDate myPrevision = prev.getPrevisionParDate(date);
    System.out.println(myPrevision.toString());
    ArrayList<WindBarb> windbarbs = new ArrayList<>();
    for (int y = 0; y < myPrevision.getVents().length; y++) {
      for (int x = 0; x < myPrevision.getVents()[y].length; x++) {
        Vent vent = myPrevision.getVents()[y][x];
        Double latitude = prev.getGrille().getLatitude(y);
        Double longitude = prev.getGrille().getLongitude(x);
        WindBarb wb = new WindBarb(latitude, longitude, vent.getDirection(), vent.getVitesse());
        wb.setValue(
            AVKey.DISPLAY_NAME, Math.round(vent.getDirection()) + "°" + " " 
                + Math.round(vent.getVitesse()) + " m/s");
        windbarbs.add(wb);
      }
    }
    appframe.displayWindbarbs(windbarbs);
  }
  
  public void refreshWindbarbs() {
    displayDate(prevision, currentDate);
  }

  public void setCurrentDate(Date date) {
    currentDate = date;
  }
  
  public Date getCurrentDate() {
    return currentDate;
  }
  
  public void setCurrentPrevision(Prevision prevision) {
    this.prevision = prevision;
  }
  
  public Prevision getCurrentPrevision() {
    return prevision;
  }

  public void changeUnit(ActionEvent e) {
    for (iterable_type  : iterable) {
      
    }
    
  }
  
}
