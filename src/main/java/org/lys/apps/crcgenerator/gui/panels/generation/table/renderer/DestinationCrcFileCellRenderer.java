/**
 * Class: DestinationCrcFileCellRenderer.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.generation.table.renderer;

import java.io.File;
import java.text.MessageFormat;

import org.lys.apps.crcgenerator.gui.GuiConstants;

/**
 * Table cell renderer for destination CRC files.
 * 
 * @author Yann Leglise
 */
public class DestinationCrcFileCellRenderer extends AbstractCrcTableCellRenderer
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 2415104879084185011L;

  /**
   * Constructor.
   */
  public DestinationCrcFileCellRenderer()
  {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void setValue(final Object pValue)
  {
    String lValueRepresentation;

    if (pValue instanceof File)
    {
      File lDestinationCrcFile = (File) pValue;
      lValueRepresentation = MessageFormat.format(GuiConstants.CFT_DESTINATION_CRC_FILE_COL_FORMAT,
          lDestinationCrcFile.getName());
      setToolTipText(lDestinationCrcFile.getAbsolutePath());
    }
    else
    {
      if (pValue == null)
      {
        lValueRepresentation = "";
      }
      else
      {
        lValueRepresentation = pValue.toString();
      }
      setToolTipText("");
    }

    super.setValue(lValueRepresentation);
  }
}
