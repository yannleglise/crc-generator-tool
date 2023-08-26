/**
 * Class: CrcGenerationStatusCellRenderer.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.generation.table.renderer;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.model.generation.CrcGenerationStatus;

/**
 * Renderer for the status of a CRC file generation.
 * 
 * @author Yann Leglise
 */
public class CrcGenerationStatusCellRenderer extends AbstractCrcTableCellRenderer
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -266903437699145162L;

  /**
   * Constructor.
   */
  public CrcGenerationStatusCellRenderer()
  {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setValue(final Object pValue)
  {
    String lValueRepresentation;

    if (pValue instanceof CrcGenerationStatus)
    {
      CrcGenerationStatus lStatus = (CrcGenerationStatus) pValue;

      switch (lStatus)
      {
        case TODO:
          lValueRepresentation = GuiConstants.CFT_STATUS_TODO_COL_FORMAT;
          break;
        case PENDING:
          lValueRepresentation = GuiConstants.CFT_STATUS_PENDING_COL_FORMAT;
          break;
        case SUCCESS:
          lValueRepresentation = GuiConstants.CFT_STATUS_SUCCESS_COL_FORMAT;
          break;
        case FAILED:
          lValueRepresentation = GuiConstants.CFT_STATUS_FAILED_COL_FORMAT;
          break;
        default:
          lValueRepresentation = "";
          break;
      }

      // TODO find a way to get the error
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
    }

    super.setValue(lValueRepresentation);
  }
}
