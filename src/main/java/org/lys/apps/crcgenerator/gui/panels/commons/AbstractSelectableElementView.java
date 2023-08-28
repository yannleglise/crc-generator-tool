/**
 * Class: AbstractSelectableElementPanel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.commons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.mvc.view.AbstractLysView;

/**
 * View representing an element that can be selected or unselected through a check box.
 * 
 * @author Yann Leglise
 */
public abstract class AbstractSelectableElementView extends AbstractLysView implements ActionListener
{
  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 8622752096559347387L;

  /**
   * The check box allowing to select/unselect the element.
   */
  private JCheckBox selectionCb;

  /**
   * Constructor.
   * 
   * @param pViewTypeIdentifier
   *          the unique identifier of the view type (one per class).
   * @param pActionProvider
   *          the action provider.
   */
  protected AbstractSelectableElementView(final long pViewTypeIdentifier, final ILysActionProvider pActionProvider)
  {
    super(pViewTypeIdentifier, pActionProvider);
  }

  /**
   * Short hand to know whether the selection check box is selected or not.
   * 
   * @return <code>true</code> if the check box is selected, <code>false</code> if not.
   */
  public boolean isSelected()
  {
    return selectionCb.isSelected();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void actionPerformed(final ActionEvent pActionEvent)
  {
    if (pActionEvent.getSource() == selectionCb)
    {
      // Get the current selection state
      boolean lIsSelected = selectionCb.isSelected();
      // Set it at model level
      handleSelectionChange(lIsSelected);
    }
  }

  /**
   * Let the subclass provide the label for the check box.
   * 
   * @return the HTML formatted label to use for the checkbox.
   */
  protected abstract String getCheckBoxLabel();

  /**
   * Let the subclass handle the selection change.
   * 
   * @param pIsElementSelected
   *          <code>true</code> if the element is selected, <code>false</code> if it is not.
   */
  protected abstract void handleSelectionChange(boolean pIsElementSelected);

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    String lFormattedCheckboxLabel = GuiConstants.FILENAME_HTML_FORMAT_START + getCheckBoxLabel()
        + GuiConstants.FILENAME_HTML_FORMAT_STOP;
    selectionCb = GuiComponentFactory.createHtmlFormattedCheckBox(lFormattedCheckboxLabel);
    selectionCb.addActionListener(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    layoutComponent(selectionCb, X_POS_01, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_C_C_C_C_INSET);
  }

  /**
   * Update the label of the check box from the model.
   */
  protected final void updateSelectionCbLabelFromModel()
  {
    String lUpdatedLabel = GuiConstants.HTML_OPEN_MARKUP + getCheckBoxLabel() + GuiConstants.HTML_CLOSE_MARKUP;
    selectionCb.setText(lUpdatedLabel);
  }

}
