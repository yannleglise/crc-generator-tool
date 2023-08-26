/**
 * Class: SelectableSourceFilePanel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.source;

import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFileModel;
import org.lys.apps.crcgenerator.gui.panels.commons.AbstractSelectableElementView;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.aspect.LysAspectType;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.ILysModel;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.lys.gui.mvc.utils.LysMvcLoggingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View representing a source file that allow selecting it or not.
 * 
 * @author Yann Leglise
 */
public class SelectableSourceFileView extends AbstractSelectableElementView
{
  /**
   * The unique identifier for this view type.
   */
  public static final long VIEW_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.VIEW_ELEMENT_TYPE, "SelectableSourceFileView");

  /**
   * The unique identifier for the source file name aspect.
   * <p>
   * The aspect data provided by the notification is the new source file name (a {@link String}).
   * </p>
   */
  public static final long SELECTABLE_SOURCE_FILE_MODEL_LIST_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Source file name for SelectableSourceFileView");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -1323670982134750261L;

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SelectableSourceFileView.class);

  /**
   * The associated model.
   */
  private SelectableSourceFileModel model;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider
   */
  public SelectableSourceFileView(final ILysActionProvider pActionProvider)
  {
    super(VIEW_TYPE_IDENTIFIER, pActionProvider);
    model = null;

    build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void associateModel(final ILysModel pModel)
  {
    if (pModel instanceof SelectableSourceFileModel)
    {
      this.model = (SelectableSourceFileModel) pModel;
      updateSelectionCbLabelFromModel();
    }
    else
    {
      String lMessage = LysMvcLoggingUtil.getMessageAboutModelUnexpectedClass(SelectableSourceFileModel.class, pModel);
      LOGGER.error(lMessage);
    }
  }

  /**
   * Getter of the model.
   * 
   * @return the associated model.
   */
  public SelectableSourceFileModel getModel()
  {
    return model;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getCheckBoxLabel()
  {
    String lLabel;
    if (model == null)
    {
      lLabel = "";
    }
    else
    {
      // Use the name from the model to compose the check box text
      lLabel = model.getSourceFileName();
    }
    return lLabel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleSelectionChange(final boolean pIsElementSelected)
  {
    // Notify the change
    notify(SELECTABLE_SOURCE_FILE_MODEL_LIST_ASPECT_IDENTIFIER, LysAspectType.VIEW_SELECTION_CHANGED,
        pIsElementSelected);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    // Not used
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performPreCreationInitialization()
  {
    // Not used
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performPostCreationInitialization()
  {
    // Not used
  }

}
