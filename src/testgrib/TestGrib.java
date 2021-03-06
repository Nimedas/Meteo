package testgrib;

import grib.parser.GribParser;

import java.io.FileNotFoundException;

import java.io.IOException;

import model.Prevision;
import net.sourceforge.jgrib.NoValidGribException;
import net.sourceforge.jgrib.NotSupportedException;

public class TestGrib {

  /**
   * main.
   * @param args les arguments
   */
  public static void main(String[] args) {
    try {
      long start = System.currentTimeMillis();
      GribParser gribParser = new GribParser();
      
      Prevision prevision = 
          gribParser.parsePrevisionFromGrib("TTxOcMxLToSYmtRzKDl0e75I4HAjqqDApv_.grb");
      
      System.out.println("Temps d'exécution : " + (System.currentTimeMillis() - start));
      System.out.println(prevision.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NotSupportedException e) {
      e.printStackTrace();
    } catch (NoValidGribException e) {
      e.printStackTrace();
    }
  }
}
