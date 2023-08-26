/**
 * Class: SourceFilesModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.source;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.AbstractLysModel;

/**
 * Model for the input files.
 * 
 * @author Yann Leglise
 */
public class SourceFilesModel extends AbstractLysModel
{
  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "SourceFilesModel");

  /**
   * The unique identifier for the source directory aspect.
   * <p>
   * The aspect data provided by the notification is the new source directory (a {@link File}).
   * </p>
   */
  public static final long SOURCE_DIRECTORY_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Source directory for SourceFilesModel");

  /**
   * The unique identifier for the selected source files aspect.
   * <p>
   * The aspect data provided by the notification is the list of selected source files (a {@link List} of {@link File}).
   * </p>
   */
  public static final long SELECTED_SOURCE_FILES_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Selected source files for SourceFilesModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -449137699999579889L;

  /**
   * The source directory.
   */
  private File sourceDirectory;

  /**
   * The list of selected source files.
   */
  private final List<File> selectedSourceFiles;

  /**
   * Constructor.
   */
  public SourceFilesModel()
  {
    super(MODEL_TYPE_IDENTIFIER);
    sourceDirectory = null;
    selectedSourceFiles = new ArrayList<>();
  }

  /**
   * Set the source directory.
   * 
   * @param pNewSourceDirectory
   *          the new source directory.
   */
  public void setSourceDirectory(final File pNewSourceDirectory)
  {
    if ((pNewSourceDirectory != sourceDirectory))
    {
      sourceDirectory = pNewSourceDirectory;
      notifyAttributeChange(SOURCE_DIRECTORY_ASPECT_IDENTIFIER, sourceDirectory);
    }
  }

  /**
   * Getter of the source directory.
   * 
   * @return the source directory.
   */
  public File getSourceDirectory()
  {
    return sourceDirectory;
  }

  /**
   * Remove all the selected files.
   */
  public void clear()
  {
    setSelectedSourceFiles(new ArrayList<>());
  }

  /**
   * Set the selected source files.
   * 
   * @param pNewSelectedSourceFiles
   *          the new selected source files.
   */
  public void setSelectedSourceFiles(final List<File> pNewSelectedSourceFiles)
  {
    // Ensure it is not the same
    boolean lIsSame = true;
    if (this.selectedSourceFiles.size() == pNewSelectedSourceFiles.size())
    {

      for (File lFile : this.selectedSourceFiles)
      {
        if (!pNewSelectedSourceFiles.contains(lFile))
        {
          lIsSame = false;
          break;
        }
      }
    }
    else
    {
      lIsSame = false;
    }

    if (!lIsSame)
    {
      selectedSourceFiles.clear();
      selectedSourceFiles.addAll(pNewSelectedSourceFiles);
      notifyAttributeChange(SELECTED_SOURCE_FILES_ASPECT_IDENTIFIER, (Serializable) selectedSourceFiles);
    }
  }

  /**
   * Getter of the selected source files.
   * 
   * @return the selected source files.
   */
  public List<File> getSelectedSourceFiles()
  {
    return selectedSourceFiles;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModelValid()
  {
    return (sourceDirectory != null) && (sourceDirectory.isDirectory());
  }

}
