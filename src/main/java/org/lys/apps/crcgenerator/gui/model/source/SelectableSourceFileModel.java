/**
 * Class: SelectableSourceFileModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.source;

import java.io.File;

import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.AbstractLysModel;

/**
 * Models a source file that can be selected or not.
 * 
 * @author Yann Leglise
 */
public class SelectableSourceFileModel extends AbstractLysModel
{

  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "SelectableSourceFileModel");

  /**
   * The unique identifier for the selection flag aspect.
   * <p>
   * The aspect data provided by the notification is the new selection state (a {@link Boolean}).
   * </p>
   */
  public static final long IS_SELECTED_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Selection flag for SelectableSourceFileModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 7222602356434065918L;

  /**
   * The source file .
   */
  private final File sourceFile;

  /**
   * The name of the file (only the last part).
   */
  private final String sourceFileName;

  /**
   * Indicates whether the extension is selected or not.
   */
  private boolean isSelected;

  /**
   * Constructor.
   * 
   * @param pSourceFile
   *          the associated source file.
   */
  public SelectableSourceFileModel(final File pSourceFile)
  {
    this(pSourceFile, false);
  }

  /**
   * Constructor.
   * 
   * @param pSourceFile
   *          the associated source file.
   * @param pIsSelected
   *          <code>true</code> if the element is selected, <code>false</code> if not.
   */
  public SelectableSourceFileModel(final File pSourceFile, final boolean pIsSelected)
  {
    super(MODEL_TYPE_IDENTIFIER);
    this.sourceFile = pSourceFile;
    if (pSourceFile == null)
    {
      sourceFileName = "";
    }
    else
    {
      sourceFileName = pSourceFile.getName();
    }
    this.isSelected = pIsSelected;
  }

  /**
   * Set the source file selection status.
   * 
   * @param pIsSelected
   *          <code>true</code> if the source file is selected, <code>false</code> if not.
   */
  public void setSelected(final boolean pIsSelected)
  {
    isSelected = pIsSelected;
    notifyAttributeChange(IS_SELECTED_ASPECT_IDENTIFIER, isSelected);
  }

  /**
   * Getter of the flag indicating whether this source file is selected or not.
   * 
   * @return <code>true</code> if selected, <code>false</code> if not.
   */
  public boolean isSelected()
  {
    return isSelected;
  }

  /**
   * Getter of the associated source file.
   * 
   * @return the source file.
   */
  public File getSourceFile()
  {
    return sourceFile;
  }

  /**
   * Getter of the source file name.
   * 
   * @return the last part of the source file path
   */
  public String getSourceFileName()
  {
    return sourceFileName;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModelValid()
  {
    return (sourceFile != null) && (sourceFile.isFile()) && (sourceFileName != null);
  }

}
