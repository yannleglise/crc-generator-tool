/**
 * Class: SelectableSourceFilesExtensionModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.source;

import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.AbstractLysModel;

/**
 * Models a source file extension that can be selected or not.
 * 
 * @author Yann Leglise
 */
public class SelectableSourceFilesExtensionModel extends AbstractLysModel
{

  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "SelectableSourceFilesExtensionModel");

  /**
   * The unique identifier for the selected status aspect.
   * <p>
   * The aspect data provided by the notification is the new selection status (a {@link Boolean}).
   * </p>
   */
  public static final long SELECTION_STATUS_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Selection status for SelectableSourceFilesExtensionModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 5679450128143621262L;

  /**
   * The source file extension (without the dot).
   */
  private final String extension;

  /**
   * Indicates whether the extension is selected or not.
   */
  private boolean isSelected;

  /**
   * Constructor.
   * 
   * @param pExtension
   *          the source file extension (without the dot).
   */
  public SelectableSourceFilesExtensionModel(final String pExtension)
  {
    this(pExtension, false);
  }

  /**
   * Constructor.
   * 
   * @param pExtension
   *          he source file extension (without the dot).
   * @param pIsSelected
   *          <code>true</code> if the element is selected, <code>false</code> if not.
   */
  public SelectableSourceFilesExtensionModel(final String pExtension, final boolean pIsSelected)
  {
    super(MODEL_TYPE_IDENTIFIER);
    extension = pExtension;
    isSelected = pIsSelected;
  }

  /**
   * Set the source file extension selection status.
   * 
   * @param pSelected
   *          <code>true</code> if the extension is selected, <code>false</code> if not.
   */
  public void setSelected(final boolean pSelected)
  {
    isSelected = pSelected;
    notifyAttributeChange(SELECTION_STATUS_ASPECT_IDENTIFIER, pSelected);
  }

  /**
   * Getter of the flag indicating whether this file extension is selected or not.
   * 
   * @return <code>true</code> if selected, <code>false</code> if not.
   */
  public boolean isSelected()
  {
    return isSelected;
  }

  /**
   * Getter of the extension.
   * 
   * @return the associated extension (without the dot)
   */
  public String getExtension()
  {
    return extension;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModelValid()
  {
    return (extension != null) && (!extension.trim().isEmpty());
  }

}
