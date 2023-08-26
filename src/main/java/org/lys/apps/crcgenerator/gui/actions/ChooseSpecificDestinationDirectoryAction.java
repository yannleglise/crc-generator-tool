/**
 * Class: ChooseSpecificDestinationDirectoryAction.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.actions;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.gui.bases.actions.AbstractLysAction;
import org.lys.gui.bases.actions.ILysActionListener;
import org.lys.gui.bases.actions.LysActionIdentifierFactory;

/**
 * Action for selecting a custom destination directory.
 * 
 * @author Yann Leglise
 */
public class ChooseSpecificDestinationDirectoryAction extends AbstractLysAction
{

  /**
   * The unique identifier for this action.
   */
  public static final long ACTION_ID = LysActionIdentifierFactory
      .getNewActionIdFor("ChooseSpecificDestinationDirectoryAction");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 2524270525959173798L;

  /**
   * Constructor.
   * 
   * @param pActionListener
   *          the element that wants to be warned when the action is triggered.
   */
  public ChooseSpecificDestinationDirectoryAction(final ILysActionListener pActionListener)
  {
    super(ACTION_ID, pActionListener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionText()
  {
    return GuiConstants.CUSTOM_DESTINATION_DIR_SELECTION_ACTION_TEXT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionTooltip()
  {
    return GuiConstants.CUSTOM_DESTINATION_DIR_SELECTION_ACTION_TOOLTIP;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getMnemonic()
  {
    return GuiConstants.CUSTOM_DESTINATION_DIR_SELECTION_ACTION_MNEMONIC;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionButtonIconFileName()
  {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionMenuItemIconFileName()
  {
    return null;
  }

}
