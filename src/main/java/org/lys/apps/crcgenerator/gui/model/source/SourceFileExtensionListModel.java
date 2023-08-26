/**
 * Class: SourceFileExtensionListModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.source;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.AbstractLysModel;

/**
 * Model for the source files extensions to choose from.
 * 
 * @author Yann Leglise
 */
public class SourceFileExtensionListModel extends AbstractLysModel
{
  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "SourceFileExtensionListModel");

  /**
   * The unique identifier for the selected status aspect.
   * <p>
   * The aspect data provided by the notification is the selectable source files extension list (a {@link Boolean}).
   * </p>
   */
  public static final long SELECTABLE_SOURCE_FILES_EXTENSION_LIST_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT,
          "List of selectable source files extension for SourceFileExtensionListModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 7851699998144627862L;

  /**
   * The list of source file extension models.
   */
  private final List<SelectableSourceFilesExtensionModel> extensionModelList;

  /**
   * Constructor.
   */
  public SourceFileExtensionListModel()
  {
    super(MODEL_TYPE_IDENTIFIER);
    extensionModelList = new ArrayList<>();
  }

  /**
   * Setter of the extension model list.
   * 
   * @param pNewExtensionModelList
   *          the extension model list to set
   */
  public void setExtensionModelList(final List<SelectableSourceFilesExtensionModel> pNewExtensionModelList)
  {

    // Ensure it is not the same
    boolean lIsSame = true;
    if (this.extensionModelList.size() == pNewExtensionModelList.size())
    {

      for (SelectableSourceFilesExtensionModel lSsfem : this.extensionModelList)
      {
        if (!pNewExtensionModelList.contains(lSsfem))
        {
          lIsSame = false;
          break;
        }
      }
    }
    else
    {
      lIsSame = false;
    }

    if (!lIsSame)
    {
      extensionModelList.clear();
      extensionModelList.addAll(pNewExtensionModelList);
      notifyAttributeChange(SELECTABLE_SOURCE_FILES_EXTENSION_LIST_ASPECT_IDENTIFIER,
          (Serializable) extensionModelList);
    }
  }

  /**
   * Getter of the extension model list.
   * 
   * @return the extension model list.
   */
  public List<SelectableSourceFilesExtensionModel> getExtensionModelList()
  {
    return extensionModelList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModelValid()
  {
    return true;
  }

}
