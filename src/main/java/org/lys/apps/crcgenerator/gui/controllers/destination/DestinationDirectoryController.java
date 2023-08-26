/**
 * Class: DestinationDirectoryController.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.controllers.destination;

import java.io.File;
import java.nio.file.Paths;

import javax.swing.JFileChooser;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.actions.ChooseSpecificDestinationDirectoryAction;
import org.lys.apps.crcgenerator.gui.controllers.common.AbstractSubController;
import org.lys.apps.crcgenerator.gui.controllers.generation.CrcFileGenerationController;
import org.lys.apps.crcgenerator.gui.frame.ToolFrame;
import org.lys.apps.crcgenerator.gui.model.destination.DestinationModel;
import org.lys.apps.crcgenerator.gui.model.destination.TargetDestinationType;
import org.lys.apps.crcgenerator.gui.model.source.SourceFilesModel;
import org.lys.apps.crcgenerator.gui.panels.destination.DestinationView;
import org.lys.apps.crcgenerator.utils.PathUtils;
import org.lys.gui.bases.actions.ILysAction;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller related for destination directory.
 * 
 * @author Yann Leglise
 */
public class DestinationDirectoryController extends AbstractSubController
{

  /**
   * The unique identifier for this controller type.
   */
  public static final long CONTROLLER_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.CONTROLLER_ELEMENT_TYPE, "DestinationDirectoryController");

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(DestinationDirectoryController.class);

  /**
   * The destination model.
   */
  private final DestinationModel destinationModel;

  /**
   * The source directory.
   */
  private File sourceDirectory;

  /**
   * The custom destination directory chosen by the user.
   */
  private File customDestinationDirectory;

  /**
   * The action to choose the specific destination directory.
   */
  private final ChooseSpecificDestinationDirectoryAction chooseSpecificDestinationDirectoryAction;

  /**
   * Constructor.
   *
   * @param pToolFrame
   *          the associated tool frame.
   * @param pMainController
   *          the main controller
   */
  public DestinationDirectoryController(final ToolFrame pToolFrame, final CrcFileGenerationController pMainController)
  {
    super(CONTROLLER_TYPE_IDENTIFIER, pToolFrame, pMainController);
    // Create the model
    destinationModel = new DestinationModel();

    sourceDirectory = null;
    customDestinationDirectory = null;

    // Create the action to choose the specific destination directory
    chooseSpecificDestinationDirectoryAction = new ChooseSpecificDestinationDirectoryAction(this);
  }

  /**
   * Getter of the choose specific destination directory action.
   * 
   * @return the action for choosing a specific destination directory
   */
  public ChooseSpecificDestinationDirectoryAction getChooseSpecificDestinationDirectoryAction()
  {
    return chooseSpecificDestinationDirectoryAction;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleAction(final ILysAction pSourceAction)
  {
    if (pSourceAction.getActionIdentifier() == ChooseSpecificDestinationDirectoryAction.ACTION_ID)
    {
      doSelectCustomDestinationDirectory();
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start()
  {
    LOGGER.debug("Start of the destination directory controller");

    DestinationView lDestinationView = getToolFrame().getMainPanel().getDestinationView();

    // Set its model
    lDestinationView.associateModel(destinationModel);

    // This controller also needs to be notified when some elements from the destination model change
    destinationModel.register(this);

    // Register to the panel notifications
    lDestinationView.register(this);

    // Set the default value at model level
    destinationModel.setTargetDestinationType(TargetDestinationType.SAME_DIRECTORY);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop()
  {
    // Nothing to do
    LOGGER.debug("Stop of the destination directory controller");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    if ((pSource.getTypeIdentifier() == DestinationView.VIEW_TYPE_IDENTIFIER)
        && (pAspect.getAspectIdentifier() == DestinationView.TARGET_DESTINATION_TYPE_ASPECT_IDENTIFIER))
    {
      TargetDestinationType lTargetDestinationType = getToolFrame().getMainPanel().getDestinationView()
          .getSelectedTargetDestinationType();
      if (lTargetDestinationType != null)
      {
        // Update the model
        destinationModel.setTargetDestinationType(lTargetDestinationType);
        // Update the destination directory
        updateDestinationDirectory(lTargetDestinationType);
      }
    }
    else if ((pSource.getTypeIdentifier() == SourceFilesModel.MODEL_TYPE_IDENTIFIER)
        && (pAspect.getAspectIdentifier() == SourceFilesModel.SOURCE_DIRECTORY_ASPECT_IDENTIFIER))
    {
      sourceDirectory = (File) pAspectData;
      updateDestinationDirectory(destinationModel.getTargetDestinationType());
    }
  }

  /**
   * Update the destination directory depending on the target destination type.
   * 
   * @param pTargetDestinationType
   *          the current target destination type.
   */
  private void updateDestinationDirectory(final TargetDestinationType pTargetDestinationType)
  {
    File lDestinationDirectory = null;
    switch (pTargetDestinationType)
    {
      case SAME_DIRECTORY:
        lDestinationDirectory = sourceDirectory;
        break;
      case CRC_DIRECTORY:
        File lCrcDirectory = new File(sourceDirectory, GuiConstants.CRC_DIR_RELATIVE_PATH);
        if (lCrcDirectory.isDirectory())
        {
          File lNormalizedCrcDir = PathUtils.normalize(lCrcDirectory);
          lDestinationDirectory = lNormalizedCrcDir;
        }
        break;
      case CUSTOM_DIRECTORY:
        lDestinationDirectory = customDestinationDirectory;
        break;
      default:
        break;
    }
    if (lDestinationDirectory != null)
    {
      destinationModel.setTargetDirectory(lDestinationDirectory);

      // Update for the main controller
      getMainController().setDestinationDirectory(lDestinationDirectory);
    }
  }

  /**
   * Let the user select the custom destination directory.
   */
  private void doSelectCustomDestinationDirectory()
  {
    // Create the file chooser
    JFileChooser lFileChooser = new JFileChooser();

    // Configure it to allow only one directory selection
    lFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    File lRootDirectory = Paths.get(System.getProperty("user.home")).getRoot().toFile();
    lFileChooser.setCurrentDirectory(lRootDirectory);
    lFileChooser.setDialogTitle(GuiConstants.CUSTOM_DESTINATION_DIR_SELECTION_CHOOSER_DIALOG_TITLE);

    int lReturnVal = lFileChooser.showOpenDialog(getToolFrame());

    if (lReturnVal == JFileChooser.APPROVE_OPTION)
    {
      customDestinationDirectory = lFileChooser.getSelectedFile();
      updateDestinationDirectory(TargetDestinationType.CUSTOM_DIRECTORY);
    }
  }
}
