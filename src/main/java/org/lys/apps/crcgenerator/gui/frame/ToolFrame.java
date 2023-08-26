/**
 * Class: ToolFrame.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.frame;

import java.awt.Container;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.panels.main.ToolMainPanel;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.bases.data.LysColoredTransparencyCharacteristics;
import org.lys.gui.bases.resources.LysResourcesManager;
import org.lys.gui.components.bases.LysWindowBounds;
import org.lys.gui.components.frame.AbstractLysFrame;

/**
 * The tool frame.
 * 
 * @author Yann Leglise
 */
public class ToolFrame extends AbstractLysFrame
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -7842724866470218781L;

  /**
   * The main panel of the tool.
   */
  private ToolMainPanel mainPanel;

  /**
   * Constructor.
   */
  public ToolFrame()
  {
    super();

    setPreferredSize(GuiConstants.FRAME_PREFERRED_DIMENSION);
    setMinimumSize(GuiConstants.FRAME_PREFERRED_DIMENSION);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainPanel = null;
  }

  /**
   * Getter of the main panel.
   * 
   * @return the main panel.
   */
  public ToolMainPanel getMainPanel()
  {
    return mainPanel;
  }

  /**
   * Actually build the contents pane.
   * 
   * @param pActionProvider
   *          the action provider to use
   */
  public void buildContentPane(final ILysActionProvider pActionProvider)
  {
    mainPanel = new ToolMainPanel(pActionProvider);
    build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Container buildContentPane()
  {
    return mainPanel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LysColoredTransparencyCharacteristics getBlockingGlassPaneCharacteristics()
  {
    return new LysColoredTransparencyCharacteristics(GuiConstants.BGP_BACKGROUND_COLOR, GuiConstants.BGP_ALPHA_VALUE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ImageIcon getFrameIcon()
  {
    return LysResourcesManager.getInstance().getWindowTitleBarIcon("lys-crc64-generator-24.png");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getFrameTitle()
  {
    return GuiConstants.FRAME_TITLE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected LysWindowBounds getInitialBounds()
  {
    return new LysWindowBounds(new Point(0, 0), GuiConstants.FRAME_PREFERRED_DIMENSION);
  }
}
