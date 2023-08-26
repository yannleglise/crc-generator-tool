/**
 * Class: CrcTableHeaderCellRenderer.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.generation.table.renderer;

import javax.swing.table.DefaultTableCellRenderer;

import org.lys.apps.crcgenerator.gui.GuiConstants;

/**
 * Custom renderer for table header cells.
 * 
 * @author Yann Leglise
 */
public class CrcTableHeaderCellRenderer extends DefaultTableCellRenderer
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 6398995405529344707L;

  /**
   * Constructor.
   */
  public CrcTableHeaderCellRenderer()
  {
    super();
    setBackground(GuiConstants.CFT_HEADER_BACKGROUND_COLOR);
  }
}
