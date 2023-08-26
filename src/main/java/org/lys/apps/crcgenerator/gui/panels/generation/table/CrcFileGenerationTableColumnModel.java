/**
 * Class: CrcFileGenerationTableColumnModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.generation.table;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.model.generation.CrcFileGenerationTableModel;
import org.lys.apps.crcgenerator.gui.panels.generation.table.renderer.CrcGenerationStatusCellRenderer;
import org.lys.apps.crcgenerator.gui.panels.generation.table.renderer.CrcTableHeaderCellRenderer;
import org.lys.apps.crcgenerator.gui.panels.generation.table.renderer.DestinationCrcFileCellRenderer;
import org.lys.apps.crcgenerator.gui.panels.generation.table.renderer.SourceFileCellRenderer;

/**
 * A specialize column model for the CRC generation table.
 *
 * @author Yann Leglise
 */
public class CrcFileGenerationTableColumnModel extends DefaultTableColumnModel
{
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 6046801049147446221L;

  /**
   * Constructor.
   */
  public CrcFileGenerationTableColumnModel()
  {
    super();
  }

  /**
   * Configure the columns.
   */
  public void configureColumns()
  {
    TableColumn lTc;

    // Source file column
    lTc = getColumn(CrcFileGenerationTableModel.SOURCE_FILE_COL_IDX);
    lTc.setCellRenderer(new SourceFileCellRenderer());
    lTc.setHeaderRenderer(new CrcTableHeaderCellRenderer());
    lTc.setPreferredWidth(GuiConstants.CFT_SOURCE_FILE_COL_PREF_WIDTH);

    // Destination CRC file column
    lTc = getColumn(CrcFileGenerationTableModel.DEST_CRC_FILE_COL_IDX);
    lTc.setCellRenderer(new DestinationCrcFileCellRenderer());
    lTc.setHeaderRenderer(new CrcTableHeaderCellRenderer());
    lTc.setPreferredWidth(GuiConstants.CFT_DEST_CRC_FILE_COL_PREF_WIDTH);

    // CRC generation status column
    lTc = getColumn(CrcFileGenerationTableModel.GENERATION_STATUS_COL_IDX);
    lTc.setCellRenderer(new CrcGenerationStatusCellRenderer());
    lTc.setHeaderRenderer(new CrcTableHeaderCellRenderer());
    lTc.setMinWidth(GuiConstants.CFT_CRC_GENERATION_STATUS_COL_PREF_WIDTH);
    lTc.setMaxWidth(GuiConstants.CFT_CRC_GENERATION_STATUS_COL_PREF_WIDTH);
    lTc.setResizable(false);
  }

}
