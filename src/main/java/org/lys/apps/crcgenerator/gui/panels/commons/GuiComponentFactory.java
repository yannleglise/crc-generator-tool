/**
 * Class: GuiComponentFactory.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.commons;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.gui.bases.actions.AbstractLysAction;

/**
 * Factory for GUI components.
 * 
 * @author Yann Leglise
 */
public final class GuiComponentFactory
{

  /**
   * Constructor.
   */
  private GuiComponentFactory()
  {
    // Utility class
  }

  /**
   * Create a new label with the given HTML formatted text.
   * 
   * @param pInternalHtmlText
   *          the HTML formatted text (should not contain the <code>html</code> markups)
   * @return the created label.
   */
  public static JLabel createHtmlFormattedLabel(final String pInternalHtmlText)
  {
    String lHtmlCode = GuiConstants.HTML_OPEN_MARKUP + pInternalHtmlText + GuiConstants.HTML_CLOSE_MARKUP;
    JLabel lLabel = new JLabel(lHtmlCode);
    lLabel.setOpaque(false);
    return lLabel;
  }

  /**
   * Create a new check box with the given HTML formatted text.
   * 
   * @param pInternalHtmlText
   *          the HTML formatted text (should not contain the <code>html</code> markups)
   * @return the created check box.
   */
  public static JCheckBox createHtmlFormattedCheckBox(final String pInternalHtmlText)
  {
    String lHtmlCode = GuiConstants.HTML_OPEN_MARKUP + pInternalHtmlText + GuiConstants.HTML_CLOSE_MARKUP;
    JCheckBox lCheckBox = new JCheckBox(lHtmlCode);
    return lCheckBox;
  }

  /**
   * Create a new radio button with the given HTML formatted text.
   * 
   * @param pInternalHtmlText
   *          the HTML formatted text (should not contain the <code>html</code> markups)
   * @return the created radio button.
   */
  public static JRadioButton createHtmlFormattedRadioButton(final String pInternalHtmlText)
  {
    String lHtmlCode = GuiConstants.HTML_OPEN_MARKUP + pInternalHtmlText + GuiConstants.HTML_CLOSE_MARKUP;
    JRadioButton lRadioButton = new JRadioButton(lHtmlCode);
    return lRadioButton;
  }

  /**
   * Create a new button from the given action, but format it through HTML with characteristics of buttons.
   * 
   * @param pGuiAction
   *          the GUI action.
   * @return the created formatted button.
   */
  public static JButton createHtmlFormattedButton(final AbstractLysAction pGuiAction)
  {
    return new JButton(pGuiAction);
  }

  /**
   * Creates a text field with the given number of columns.
   * 
   * @param pColunms
   *          the number of columns for this input field.
   * @return the text field.
   */
  public static JTextField createTextField(final int pColunms)
  {
    JTextField lTextField = new JTextField(pColunms);
    lTextField.setFont(GuiConstants.USER_INPUT_FONT);

    return lTextField;
  }
}
