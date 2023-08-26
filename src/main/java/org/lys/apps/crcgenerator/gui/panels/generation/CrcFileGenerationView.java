/**
 * Class: CrcFileGenerationView.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.generation;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.actions.CrcGenerationStartAction;
import org.lys.apps.crcgenerator.gui.model.generation.CrcFilesGenerationModel;
import org.lys.apps.crcgenerator.gui.panels.generation.table.CrcFileGenerationTable;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.ILysModel;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.lys.gui.mvc.utils.LysMvcLoggingUtil;
import org.lys.gui.mvc.view.AbstractLysView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View displaying the CRC generation elements.
 * 
 * @author Yann Leglise
 */
public class CrcFileGenerationView extends AbstractLysView
{
  /**
   * The unique identifier for this view type.
   */
  public static final long VIEW_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.VIEW_ELEMENT_TYPE, "CrcFileGenerationView");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -1980679166868541480L;

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CrcFileGenerationView.class);

  /**
   * The CRC file generation table.
   */
  private CrcFileGenerationTable crcFileGenerationTable;

  /**
   * The associated model.
   */
  private CrcFilesGenerationModel model;

  /**
   * The action for the CRC generation start.
   */
  private final CrcGenerationStartAction crcGenerationStartAction;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider
   */
  public CrcFileGenerationView(final ILysActionProvider pActionProvider)
  {
    super(VIEW_TYPE_IDENTIFIER, pActionProvider);
    crcFileGenerationTable = null;
    model = null;

    crcGenerationStartAction = (CrcGenerationStartAction) getActionForId(CrcGenerationStartAction.ACTION_ID);
    build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void associateModel(final ILysModel pModel)
  {
    if (pModel instanceof CrcFilesGenerationModel)
    {
      this.model = (CrcFilesGenerationModel) pModel;
      // Listen to the model notifications
      this.model.register(this);
    }
    else
    {
      String lMessage = LysMvcLoggingUtil.getMessageAboutModelUnexpectedClass(CrcFilesGenerationModel.class, pModel);
      LOGGER.error(lMessage);
    }
  }

  /**
   * Update the availability of the CRC file generation action depending on the model state.
   */
  public void updateStartActionAvailability()
  {
    crcGenerationStartAction.setEnabled(model.requiresAction());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    if (pSource.getTypeIdentifier() == CrcFilesGenerationModel.MODEL_TYPE_IDENTIFIER)
    {
      crcFileGenerationTable.fillFrom(model);

      // Update the action for generating the CRC files
      updateStartActionAvailability();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    crcFileGenerationTable = new CrcFileGenerationTable();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    // Insert a frame
    installLabelBorderFor(GuiConstants.LABEL_BORDER_COLOR, GuiConstants.LABEL_BORDER_FONT,
        GuiConstants.CRC_GENERATION_PANEL_BORDER_TEXT);

    // Create the button for starting the generation
    JButton lStartCrcGenerationButton = new JButton(crcGenerationStartAction);

    layoutComponent(lStartCrcGenerationButton, X_POS_01, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_50, Y_WEIGHT_00,
        FILL_NONE, ANCHOR_CENTER, ELEMENT_T_L_C_R_INSET);

    // Insert the table in a scroll pane
    JScrollPane lScrollPane = new JScrollPane(crcFileGenerationTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
        ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    layoutComponent(lScrollPane, X_POS_01, Y_POS_02, X_PADDING_01, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_100, FILL_BOTH,
        ANCHOR_LEFT, ELEMENT_C_L_B_R_INSET);
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
