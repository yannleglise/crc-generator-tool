/**
 * Class: CrcGenerationStartAction.
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
 * Action for starting the CRC generation.
 * 
 * @author Yann Leglise
 */
public class CrcGenerationStartAction extends AbstractLysAction
{
  /**
   * The unique identifier for this action.
   */
  public static final long ACTION_ID = LysActionIdentifierFactory.getNewActionIdFor("CrcGenerationStartAction");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -2592648861893688605L;

  /**
   * Constructor.
   * 
   * @param pActionListener
   *          the action listener, that will be called when the action is triggered.
   */
  public CrcGenerationStartAction(final ILysActionListener pActionListener)
  {
    super(ACTION_ID, pActionListener);
    // Disable by default
    setEnabled(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionText()
  {
    return GuiConstants.CRC_GENERATION_START_ACTION_TEXT;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getActionTooltip()
  {
    return GuiConstants.CRC_GENERATION_START_ACTION_TOOLTIP;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int getMnemonic()
  {
    return GuiConstants.CRC_GENERATION_START_ACTION_MNEMONIC;
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
