/**
 * Class: CrcFileGenerationTable.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.generation.table;

import javax.swing.JTable;

import org.lys.apps.crcgenerator.gui.model.generation.CrcFileGenerationTableModel;
import org.lys.apps.crcgenerator.gui.model.generation.CrcFilesGenerationModel;

/**
 * A specialized table to display the CRC file generation elements.
 * 
 * @author Yann Leglise
 */
public class CrcFileGenerationTable extends JTable
{
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -5618952727207983367L;

  /**
   * The underlying model.
   */
  private final CrcFileGenerationTableModel model;

  /**
   * The column model.
   */
  private final CrcFileGenerationTableColumnModel cfgtColumnModel;

  /**
   * Constructor.
   */
  public CrcFileGenerationTable()
  {
    super();

    model = new CrcFileGenerationTableModel();

    cfgtColumnModel = new CrcFileGenerationTableColumnModel();

    // Set the column model before the model
    setColumnModel(cfgtColumnModel);
    setModel(model);

    cfgtColumnModel.configureColumns();
  }

  /**
   * Update the table model from the given CRC files generation model.
   * 
   * @param pCrcFilesGenerationModel
   *          the CRC files generation model to update from
   */
  public void fillFrom(final CrcFilesGenerationModel pCrcFilesGenerationModel)
  {
    model.fillFrom(pCrcFilesGenerationModel.getCrcGenerationElementList());
    cfgtColumnModel.configureColumns();
  }
}
