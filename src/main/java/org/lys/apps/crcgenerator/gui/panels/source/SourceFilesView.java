/**
 * Class: SourceFilesView.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.panels.source;

import java.io.File;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.apps.crcgenerator.gui.actions.ChooseSourceFilesDirectoryAction;
import org.lys.apps.crcgenerator.gui.model.source.SourceFilesModel;
import org.lys.apps.crcgenerator.gui.panels.commons.GuiComponentFactory;
import org.lys.apps.crcgenerator.gui.panels.commons.TwentyEightyTwoColumnsPanel;
import org.lys.gui.bases.actions.ILysActionProvider;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.aspect.LysAspectType;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.ILysModel;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.lys.gui.mvc.utils.LysMvcLoggingUtil;
import org.lys.gui.mvc.view.AbstractLysView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * View for the input files.
 * 
 * @author Yann Leglise
 */
public class SourceFilesView extends AbstractLysView
{
  /**
   * The unique identifier for this view type.
   */
  public static final long VIEW_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.VIEW_ELEMENT_TYPE, "SourceFilesView");

  /**
   * The unique identifier for the source directory aspect.
   * <p>
   * The aspect data provided by the notification is the new source directory (a {@link File}).
   * </p>
   */
  public static final long SOURCE_DIRECTORY_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Source directory for SourceFilesView");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -4779777226744211924L;

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SourceFilesView.class);

  /**
   * The input field for the source directory.
   */
  private JTextField sourceDirectoryIf;

  /**
   * The action to choose the source files directory.
   */
  private final ChooseSourceFilesDirectoryAction chooseSourceFilesDirectoryAction;

  /**
   * The button to choose the source directory.
   */
  private JButton sourceDirectoryChooseButton;

  /**
   * The view for the filtering of source file extensions.
   */
  private SourceFileExtensionListView sourceFileExtensionListView;

  /**
   * The view for the candidate source files.
   */
  private SelectableSourceFileListView selectableSourceFileListView;

  /**
   * The source file model.
   */
  private SourceFilesModel sourceFileModel;

  /**
   * The source directory selected by the user through the file chooser.
   */
  private File selectedSourceDirectory;

  /**
   * Constructor.
   * 
   * @param pActionProvider
   *          the action provider
   */
  public SourceFilesView(final ILysActionProvider pActionProvider)
  {
    super(VIEW_TYPE_IDENTIFIER, pActionProvider);
    sourceFileModel = null;
    sourceDirectoryIf = null;
    sourceDirectoryChooseButton = null;
    sourceFileExtensionListView = null;
    selectableSourceFileListView = null;
    selectedSourceDirectory = null;

    chooseSourceFilesDirectoryAction = (ChooseSourceFilesDirectoryAction) getActionForId(
        ChooseSourceFilesDirectoryAction.ACTION_ID);
    build();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void associateModel(final ILysModel pModel)
  {
    if (pModel instanceof SourceFilesModel)
    {
      sourceFileModel = (SourceFilesModel) pModel;
      // Listen to this model notifications
      sourceFileModel.register(this);
    }
    else
    {
      String lMessage = LysMvcLoggingUtil.getMessageAboutModelUnexpectedClass(SourceFilesModel.class, pModel);
      LOGGER.error(lMessage);
    }
  }

  /**
   * Let the user select the source files directory.
   */
  public void doSelectSourceFilesDirectory()
  {
    // Create the file chooser
    JFileChooser lFileChooser = new JFileChooser();

    // Configure it to allow only one directory selection
    lFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    File lRootDirectory;

    // Use the previously selected directory if defined, or the user directory otherwise
    if (selectedSourceDirectory == null)
    {
      lRootDirectory = Paths.get(System.getProperty("user.home")).getRoot().toFile();
    }
    else
    {
      lRootDirectory = selectedSourceDirectory;
    }
    lFileChooser.setCurrentDirectory(lRootDirectory);
    lFileChooser.setDialogTitle(GuiConstants.SOURCE_FILES_DIR_SELECTION_CHOOSER_DIALOG_TITLE);

    int lReturnVal = lFileChooser.showOpenDialog(this);

    if (lReturnVal == JFileChooser.APPROVE_OPTION)
    {
      // Memorize the selected directory
      selectedSourceDirectory = lFileChooser.getSelectedFile();
      // Notify
      notify(SOURCE_DIRECTORY_ASPECT_IDENTIFIER, LysAspectType.VIEW_VALUE_CHANGED, selectedSourceDirectory);
    }
  }

  /**
   * Getter of the source file extension list view.
   *
   * @return the source file extension list view
   */
  public SourceFileExtensionListView getSourceFileExtensionListView()
  {
    return sourceFileExtensionListView;
  }

  /**
   * Getter of the selectable source file list view.
   *
   * @return the selectable source file list view
   */
  public SelectableSourceFileListView getSelectableSourceFileListView()
  {
    return selectableSourceFileListView;
  }

  /**
   * Getter of the selected source directory.
   *
   * @return the selected source directory
   */
  public File getSelectedSourceDirectory()
  {
    return selectedSourceDirectory;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    if (pSource.getTypeIdentifier() == SourceFilesModel.MODEL_TYPE_IDENTIFIER)
    {
      File lSourceDir = sourceFileModel.getSourceDirectory();
      if (lSourceDir == null)
      {
        sourceDirectoryIf.setText("");
      }
      else
      {
        sourceDirectoryIf.setText(lSourceDir.getAbsolutePath());
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createComponents()
  {
    // Create the input field for the source directory
    sourceDirectoryIf = GuiComponentFactory.createTextField(GuiConstants.PATH_INPUT_FIELD_COLUMN_COUNT);
    sourceDirectoryIf.setEditable(false);

    // Create the button for choosing the source files directory
    sourceDirectoryChooseButton = GuiComponentFactory.createHtmlFormattedButton(chooseSourceFilesDirectoryAction);

    // Create the source file extension filtering panel
    sourceFileExtensionListView = new SourceFileExtensionListView(null);

    // Create the candidate source files panel
    selectableSourceFileListView = new SelectableSourceFileListView(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void layoutPanel()
  {
    installLabelBorderFor(GuiConstants.LABEL_BORDER_COLOR, GuiConstants.LABEL_BORDER_FONT,
        GuiConstants.SOURCE_PANEL_BORDER_TEXT);
    // First row: Source directory label (100%)
    JLabel lSourceDirLabel = GuiComponentFactory
        .createHtmlFormattedLabel(GuiConstants.SOURCE_PANEL_INPUT_FILES_DIR_LABEL_TEXT);
    layoutComponent(lSourceDirLabel, X_POS_01, Y_POS_01, X_PADDING_04, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_T_L_C_R_INSET);

    // Second row: Source directory input field (90%) and choose button (10%)
    layoutComponent(sourceDirectoryIf, X_POS_01, Y_POS_02, X_PADDING_03, Y_PADDING_01, X_WEIGHT_90, Y_WEIGHT_00,
        FILL_HORIZONTAL, ANCHOR_LEFT, ELEMENT_C_L_C_R_INSET);
    layoutComponent(sourceDirectoryChooseButton, X_POS_04, Y_POS_02, X_PADDING_01, Y_PADDING_01, X_WEIGHT_10,
        Y_WEIGHT_00, FILL_NONE, ANCHOR_RIGHT, ELEMENT_C_C_C_R_INSET);

    // Third row: Source file extension filtering panel (20%), and selectable source files panel (80%)
    TwentyEightyTwoColumnsPanel lTePanel = new TwentyEightyTwoColumnsPanel(sourceFileExtensionListView,
        selectableSourceFileListView);
    layoutComponent(lTePanel, X_POS_01, Y_POS_03, X_PADDING_04, Y_PADDING_01, X_WEIGHT_100, Y_WEIGHT_20, FILL_BOTH,
        ANCHOR_LEFT, ELEMENT_C_L_B_R_INSET);
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
