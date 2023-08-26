/**
 * Class: SourceFilesController.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.controllers.source;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.lys.apps.crcgenerator.gui.actions.ChooseSourceFilesDirectoryAction;
import org.lys.apps.crcgenerator.gui.controllers.common.AbstractSubController;
import org.lys.apps.crcgenerator.gui.controllers.generation.CrcFileGenerationController;
import org.lys.apps.crcgenerator.gui.frame.ToolFrame;
import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFileListModel;
import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFileModel;
import org.lys.apps.crcgenerator.gui.model.source.SelectableSourceFilesExtensionModel;
import org.lys.apps.crcgenerator.gui.model.source.SourceFileExtensionListModel;
import org.lys.apps.crcgenerator.gui.model.source.SourceFilesModel;
import org.lys.apps.crcgenerator.gui.panels.source.SelectableSourceFileExtensionView;
import org.lys.apps.crcgenerator.gui.panels.source.SelectableSourceFileListView;
import org.lys.apps.crcgenerator.gui.panels.source.SelectableSourceFileView;
import org.lys.apps.crcgenerator.gui.panels.source.SourceFileExtensionListView;
import org.lys.apps.crcgenerator.gui.panels.source.SourceFilesView;
import org.lys.gui.bases.actions.ILysAction;
import org.lys.gui.mvc.aspect.ILysAspect;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.notifications.ILysNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller related to source files.
 * 
 * @author Yann Leglise
 */
