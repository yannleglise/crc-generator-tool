/**
 * Class: SourceFileExtensionListView.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.source;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFilesExtensionModel;
import org.lys.apps.crcgenerator.gui.model.source.SourceFileExtensionListModel;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.ILysModel;
import org.lys.gui.mvc.notifications.ILysNotifiable;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.lys.gui.mvc.utils.LysMvcLoggingUtil;
import org.lys.gui.mvc.view.AbstractLysView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Panel for the list of source file extensions.
 * 
 * @author Yann Leglise
 */
public class SourceFileExtensionListView extends AbstractLysView
{
  /**
   * The unique identifier for this view type.
   */
  public static final long VIEW_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.VIEW_ELEMENT_TYPE, "SourceFileExtensionListView");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -185654575343581410L;

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SourceFileExtensionListView.class);

  /**
   * The sub-panel containing all the components corresponding to the source file extensions.
   */
  private JPanel sourceFileExtensionsPanel;

  /**
   * The associated model.
   */
  private SourceFileExtensionListModel model;

  /**
   * The notifiable to associated with selectable source file view.
   */
  private transient ILysNotifiable selectableSourceFileExtensionViewNotifiable;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider
   */
  public SourceFileExtensionListView(final ILysActionProvider pActionProvider)
  {
    super(VIEW_TYPE_IDENTIFIER, pActionProvider);
    sourceFileExtensionsPanel = null;
    model = null;
    selectableSourceFileExtensionViewNotifiable = null;

    build();
  }

  /**
   * Setter of the notifiable to associate with the selectable source file extension views.
   *
   * @param pSelectableSourceFileExtensionViewNotifiable
   *          the notifiable.
   */
  public void setSelectableSourceFileExtensionViewNotifiable(
      final ILysNotifiable pSelectableSourceFileExtensionViewNotifiable)
  {
    selectableSourceFileExtensionViewNotifiable = pSelectableSourceFileExtensionViewNotifiable;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void associateModel(final ILysModel pModel)
  {
    if (pModel instanceof SourceFileExtensionListModel)
    {
      this.model = (SourceFileExtensionListModel) pModel;
      // Register to this model's notifications
      this.model.register(this);
    }
    else
    {
      String lMessage = LysMvcLoggingUtil.getMessageAboutModelUnexpectedClass(SourceFileExtensionListModel.class,
          pModel);
      LOGGER.error(lMessage);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    if (pSource.getTypeIdentifier() == SourceFileExtensionListModel.MODEL_TYPE_IDENTIFIER)
    {
      fillFromModel();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    // Create the source file extensions elements panel
    sourceFileExtensionsPanel = new JPanel();
    sourceFileExtensionsPanel.setLayout(new BoxLayout(sourceFileExtensionsPanel, BoxLayout.Y_AXIS));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    installLabelBorderFor(GuiConstants.LABEL_BORDER_COLOR, GuiConstants.LABEL_BORDER_FONT,
        GuiConstants.SOURCE_FILE_EXTENSION_LIST_PANEL_BORDER_TEXT);
    // Place the panel in a scroll pane
    JScrollPane lScrollPane = new JScrollPane(sourceFileExtensionsPanel,
        ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    layoutComponent(lScrollPane, X_POS_01, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_100, FILL_BOTH,
        ANCHOR_LEFT, ELEMENT_T_L_B_R_INSET);
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

  /**
   * Fill the panel with as many component panels as needed to reflect the elements in the model.
   */
  private void fillFromModel()
  {
    List<SelectableSourceFilesExtensionModel> lExtensionModelList = model.getExtensionModelList();
    // Empty the panel
    sourceFileExtensionsPanel.removeAll();
    SelectableSourceFileExtensionView lView;
    for (SelectableSourceFilesExtensionModel lModel : lExtensionModelList)
    {
      lView = new SelectableSourceFileExtensionView(null);
      // Set the associated model
      lView.associateModel(lModel);
      // Let the controller listen to the notifications of the panel
      lView.register(selectableSourceFileExtensionViewNotifiable);
      // Add this panel
      sourceFileExtensionsPanel.add(lView);
    }
    revalidate();
    repaint();
  }
}
