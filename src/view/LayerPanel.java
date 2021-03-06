/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package view;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwindx.examples.LayerTreeUsage;
import gov.nasa.worldwindx.examples.OnScreenLayerManager;
import gov.nasa.worldwindx.examples.layermanager.ElevationModelManagerPanel;
import gov.nasa.worldwindx.examples.layermanager.LayerManagerPanel;

import java.awt.BorderLayout;

import javax.swing.JPanel;



/**
 * Panel to display a list of layers. A layer can be turned on or off by
 * clicking a check box next to the layer name.
 *
 * @version $Id: LayerPanel.java 2115 2014-07-01 17:58:16Z tgaskins $
 *
 * @see LayerTreeUsage
 * @see OnScreenLayerManager
 */
public class LayerPanel extends JPanel {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  protected LayerManagerPanel layerManagerPanel;
  protected ElevationModelManagerPanel elevationModelManagerPanel;

  /**
   * Classe LayerPanel.
   * @param wwd WorldWindow
   */
  public LayerPanel(WorldWindow wwd) {
    super(new BorderLayout(10, 10));

    this.add(this.layerManagerPanel = new LayerManagerPanel(wwd), BorderLayout.CENTER);

  }

  public void updateLayers(WorldWindow wwd) {
    this.layerManagerPanel.update(wwd);
  }

  public void updateElevations(WorldWindow wwd) {
    this.elevationModelManagerPanel.update(wwd);
  }

  
  /**
   * @deprecated There is no need to call this method. As of 6/30/14 it is a
   *             no-op.
   */
  public void update(WorldWindow wwd) {
    // This is here merely to provide backwards compatibility for users of the
    // previous version of LayerPanel.
  }
}
