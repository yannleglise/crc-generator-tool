/**
 * Class: ToolMainPanel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.main;

import org.lys.apps.crcgenerator.gui.panels.destination.DestinationView;
import org.lys.apps.crcgenerator.gui.panels.generation.CrcFileGenerationView;
import org.lys.apps.crcgenerator.gui.panels.source.SourceFilesView;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.bases.panels.AbstractLysPanel;

/**
 * The main panel of the tool.
 * 
 * @author Yann Leglise
 */
public class ToolMainPanel extends AbstractLysPanel
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 2174844473439306360L;

  /**
   * The view for the source files.
   */
  private SourceFilesView sourceFilesView;

  /**
   * The view for the destination directory.
   */
  private DestinationView destinationView;

  /**
   * The view for the CRC generation items.
   */
  private CrcFileGenerationView crcFileGenerationView;

  /**
   * The action provider.
   */
  private final ILysActionProvider actionProvider;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider.
   */
  public ToolMainPanel(final ILysActionProvider pActionProvider)
  {
    super();
    actionProvider = pActionProvider;
    sourceFilesView = null;
    destinationView = null;
    crcFileGenerationView = null;

    build();
  }

  /**
   * Getter of the source files view.
   * 
   * @return the source files view.
   */
  public SourceFilesView getSourceFilesView()
  {
    return sourceFilesView;
  }

  /**
   * Getter of the destination view.
   * 
   * @return the destination view.
   */
  public DestinationView getDestinationView()
  {
    return destinationView;
  }

  /**
   * Getter of the CRC file generation view.
   * 
   * @return the CRC file generation view
   */
  public CrcFileGenerationView getCrcFileGenerationView()
  {
    return crcFileGenerationView;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    sourceFilesView = new SourceFilesView(actionProvider);
    destinationView = new DestinationView(actionProvider);
    crcFileGenerationView = new CrcFileGenerationView(actionProvider);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    layoutComponent(sourceFilesView, X_POS_01, Y_POS_01, X_PADDING_01, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_40,
        FILL_BOTH, ANCHOR_LEFT, ELEMENT_T_L_C_R_INSET);
    layoutComponent(destinationView, X_POS_01, Y_POS_02, X_PADDING_01, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_10,
        FILL_BOTH, ANCHOR_LEFT, ELEMENT_C_L_C_R_INSET);
    layoutComponent(crcFileGenerationView, X_POS_01, Y_POS_03, X_PADDING_01, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_50,
        FILL_BOTH, ANCHOR_LEFT, ELEMENT_C_L_B_R_INSET);
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
