/**
 * Class: TwentyEightyTwoColumnsPanel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.commons;

import javax.swing.JPanel;

import org.lys.gui.bases.panels.AbstractLysPanel;

/**
 * A shorthand panel that contains 2 sub-panels distributed on 2 columns with 20% - 80%.
 * 
 * @author Yann Leglise
 */
public class TwentyEightyTwoColumnsPanel extends AbstractLysPanel
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 9188110595438238528L;

  /**
   * The left panel (associated with 20%).
   */
  private final JPanel leftPanel;

  /**
   * The left panel (associated with 80%).
   */
  private final JPanel rightPanel;

  /**
   * Constructor.
   * 
   * @param pLeftPanel
   *          the left panel (associated with 20%).
   * @param pRightPanel
   *          the left panel (associated with 80%).
   */
  public TwentyEightyTwoColumnsPanel(final JPanel pLeftPanel, final JPanel pRightPanel)
  {
    super();
    this.leftPanel = pLeftPanel;
    this.rightPanel = pRightPanel;
    build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    // Already provided through constructor
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    layoutComponent(leftPanel, X_POS_01, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_20, Y_WEIGHT_100, FILL_BOTH,
        ANCHOR_LEFT, ELEMENT_T_C_B_R_INSET);
    layoutComponent(rightPanel, X_POS_02, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_80, Y_WEIGHT_100, FILL_BOTH,
        ANCHOR_LEFT, ELEMENT_T_L_B_C_INSET);
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