public class SourceFilesController extends AbstractSubController
{
  /**
   * The unique identifier for this controller type.
   */
  public static final long CONTROLLER_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.CONTROLLER_ELEMENT_TYPE, "SourceFilesController");

  /**
   * The class logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SourceFilesController.class);

  /**
   * Regular expression representing a filename with an extension.
   * <p>
   * It contains a group corresponding to the extension (without the dot) when it matches.
   * </p>
   */
  private static final String FILENAME_WITH_EXTENSION_REGEXP = ".*\\.([^.]+)";

  /**
   * Pattern associated with {@link #FILENAME_WITH_EXTENSION_REGEXP}.
   */
  private static final Pattern FILENAME_WITH_EXTENSION_PATTERN = Pattern.compile(FILENAME_WITH_EXTENSION_REGEXP);

  /**
   * The source file model.
   */
  private final SourceFilesModel sourceFileModel;

  /**
   * The source files extensions model.
   */
  private final SourceFileExtensionListModel sourceFileExtensionListModel;

  /**
   * The selected source files model.
   */
  private final SelectableSourceFileListModel selectableSourceFileListModel;

  /**
   * The action to choose the source files directory.
   */
  private final ChooseSourceFilesDirectoryAction chooseSourceFilesDirectoryAction;

  /**
   * Constructor.
   * 
   * @param pToolFrame
   *          the associated tool frame.
   * @param pMainController
   *          the main controller
   */
  public SourceFilesController(final ToolFrame pToolFrame, final CrcFileGenerationController pMainController)
  {
    super(CONTROLLER_TYPE_IDENTIFIER, pToolFrame, pMainController);

    // Initialize the models
    sourceFileModel = new SourceFilesModel();
    sourceFileExtensionListModel = new SourceFileExtensionListModel();
    selectableSourceFileListModel = new SelectableSourceFileListModel();
    // Create the action to choose the directory for source files
    chooseSourceFilesDirectoryAction = new ChooseSourceFilesDirectoryAction(this);
  }

  /**
   * Getter of the choose source files directory action.
   *
   * @return the choose source files directory action
   */
  public ChooseSourceFilesDirectoryAction getChooseSourceFilesDirectoryAction()
  {
    return chooseSourceFilesDirectoryAction;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void start()
  {
    LOGGER.debug("Start of the source files controller");

    SourceFilesView lSourceFilesPanel = getToolFrame().getMainPanel().getSourceFilesView();
    // Set its model
    lSourceFilesPanel.associateModel(sourceFileModel);

    SourceFileExtensionListView lSourceFileExtensionListPanel = lSourceFilesPanel.getSourceFileExtensionListView();
    // Set its model
    lSourceFileExtensionListPanel.associateModel(sourceFileExtensionListModel);
    // Set the reference to this controller
    lSourceFileExtensionListPanel.setSelectableSourceFileExtensionViewNotifiable(this);

    // Register this controller to notifications of the source files view
    lSourceFilesPanel.register(this);

    // Let the selectable source file panel listen to the associated model
    SelectableSourceFileListView lSourceFileListPanel = lSourceFilesPanel.getSelectableSourceFileListView();
    // Set its model
    lSourceFileListPanel.associateModel(selectableSourceFileListModel);
    // Set the reference to this controller
    lSourceFileListPanel.setSelectableSourceFileViewNotifiable(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void stop()
  {
    // Nothing to do
    LOGGER.debug("Stop of the source files controller");
  }

  /**
   * Set the source directory.
   * 
   * @param pSourceDirectory
   *          the chosen source directory.
   */
  public void setSourceDirectory(final File pSourceDirectory)
  {
    sourceFileModel.setSourceDirectory(pSourceDirectory);
    // Update the source file extension model
    updateSourceFileExtensionModel(pSourceDirectory);
  }

  /**
   * Update the source file extension model for the given source directory.
   * 
   * @param pSourceDirectory
   *          the source directory to consider.
   */
  public void updateSourceFileExtensionModel(final File pSourceDirectory)
  {
    // First detect the list of (unique) file extensions
    Set<String> lExtensionSet = new HashSet<>();
    if ((pSourceDirectory != null) && (pSourceDirectory.isDirectory()))
    {
      String lElementName;
      String lFileExtension;
      Matcher lElementNameMatcher;
      for (File lElement : pSourceDirectory.listFiles())
      {
        if (lElement.isFile())
        {
          lElementName = lElement.getName();
          lElementNameMatcher = FILENAME_WITH_EXTENSION_PATTERN.matcher(lElementName);
          if (lElementNameMatcher.matches())
          {
            lFileExtension = lElementNameMatcher.group(1);
            lExtensionSet.add(lFileExtension);
          }
        }
      }
    }

    // Order the extensions alphabetically
    List<String> lOrderedExtensionList = new ArrayList<>(lExtensionSet);
    Collections.sort(lOrderedExtensionList);

    // Build the list of new extension models
    List<SelectableSourceFilesExtensionModel> lNewExtensionModelList = new ArrayList<>();
    SelectableSourceFilesExtensionModel lModel;
    for (String lFileExtension : lOrderedExtensionList)
    {
      lModel = new SelectableSourceFilesExtensionModel(lFileExtension);
      lNewExtensionModelList.add(lModel);
    }

    sourceFileExtensionListModel.setExtensionModelList(lNewExtensionModelList);

    // Clear the existing selected files
    selectableSourceFileListModel.setSelectableSourceFileModelList(new ArrayList<>());
    sourceFileModel.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleNotification(final ILysNotifier pSource, final ILysAspect pAspect, final Object pAspectData)
  {
    if ((pSource.getTypeIdentifier() == SourceFilesView.VIEW_TYPE_IDENTIFIER)
        && (pAspect.getAspectIdentifier() == SourceFilesView.SOURCE_DIRECTORY_ASPECT_IDENTIFIER))
    {
      File lSelectedSourceDirectory = getToolFrame().getMainPanel().getSourceFilesView().getSelectedSourceDirectory();
      setSourceDirectory(lSelectedSourceDirectory);
    }
    else if ((pSource.getTypeIdentifier() == SelectableSourceFileExtensionView.VIEW_TYPE_IDENTIFIER)
        && (pAspect.getAspectIdentifier() // cs
            == SelectableSourceFileExtensionView.SELECTABLE_SOURCE_FILE_MODEL_LIST_ASPECT_IDENTIFIER))
    {
      if (pSource instanceof SelectableSourceFileExtensionView)
      {
        SelectableSourceFileExtensionView lSourcePanel = (SelectableSourceFileExtensionView) pSource;
        boolean lExtensionIsSelected = lSourcePanel.isSelected();
        SelectableSourceFilesExtensionModel lModel = lSourcePanel.getModel();
        handleSourceFileExtensionSelectionChange(lModel, lExtensionIsSelected);
      }
    }
    else if ((pSource.getTypeIdentifier() == SelectableSourceFileView.VIEW_TYPE_IDENTIFIER) && (pAspect
        .getAspectIdentifier() == SelectableSourceFileView.SELECTABLE_SOURCE_FILE_MODEL_LIST_ASPECT_IDENTIFIER))
    {
      SelectableSourceFileView lSourcePanel = (SelectableSourceFileView) pSource;
      boolean lFileIsSelected = lSourcePanel.isSelected();
      SelectableSourceFileModel lModel = lSourcePanel.getModel();
      handleSourceFileSelectionChanged(lModel, lFileIsSelected);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleAction(final ILysAction pSourceAction)
  {
    if (pSourceAction.getActionIdentifier() == ChooseSourceFilesDirectoryAction.ACTION_ID)
    {
      // Let the view ask the user to select the directory
      getToolFrame().getMainPanel().getSourceFilesView().doSelectSourceFilesDirectory();
    }
  }

  /**
   * Called when a selection of source file extensions has been performed.
   * 
   * @param pModel
   *          the selectable source files extension model
   * @param pIsSelected
   *          <code>true</code> if the extension becomes selected, <code>false</code> if it becomes unselected.
   */
  private void handleSourceFileExtensionSelectionChange(final SelectableSourceFilesExtensionModel pModel,
      final boolean pIsSelected)
  {
    // Update the model
    pModel.setSelected(pIsSelected);

    // If we change a source file extension selection, we reinitialize the selected source files
    sourceFileModel.clear();

    // Update the list of selectable source file extensions
    List<File> lCandidateSourceFiles = getFilesMatchingSelectedExtensions();

    // Sort the elements through their file name
    Collections.sort(lCandidateSourceFiles, new Comparator<File>()
    {

      @Override
      public int compare(final File pF1, final File pF2)
      {

        return pF1.getName().compareTo(pF2.getName());
      }
    });

    // Create the list of associated selectable soruce file models
    List<SelectableSourceFileModel> lSelectableSourceFileModelList = new ArrayList<>();

    SelectableSourceFileModel lSubModel;
    for (File lCandidateFile : lCandidateSourceFiles)
    {
      lSubModel = new SelectableSourceFileModel(lCandidateFile);
      lSelectableSourceFileModelList.add(lSubModel);
    }

    // Update the model of selectable source files
    selectableSourceFileListModel.setSelectableSourceFileModelList(lSelectableSourceFileModelList);

    // Selected source files is reset and none of them is selected
    // Update for the main controller
    getMainController().setSourceFiles(sourceFileModel.getSelectedSourceFiles());
  }

  /**
   * Called when the selection of a source file has been performed.
   * 
   * @param pModel
   *          the selectable source file model
   * @param pFileIsSelected
   *          <code>true</code> if the file becomes selected, <code>false</code> if it becomes unselected.
   */
  private void handleSourceFileSelectionChanged(final SelectableSourceFileModel pModel, final boolean pFileIsSelected)
  {
    // Update the model
    pModel.setSelected(pFileIsSelected);

    // Update a source file model level
    List<File> lSelectedSourceFiles = new ArrayList<>();
    for (SelectableSourceFileModel lSelectableSourceFileModel : selectableSourceFileListModel
        .getSelectableSourceFileModelList())
    {
      if (lSelectableSourceFileModel.isSelected())
      {
        lSelectedSourceFiles.add(lSelectableSourceFileModel.getSourceFile());
      }
    }
    sourceFileModel.setSelectedSourceFiles(lSelectedSourceFiles);

    // Update for the main controller
    getMainController().setSourceFiles(lSelectedSourceFiles);
  }

  /**
   * Computes the list of files from the source directory that match the selected file extensions.
   * 
   * @return the list of candidate source files
   */
  private List<File> getFilesMatchingSelectedExtensions()
  {
    List<File> lCandidateSourceFileList = new ArrayList<>();

    // Select the files from the source directory that match the extension
    File lSourceFileDirectory = sourceFileModel.getSourceDirectory();
    if (lSourceFileDirectory != null)
    {

      String lExtension;
      for (SelectableSourceFilesExtensionModel lModel : sourceFileExtensionListModel.getExtensionModelList())
      {
        if (lModel.isSelected())
        {
          // Iterate on the source directory
          lExtension = lModel.getExtension();
          fillFilesMatchingExension(lExtension, lSourceFileDirectory, lCandidateSourceFileList);
        }
      }
    }

    return lCandidateSourceFileList;
  }

  /**
   * Add the files from the source directory matching the given extension.
   * 
   * @param pExtension
   *          the extension to consider (without the dot).
   * @param pSourceFileDirectory
   *          the source directory (not <code>null</code>)
   * @param pCandidateSourceFileList
   *          the list where to add matching files.
   */
  private void fillFilesMatchingExension(final String pExtension, final File pSourceFileDirectory,
      final List<File> pCandidateSourceFileList)
  {
    // Iterate on the source directory
    for (File lElement : pSourceFileDirectory.listFiles())
    {
      if (lElement.isFile() && (lElement.getName().endsWith(pExtension)))
      {
        pCandidateSourceFileList.add(lElement);
      }
    }
  }

  /**
   * Getter of the source file model.
   * 
   * @return the source file model
   */
  public SourceFilesModel getSourceFileModel()
  {
    return sourceFileModel;
  }
}
