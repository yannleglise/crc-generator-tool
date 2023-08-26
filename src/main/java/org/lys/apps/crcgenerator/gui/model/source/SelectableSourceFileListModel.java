/**
 * Class: SelectableSourceFileListModel.
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
 * Model for the filtered source files that can be selected.
 * 
 * @author Yann Leglise
 */
public class SelectableSourceFileListModel extends AbstractLysModel
{
  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "SelectableSourceFileListModel");

  /**
   * The unique identifier for the selectable source file model list aspect.
   * <p>
   * The aspect data provided by the notification is the new list of selectable source file models (a {@link List} of
   * {@link SelectableSourceFileModel}).
   * </p>
   */
  public static final long SELECTABLE_SOURCE_FILE_MODEL_LIST_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT,
          "Selectable source  file model list for SelectableSourceFileListModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -2377954613311339473L;

  /**
   * The list of selectable source file models.
   */
  private final List<SelectableSourceFileModel> selectableSourceFileModelList;

  /**
   * Constructor.
   */
  public SelectableSourceFileListModel()
  {
    super(MODEL_TYPE_IDENTIFIER);
    selectableSourceFileModelList = new ArrayList<>();
  }

  /**
   * Setter of the selectable source file model list.
   * 
   * @param pSelectableSourceFileModelList
   *          the list of sub models to set
   */
  public void setSelectableSourceFileModelList(final List<SelectableSourceFileModel> pSelectableSourceFileModelList)
  {
    // Ensure it is not the same
    boolean lIsSame = true;
    if (this.selectableSourceFileModelList.size() == pSelectableSourceFileModelList.size())
    {

      for (SelectableSourceFileModel lSsfm : this.selectableSourceFileModelList)
      {
        if (!pSelectableSourceFileModelList.contains(lSsfm))
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
      this.selectableSourceFileModelList.clear();
      this.selectableSourceFileModelList.addAll(pSelectableSourceFileModelList);
      notifyAttributeChange(SELECTABLE_SOURCE_FILE_MODEL_LIST_ASPECT_IDENTIFIER,
          (Serializable) this.selectableSourceFileModelList);
    }
  }

  /**
   * Getter of the selectable source file model list.
   * 
   * @return the selectable source file model list.
   */
  public List<SelectableSourceFileModel> getSelectableSourceFileModelList()
  {
    return selectableSourceFileModelList;
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
