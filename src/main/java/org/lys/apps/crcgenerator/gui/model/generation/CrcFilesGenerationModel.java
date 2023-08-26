/**
 * Class: CrcFilesGenerationModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.generation;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.lys.apps.crcgenerator.gui.GuiConstants;
import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.AbstractLysModel;

/**
 * Models the CRC files generation.
 * 
 * @author Yann Leglise
 */
public class CrcFilesGenerationModel extends AbstractLysModel
{
  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "CrcFilesGenerationModel");

  /**
   * The unique identifier for the source files list aspect.
   * <p>
   * The aspect data provided by the notification is the new list of source files (a {@link List} of {@link File}).
   * </p>
   */
  public static final long SOURCE_FILE_LIST_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Source files list for CrcFilesGenerationModel");

  /**
   * The unique identifier for the destination directory aspect.
   * <p>
   * The aspect data provided by the notification is the new destination directory (a {@link File}).
   * </p>
   */
  public static final long DESTINATION_DIRECTORY_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Destination directory for CrcFilesGenerationModel");

  /**
   * The unique identifier for the CRC generation element list aspect.
   * <p>
   * The aspect data provided by the notification is the new list of CRC generation elements (a {@link List} of
   * {@link CrcGenerationElement}).
   * </p>
   */
  public static final long CRC_GENERATION_ELEMENT_LIST_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "CRC generation element list for CrcFilesGenerationModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = -7453808411086984343L;

  /**
   * The list of source files.
   */
  private final List<File> sourceFileList;

  /**
   * The destination directory.
   */
  private File destinationDirectory;

  /**
   * The list of CRC generation elements.
   */
  private final transient List<CrcGenerationElement> crcGenerationElementList;

  /**
   * Constructor.
   * 
   */
  public CrcFilesGenerationModel()
  {
    super(MODEL_TYPE_IDENTIFIER);
    sourceFileList = new ArrayList<>();
    crcGenerationElementList = new ArrayList<>();
    destinationDirectory = null;
  }

  /**
   * Set the source files to process.
   * 
   * @param pSourceFiles
   *          the list of source files to process.
   */
  public void setSourceFiles(final List<File> pSourceFiles)
  {
    // Ensure it is not the same
    boolean lIsSame = true;
    if (this.sourceFileList.size() == pSourceFiles.size())
    {

      for (File lFile : this.sourceFileList)
      {
        if (!pSourceFiles.contains(lFile))
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
      sourceFileList.clear();
      sourceFileList.addAll(pSourceFiles);
      notifyAttributeChange(SOURCE_FILE_LIST_ASPECT_IDENTIFIER, (Serializable) sourceFileList);
      // Update the list of CRC generation elements
      updatecCrcGenerationElementList();
    }
  }

  /**
   * Setter of the destination directory.
   * 
   * @param pDestinationDirectory
   *          the destination directory to set
   */
  public void setDestinationDirectory(final File pDestinationDirectory)
  {
    if (this.destinationDirectory != pDestinationDirectory)
    {
      this.destinationDirectory = pDestinationDirectory;
      notifyAttributeChange(DESTINATION_DIRECTORY_ASPECT_IDENTIFIER, pDestinationDirectory);
      // Update the list of CRC generation elements
      updatecCrcGenerationElementList();
    }
  }

  /**
   * Getter of the source file list.
   * 
   * @return the list of source files to process
   */
  public List<File> getSourceFileList()
  {
    return sourceFileList;
  }

  /**
   * Getter of the destination directory.
   * 
   * @return the destination directory.
   */
  public File getDestinationDirectory()
  {
    return destinationDirectory;
  }

  /**
   * Getter of the CRC generation element list.
   * 
   * @return the list of CRC generation elements to process
   */
  public List<CrcGenerationElement> getCrcGenerationElementList()
  {
    return crcGenerationElementList;
  }

  /**
   * Update the list of CRC generation elements, each time the source file list or the destination directory change.
   */
  private void updatecCrcGenerationElementList()
  {
    crcGenerationElementList.clear();
    File lDestinationCrcFile;
    CrcGenerationElement lCrcGenElt;
    for (File lSourceFile : sourceFileList)
    {
      lDestinationCrcFile = new File(destinationDirectory, lSourceFile.getName() + GuiConstants.CRC_FILE_EXTENSION);
      lCrcGenElt = new CrcGenerationElement(lSourceFile, lDestinationCrcFile);
      crcGenerationElementList.add(lCrcGenElt);
    }
    notifyAttributeChange(CRC_GENERATION_ELEMENT_LIST_ASPECT_IDENTIFIER, (Serializable) crcGenerationElementList);
  }

  /**
   * Indicates whether the CRC generation can be launched or not.
   * <p>
   * This is the case if there is at least one element that is not in success.
   * </p>
   * 
   * @return <code>true</code> if the CRC generation can be launched, <code>false</code> if not.
   */
  public boolean requiresAction()
  {
    boolean lRequiresAction = false;

    if (crcGenerationElementList.isEmpty())
    {
      lRequiresAction = false;
    }
    else
    {
      lRequiresAction = false;
      for (CrcGenerationElement lCge : crcGenerationElementList)
      {
        if (lCge.getStatus() != CrcGenerationStatus.SUCCESS)
        {
          lRequiresAction = true;
          break;
        }
      }
    }

    return lRequiresAction;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModelValid()
  {
    return (!crcGenerationElementList.isEmpty()) && (destinationDirectory != null)
        && (destinationDirectory.isDirectory());
  }

}
