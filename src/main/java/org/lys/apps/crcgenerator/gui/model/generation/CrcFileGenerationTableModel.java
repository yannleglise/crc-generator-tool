/**
 * Class: CrcFileGenerationTableModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.generation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.lys.apps.crcgenerator.gui.GuiConstants;

/**
 * Specialized table model for CRC file generation elements.
 * 
 * @author Yann Leglise
 */
public class CrcFileGenerationTableModel extends AbstractTableModel implements ICrcGenerationElementStateChangeListener
{

  /**
   * Index for the source file column of the CRC file generation table.
   */
  public static final int SOURCE_FILE_COL_IDX = 0;

  /**
   * Index for the destination CRC file column of the CRC file generation table.
   */
  public static final int DEST_CRC_FILE_COL_IDX = 1;

  /**
   * Index for the CRC generation status column of the CRC file generation table.
   */
  public static final int GENERATION_STATUS_COL_IDX = 2;

  /**
   * The number of columns of the CRC file generation table.
   * <p>
   * Source file | CRC target file | CRC generation status.
   * </p>
   */
  public static final int COLUMN_COUNT = 3;

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 4821333813247739572L;

  /**
   * The list of columns.
   */
  private final List<String> columnNames;

  /**
   * The list of CRC generation elements.
   */
  private final transient List<CrcGenerationElement> crcGenerationElementList;

  /**
   * Constructor.
   */
  public CrcFileGenerationTableModel()
  {
    super();
    columnNames = new ArrayList<>();
    // Fill column names
    columnNames.add(GuiConstants.CFT_SOURCE_FILE_COL_LABEL);
    columnNames.add(GuiConstants.CFT_DESTINAITON_CRC_FILE_COL_LABEL);
    columnNames.add(GuiConstants.CFT_CRC_GENERATION_STATUS_COL_LABEL);

    crcGenerationElementList = new ArrayList<>();
  }

  /**
   * Fill the model from the given list of CRC generation elements.
   * 
   * @param pCrcGenerationElementList
   *          the list of CRC generation elements to consider (must not be <code>null</code>).
   */
  public void fillFrom(final List<CrcGenerationElement> pCrcGenerationElementList)
  {
    this.crcGenerationElementList.clear();
    for (CrcGenerationElement lCrcGenerationElement : pCrcGenerationElementList)
    {
      // Declare this as listener to update the table when its status changes
      lCrcGenerationElement.setListener(this);
      this.crcGenerationElementList.add(lCrcGenerationElement);
    }
    fireTableStructureChanged();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getRowCount()
  {
    return crcGenerationElementList.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getColumnCount()
  {
    return COLUMN_COUNT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnName(final int pColumn)
  {
    return columnNames.get(pColumn);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValueAt(final int pRowIndex, final int pColumnIndex)
  {
    Object lValue = null;
    if ((0 <= pRowIndex) && (pRowIndex < getRowCount()) && (0 <= pColumnIndex) && (pColumnIndex < COLUMN_COUNT))
    {
      CrcGenerationElement lCrcGenerationElement = crcGenerationElementList.get(pRowIndex);
      if (pColumnIndex == SOURCE_FILE_COL_IDX)
      {
        lValue = lCrcGenerationElement.getSourceFile();
      }
      else if (pColumnIndex == DEST_CRC_FILE_COL_IDX)
      {
        lValue = lCrcGenerationElement.getDestinationFile();
      }
      else if (pColumnIndex == GENERATION_STATUS_COL_IDX)
      {
        lValue = lCrcGenerationElement.getStatus();
      }
    }

    return lValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleCrcGenerationNewStatus(final long pInstanceIdentifier, final CrcGenerationStatus pNewStatus)
  {
    CrcGenerationElement lCge;
    for (int i = 0; i < getRowCount(); i++)
    {
      lCge = crcGenerationElementList.get(i);
      if (lCge.matches(pInstanceIdentifier))
      {
        // The status of the instance at this row changed
        fireTableCellUpdated(i, GENERATION_STATUS_COL_IDX);
        break;
      }
    }
  }

}
