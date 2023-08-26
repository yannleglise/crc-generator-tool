/**
 * Class: AbstractController.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.controllers.common;

import org.lys.apps.crcgenerator.gui.frame.ToolFrame;
import org.lys.gui.bases.actions.ILysActionListener;
import org.lys.gui.mvc.controller.AbstractLysController;

/**
 * Base class for a controller associated with a tool frame.
 * 
 * @author Yann Leglise
 */
public abstract class AbstractController extends AbstractLysController implements ILysActionListener
{

  /**
   * The tool frame.
   */
  private final ToolFrame toolFrame;

  /**
   * Constructor.
   * 
   * @param pControllerTypeIdentifier
   *          the controller type unique identifier (one per class).
   * @param pToolFrame
   *          the tool frame.
   */
  protected AbstractController(final long pControllerTypeIdentifier, final ToolFrame pToolFrame)
  {
    super(pControllerTypeIdentifier);
    this.toolFrame = pToolFrame;
  }

  /**
   * Getter of the tool frame.
   * 
   * @return the tool frame.
   */
  public ToolFrame getToolFrame()
  {
    return toolFrame;
  }

}
