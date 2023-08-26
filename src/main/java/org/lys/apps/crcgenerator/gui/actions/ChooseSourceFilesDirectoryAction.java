/**
 * Class: ChooseSourceFilesDirectoryAction.
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
 * Action for selecting the source files directory.
 * 
 * @author Yann Leglise
 */
public class ChooseSourceFilesDirectoryAction extends AbstractLysAction
{
  /**
   * The unique identifier for this action.
   */
  public static final long ACTION_ID = LysActionIdentifierFactory.getNewActionIdFor("ChooseSourceFilesDirectoryAction");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -2992300832840969135L;

  /**
   * Constructor.
   * 
   * @param pActionListener
   *          the element that wants to be warned when the action is triggered.
   */
  public ChooseSourceFilesDirectoryAction(final ILysActionListener pActionListener)
  {
    super(ACTION_ID, pActionListener);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionText()
  {
    return GuiConstants.SOURCE_FILES_DIR_SELECTION_ACTION_TEXT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionTooltip()
  {
    return GuiConstants.SOURCE_FILES_DIR_SELECTION_ACTION_TOOLTIP;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getMnemonic()
  {
    return GuiConstants.SOURCE_FILES_DIR_SELECTION_ACTION_MNEMONIC;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionButtonIconFileName()
  {
    // Not used
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionMenuItemIconFileName()
  {
    // Not used
    return null;
  }

}
