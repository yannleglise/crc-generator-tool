/**
 * Class: SelectableSourceFileListPanel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.source;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFileListModel;
import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFileModel;
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
 * View for the list of selectable source files.
 * 
 * @author Yann Leglise
 */
public class SelectableSourceFileListView extends AbstractLysView
{
  /**
   * The unique identifier for this view type.
   */
  public static final long VIEW_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.VIEW_ELEMENT_TYPE, "SelectableSourceFileListPanel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 8942019071604854112L;

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SelectableSourceFileListView.class);

  /**
   * The sub-panel containing all the components corresponding to the selectable source files.
   */
  private JPanel selectableSourceFilesPanel;

  /**
   * The associated model.
   */
  private SelectableSourceFileListModel model;

  /**
   * The notifiable to associated with selectable source file views.
   */
  private transient ILysNotifiable selectableSourceFileViewNotifiable;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider
   */
  public SelectableSourceFileListView(final ILysActionProvider pActionProvider)
  {
    super(VIEW_TYPE_IDENTIFIER, pActionProvider);
    selectableSourceFilesPanel = null;
    model = null;
    selectableSourceFileViewNotifiable = null;
    build();
  }

  /**
   * Setter of the notifiable to register for all the selectable source file views created in this view.
   *
   * @param pSelectableSourceFileViewNotifiable
   *          the notifiable.
   */
  public void setSelectableSourceFileViewNotifiable(final ILysNotifiable pSelectableSourceFileViewNotifiable)
  {
    selectableSourceFileViewNotifiable = pSelectableSourceFileViewNotifiable;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void associateModel(final ILysModel pModel)
  {
    if (pModel instanceof SelectableSourceFileListModel)
    {
      this.model = (SelectableSourceFileListModel) pModel;
      // Listen to this model's notifications
      this.model.register(this);
    }
    else
    {
      String lMessage = LysMvcLoggingUtil.getMessageAboutModelUnexpectedClass(SelectableSourceFileListModel.class,
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
    if (pSource.getTypeIdentifier() == SelectableSourceFileListModel.MODEL_TYPE_IDENTIFIER)
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
    selectableSourceFilesPanel = new JPanel();
    selectableSourceFilesPanel.setLayout(new BoxLayout(selectableSourceFilesPanel, BoxLayout.Y_AXIS));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    installLabelBorderFor(GuiConstants.LABEL_BORDER_COLOR, GuiConstants.LABEL_BORDER_FONT,
        GuiConstants.SELECTABLE_SOURCE_FILE_LIST_PANEL_BORDER_TEXT);
    // Place the panel in a scroll pane
    JScrollPane lScrollPane = new JScrollPane(selectableSourceFilesPanel,
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
   * Fill the selectable source files panel from the model.
   */
  private void fillFromModel()
  {
    selectableSourceFilesPanel.removeAll();

    SelectableSourceFileView lPanel;

    // Add a component for each file
    for (SelectableSourceFileModel lSsfModel : model.getSelectableSourceFileModelList())
    {
      // Memorize the model
      lPanel = new SelectableSourceFileView(null);
      lPanel.associateModel(lSsfModel);
      // Let the given notifiable listen to the notifications of the created view
      lPanel.register(selectableSourceFileViewNotifiable);
      // Add the panel
      selectableSourceFilesPanel.add(lPanel);
    }

    revalidate();
    repaint();
  }
}
