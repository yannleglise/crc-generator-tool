/**
 * Class: DestinationView.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.destination;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.actions.ChooseSpecificDestinationDirectoryAction;
import org.lys.apps.crcgenerator.gui.model.destination.DestinationModel;
import org.lys.apps.crcgenerator.gui.model.destination.TargetDestinationType;
import org.lys.apps.crcgenerator.gui.panels.commons.GuiComponentFactory;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.aspect.LysAspectType;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.ILysModel;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.lys.gui.mvc.utils.LysMvcLoggingUtil;
import org.lys.gui.mvc.view.AbstractLysView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View for the destination directory.
 * 
 * @author Yann Leglise
 */
public class DestinationView extends AbstractLysView implements ItemListener
{
  /**
   * The unique identifier for this view type.
   */
  public static final long VIEW_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.VIEW_ELEMENT_TYPE, "DestinationView");

  /**
   * The unique identifier for the target destination type aspect.
   * <p>
   * The aspect data provided by the notification is the new target destination type (a {@link TargetDestinationType}).
   * </p>
   */
  public static final long TARGET_DESTINATION_TYPE_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Target destination type for DestinationView");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 3758738306116071882L;

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(DestinationView.class);

  /**
   * The associated model.
   */
  private DestinationModel destinationModel;

  /**
   * The radio button corresponding to the choice of a destination directory corresponding to the same as the input
   * directory.
   */
  private JRadioButton sameDirectoryRb;

  /**
   * The radio button corresponding to the choice of a destination directory corresponding to the CRC director at the
   * same level as the input directory.
   */
  private JRadioButton crcDirectoryRb;

  /**
   * The radio button corresponding to the choice of a destination directory corresponding to a custom directory.
   */
  private JRadioButton customDirectoryRb;

  /**
   * The action for choosing the specific destination directory.
   */
  private final ChooseSpecificDestinationDirectoryAction chooseSpecificDestinationDirectoryAction;

  /**
   * The button to choose the custom destination directory.
   */
  private JButton customDestinationDirectoryChooseButton;

  /**
   * The input field for the target directory.
   */
  private JTextField targetDirectoryIf;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider.
   */
  public DestinationView(final ILysActionProvider pActionProvider)
  {
    super(VIEW_TYPE_IDENTIFIER, pActionProvider);
    destinationModel = null;
    sameDirectoryRb = null;
    crcDirectoryRb = null;
    customDirectoryRb = null;
    customDestinationDirectoryChooseButton = null;
    targetDirectoryIf = null;

    chooseSpecificDestinationDirectoryAction = (ChooseSpecificDestinationDirectoryAction) getActionForId(
        ChooseSpecificDestinationDirectoryAction.ACTION_ID);
    build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void associateModel(final ILysModel pModel)
  {
    if (pModel instanceof DestinationModel)
    {
      destinationModel = (DestinationModel) pModel;
      // Listen to its notifications
      destinationModel.register(this);
    }
    else
    {
      String lMessage = LysMvcLoggingUtil.getMessageAboutModelUnexpectedClass(DestinationModel.class, pModel);
      LOGGER.error(lMessage);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void itemStateChanged(final ItemEvent pItemEvent)
  {
    Object lEventSource = pItemEvent.getSource();
    TargetDestinationType lTdt = null;
    if (lEventSource == sameDirectoryRb)
    {
      lTdt = TargetDestinationType.SAME_DIRECTORY;
    }
    else if (lEventSource == crcDirectoryRb)
    {
      lTdt = TargetDestinationType.CRC_DIRECTORY;
    }
    else if (lEventSource == customDirectoryRb)
    {
      lTdt = TargetDestinationType.CUSTOM_DIRECTORY;
    }
    if ((lTdt != null) && (pItemEvent.getStateChange() == ItemEvent.SELECTED))
    {
      // Notify the change if it is a selection
      notify(TARGET_DESTINATION_TYPE_ASPECT_IDENTIFIER, LysAspectType.VIEW_SELECTION_CHANGED, lTdt);
    }
  }

  /**
   * Get the currently selected target destination type.
   * 
   * @return the selected target destination type, or <code>null</code> if none is selected.
   */
  public TargetDestinationType getSelectedTargetDestinationType()
  {
    TargetDestinationType lSelectedTargetDestinationType;

    if (customDirectoryRb.isSelected())
    {
      lSelectedTargetDestinationType = TargetDestinationType.CUSTOM_DIRECTORY;
    }
    else if (crcDirectoryRb.isSelected())
    {
      lSelectedTargetDestinationType = TargetDestinationType.CRC_DIRECTORY;
    }
    else if (sameDirectoryRb.isSelected())
    {
      lSelectedTargetDestinationType = TargetDestinationType.SAME_DIRECTORY;
    }
    else
    {
      lSelectedTargetDestinationType = null;
    }

    return lSelectedTargetDestinationType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    if (pSource.getTypeIdentifier() == DestinationModel.MODEL_TYPE_IDENTIFIER)
    {
      if (pAspect.getAspectIdentifier() == DestinationModel.TARGET_DESTINATION_TYPE_ASPECT_IDENTIFIER)
      {
        TargetDestinationType lTargetDestinationType = destinationModel.getTargetDestinationType();
        handleTargetDestinationTypeChange(lTargetDestinationType);
      }
      else if (pAspect.getAspectIdentifier() == DestinationModel.TARGET_DIRECTORY_ASPECT_IDENTIFIER)
      {
        File lDestinationDirectory = destinationModel.getTargetDirectory();
        if (lDestinationDirectory == null)
        {
          targetDirectoryIf.setText("");
        }
        else
        {
          targetDirectoryIf.setText(lDestinationDirectory.getAbsolutePath());
          // Depending on whether a CRC directory exists at the same level or not, the associated choice needs to be
          // grayed out or not
          File lCrcDirectory = new File(lDestinationDirectory, GuiConstants.CRC_DIR_RELATIVE_PATH);
          crcDirectoryRb.setEnabled(lCrcDirectory.isDirectory());
        }
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    // Create a button group for mutal exclusion of radio buttons
    ButtonGroup lRbGroup = new ButtonGroup();

    // Create the destination types radio buttons
    sameDirectoryRb = createRadioButton(GuiConstants.DESTINATION_TYPE_SAME_DIR_TEXT,
        GuiConstants.DESTINATION_TYPE_SAME_DIR_TOOLTIP, lRbGroup);
    crcDirectoryRb = createRadioButton(GuiConstants.DESTINATION_TYPE_CRC_DIR_TEXT,
        GuiConstants.DESTINATION_TYPE_CRC_DIR_TOOLTIP, lRbGroup);
    customDirectoryRb = createRadioButton(GuiConstants.DESTINATION_TYPE_CUSTOM_DIR_TEXT,
        GuiConstants.DESTINATION_TYPE_CUSTOM_DIR_TOOLTIP, lRbGroup);

    // Create the button for selecting the custom destination directory
    customDestinationDirectoryChooseButton = GuiComponentFactory
        .createHtmlFormattedButton(chooseSpecificDestinationDirectoryAction);

    // Create the input field for the target directory
    targetDirectoryIf = GuiComponentFactory.createTextField(GuiConstants.PATH_INPUT_FIELD_COLUMN_COUNT);
    targetDirectoryIf.setEditable(false);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    installLabelBorderFor(GuiConstants.LABEL_BORDER_COLOR, GuiConstants.LABEL_BORDER_FONT,
        GuiConstants.DESTINATION_PANEL_BORDER_TEXT);
    // First row: Radio buttons for choosing the destination directory type
    layoutComponent(sameDirectoryRb, X_POS_01, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_40, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_T_L_C_C_INSET);
    layoutComponent(crcDirectoryRb, X_POS_02, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_40, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_T_C_C_C_INSET);
    layoutComponent(customDirectoryRb, X_POS_03, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_20, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_T_C_C_R_INSET);

    // Second row: the target directory input field and the browse button
    layoutComponent(targetDirectoryIf, X_POS_01, Y_POS_02, X_PADDING_02, Y_PADDING_01, X_WEIGHT_80, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_C_L_B_C_INSET);
    layoutComponent(customDestinationDirectoryChooseButton, X_POS_03, Y_POS_02, X_PADDING_01, Y_PADDING_01, X_WEIGHT_20,
        Y_WEIGHT_00, FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_C_C_B_R_INSET);
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
   * Create a radio button with the given characteristics.
   * 
   * @param pLabel
   *          the raw label for the radio button.
   * @param pTooltip
   *          the tooltip for the radio button.
   * @param pButtonGroup
   *          the button group where to add the created radio button.
   * @return the created radio button.
   */
  private JRadioButton createRadioButton(final String pLabel, final String pTooltip, final ButtonGroup pButtonGroup)
  {
    JRadioButton lRadioButton = GuiComponentFactory.createHtmlFormattedRadioButton(pLabel);
    lRadioButton.setToolTipText(pTooltip);

    lRadioButton.addItemListener(this);

    if (pButtonGroup != null)
    {
      pButtonGroup.add(lRadioButton);
    }

    return lRadioButton;
  }

  /**
   * Handle the change of target destination type.
   * 
   * @param pTargetDestinationType
   *          the new target destination type.
   */
  private void handleTargetDestinationTypeChange(final TargetDestinationType pTargetDestinationType)
  {
    boolean lChooseCustomDirBtEnabled = false;

    switch (pTargetDestinationType)
    {
      case CUSTOM_DIRECTORY:
        if (!customDirectoryRb.isSelected())
        {
          customDirectoryRb.setSelected(true);
        }
        lChooseCustomDirBtEnabled = true;
        break;
      case SAME_DIRECTORY:
        if (!sameDirectoryRb.isSelected())
        {
          sameDirectoryRb.setSelected(true);
        }
        break;
      case CRC_DIRECTORY:
        if (!crcDirectoryRb.isSelected())
        {
          crcDirectoryRb.setSelected(true);
        }
        break;
      default:
        break;
    }

    chooseSpecificDestinationDirectoryAction.setEnabled(lChooseCustomDirBtEnabled);
  }
}
