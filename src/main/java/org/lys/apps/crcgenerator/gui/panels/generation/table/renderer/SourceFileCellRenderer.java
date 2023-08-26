/**
 * Class: SourceFileCellRenderer.
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
 * Table cell renderer for source files.
 * 
 * @author Yann Leglise
 */
public class SourceFileCellRenderer extends AbstractCrcTableCellRenderer
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -7728313033753754129L;

  /**
   * Constructor.
   */
  public SourceFileCellRenderer()
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
      File lSourceFile = (File) pValue;
      lValueRepresentation = MessageFormat.format(GuiConstants.CFT_SOURCE_FILE_COL_FORMAT, lSourceFile.getName());
      setToolTipText(lSourceFile.getAbsolutePath());
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
