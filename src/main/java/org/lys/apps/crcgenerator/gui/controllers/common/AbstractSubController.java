/**
 * Class: AbstractSubController.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.controllers.common;

import org.lys.apps.crcgenerator.gui.controllers.generation.CrcFileGenerationController;
import org.lys.apps.crcgenerator.gui.frame.ToolFrame;

/**
 * Sub controller common superclass.
 * 
 * <p>
 * Holds a reference to the main controller.
 * </p>
 * 
 * @author Yann Leglise
 */
public abstract class AbstractSubController extends AbstractController
{

  /**
   * Reference to the main controller.
   */
  private final CrcFileGenerationController mainController;

  /**
   * Constructor.
   * 
   * @param pControllerTypeIdentifier
   *          the controller type unique identifier (one per class).
   * @param pToolFrame
   *          the associated tool frame.
   * @param pMainController
   *          the main controller.
   */
  protected AbstractSubController(final long pControllerTypeIdentifier, final ToolFrame pToolFrame,
      final CrcFileGenerationController pMainController)
  {
    super(pControllerTypeIdentifier, pToolFrame);
    this.mainController = pMainController;
  }

  /**
   * Getter of the main controller.
   * 
   * @return the main controller.
   */
  public CrcFileGenerationController getMainController()
  {
    return mainController;
  }

}
