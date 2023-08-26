/**
 * Class: CrcFileGenerationController.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.controllers.generation;

import java.io.File;
import java.util.List;

import org.lys.apps.crcgenerator.gui.actions.CrcGenerationStartAction;
import org.lys.apps.crcgenerator.gui.controllers.common.AbstractController;
import org.lys.apps.crcgenerator.gui.frame.ToolFrame;
import org.lys.apps.crcgenerator.gui.model.generation.CrcFilesGenerationModel;
import org.lys.gui.bases.actions.ILysAction;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller managing the CRC file generation.
 * 
 * @author Yann Leglise
 */
public class CrcFileGenerationController extends AbstractController
{
  /**
   * The unique identifier for this controller type.
   */
  public static final long CONTROLLER_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.CONTROLLER_ELEMENT_TYPE, "CrcFileGenerationController");

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CrcFileGenerationController.class);

  /**
   * The related model.
   */
  private final CrcFilesGenerationModel crcFilesGenerationModel;

  /**
   * The action to start the CRC generation.
   */
  private final CrcGenerationStartAction crcGenerationStartAction;

  /**
   * Constructor.
   * 
   * @param pToolFrame
   *          the associated tool frame.
   */
  public CrcFileGenerationController(final ToolFrame pToolFrame)
  {
    super(CONTROLLER_TYPE_IDENTIFIER, pToolFrame);

    // Create the model
    crcFilesGenerationModel = new CrcFilesGenerationModel();

    // Create the action for starting the CRC file generation
    crcGenerationStartAction = new CrcGenerationStartAction(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start()
  {
    LOGGER.debug("Start of CRC file generation controller");
    // Associate the model to the view
    getToolFrame().getMainPanel().getCrcFileGenerationView().associateModel(crcFilesGenerationModel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop()
  {
    LOGGER.debug("Stop of CRC file generation controller");

  }

  /**
   * Getter of the CRC generation start action.
   *
   * @return the CRC generation start action
   */
  public CrcGenerationStartAction getCrcGenerationStartAction()
  {
    return crcGenerationStartAction;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    // Nothing to do
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleAction(final ILysAction pSourceAction)
  {
    if (pSourceAction.getActionIdentifier() == CrcGenerationStartAction.ACTION_ID)
    {
      doStartCrcFileGeneration();
    }
  }

  /**
   * Set the source files to process.
   * 
   * @param pSourceFiles
   *          the source files to process.
   */
  public void setSourceFiles(final List<File> pSourceFiles)
  {
    // Update the model
    crcFilesGenerationModel.setSourceFiles(pSourceFiles);
  }

  /**
   * Set the destination directory.
   * 
   * @param pDestinationDirectory
   *          the destination directory to set.
   */
  public void setDestinationDirectory(final File pDestinationDirectory)
  {
    // Update the model
    crcFilesGenerationModel.setDestinationDirectory(pDestinationDirectory);
  }

  /**
   * Called when the CRC file generation task has ended.
   */
  public void handleEndOfCrcFilesGeneration()
  {
    // Deactivate the glass pane on the frame to allow user actions anew
    getToolFrame().activateBlockingGlassPane(false);

    // Update the availability of the generate action
    getToolFrame().getMainPanel().getCrcFileGenerationView().updateStartActionAvailability();
  }

  /**
   * Start the CRC file generation process.
   */
  private void doStartCrcFileGeneration()
  {
    // Activate the glass pane on the frame to prevent user actions
    getToolFrame().activateBlockingGlassPane(true);

    CrcFileGenerationWorker lWorker = new CrcFileGenerationWorker(this, crcFilesGenerationModel);
    lWorker.execute();
  }

}
