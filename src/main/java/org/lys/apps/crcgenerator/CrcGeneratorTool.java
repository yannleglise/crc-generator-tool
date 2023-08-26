/**
 * Class: CrcGeneratorTool.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator;

import javax.naming.ConfigurationException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.lys.apps.crcgenerator.gui.controllers.destination.DestinationDirectoryController;
import org.lys.apps.crcgenerator.gui.controllers.generation.CrcFileGenerationController;
import org.lys.apps.crcgenerator.gui.controllers.source.SourceFilesController;
import org.lys.apps.crcgenerator.gui.frame.ToolFrame;
import org.lys.commons.bases.exceptions.interceptable.LysLogInitializationException;
import org.lys.commons.bases.logging.LysLoggingInitializer;
import org.lys.commons.bases.messages.LysCommonsBasesMessageBundleInitializer;
import org.lys.commons.constants.LysEnvironmentConstants;
import org.lys.commons.utils.messages.LysGuiUtilsMessageBundleInitializer;
import org.lys.gui.bases.actions.LysActionProvider;
import org.lys.gui.bases.messages.LysGuiBasesMessageBundleInitializer;
import org.lys.gui.components.messages.LysGuiComponentsMessageBundleInitializer;
import org.lys.gui.mvc.messages.LysGuiMvcMessageBundleInitializer;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * Main class to launch the tool.
 * 
 * @author Yann Leglise
 */
public class CrcGeneratorTool
{

  /**
   * The tool frame.
   */
  private final ToolFrame toolFrame;

  /**
   * The CRC file generation controller.
   */
  private final CrcFileGenerationController crcFileGenerationController;

  /**
   * The source file controller.
   */
  private final SourceFilesController sourceFileController;

  /**
   * The destination directory controller.
   */
  private final DestinationDirectoryController destinationDirectoryController;

  /**
   * Constructor.
   */
  public CrcGeneratorTool()
  {
    toolFrame = new ToolFrame();
    crcFileGenerationController = new CrcFileGenerationController(toolFrame);
    sourceFileController = new SourceFilesController(toolFrame, crcFileGenerationController);
    destinationDirectoryController = new DestinationDirectoryController(toolFrame, crcFileGenerationController);
  }

  /**
   * Start the tool.
   */
  public void start()
  {

    LysActionProvider lActionProvider = new LysActionProvider();
    lActionProvider.addAction(crcFileGenerationController.getCrcGenerationStartAction());
    lActionProvider.addAction(sourceFileController.getChooseSourceFilesDirectoryAction());
    lActionProvider.addAction(destinationDirectoryController.getChooseSpecificDestinationDirectoryAction());

    // Let the frame build its contents pane
    toolFrame.buildContentPane(lActionProvider);

    // Start the controllers
    sourceFileController.start();
    destinationDirectoryController.start();
    crcFileGenerationController.start();

    /**
     * The changes from the source file model needs to be monitored by the destination directory controller
     */
    sourceFileController.getSourceFileModel().register(destinationDirectoryController);

    // Display the frame
    SwingUtilities.invokeLater(new Runnable()
    {

      @Override
      public void run()
      {
        toolFrame.setVisible(true);
        toolFrame.center();
        handleInitialization();
      }
    });

  }

  /**
   * Called after the frame is made visible for the first time.
   */
  public void handleInitialization()
  {
    sourceFileController.start();
    destinationDirectoryController.start();
    crcFileGenerationController.start();
  }

  /**
   * Launches the GUI tool.
   * 
   * @param pArgs
   *          the command line arguments
   */
  public static void main(final String[] pArgs)
  {
    // Initialize the used projects
    try
    {
      System.setProperty(LysEnvironmentConstants.LOG_DIR_ENV_VAR_NAME, System.getenv().get("TEMP"));
      System.setProperty(LysEnvironmentConstants.LOG_CONFIG_FILE_ENV_VAR_NAME, "crc-generator-tool_logback.xml");
      LysLoggingInitializer.initializeLogging();
      LysCommonsBasesMessageBundleInitializer.getInstance().initializeMessageBundles();
      LysGuiUtilsMessageBundleInitializer.getInstance().initializeMessageBundles();
      LysGuiBasesMessageBundleInitializer.getInstance().initializeMessageBundles();
      LysGuiMvcMessageBundleInitializer.getInstance().initializeMessageBundles();
      LysGuiComponentsMessageBundleInitializer.getInstance().initializeMessageBundles();

      // Install the LAF
      UIManager.setLookAndFeel(new FlatDarkLaf());

      // Start this application
      CrcGeneratorTool lTool = new CrcGeneratorTool();
      lTool.start();
    }
    catch (ConfigurationException e)
    {
      System.err.println("Fix this configuration error: " + e.toString()); // NOSONAR as this is likely to be a problem
                                                                           // preventing the logging system to be fine
    }
    catch (LysLogInitializationException e)
    {
      System.err.println("Fix this error: " + e.toString()); // NOSONAR as linked with the logging system
    }
    catch (UnsupportedLookAndFeelException e)
    {
      System.err.println("Look and feel does not work (make sure to have java 8): " // NOSONAR as installation problem
          + e.toString());
    }
  }

}
