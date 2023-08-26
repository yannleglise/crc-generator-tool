/**
 * Class: DestinationModel.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.destination;

import java.io.File;

import org.lys.gui.mvc.identification.LysIdentifiedItemTypeCategory;
import org.lys.gui.mvc.identification.LysItemTypeIdentifierFactory;
import org.lys.gui.mvc.model.AbstractLysModel;

/**
 * Models the destination of generated files.
 * 
 * @author Yann Leglise
 */
public class DestinationModel extends AbstractLysModel
{

  /**
   * The unique identifier for this model type.
   */
  public static final long MODEL_TYPE_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.MODEL_ELEMENT_TYPE, "DestinationModel");

  /**
   * The unique identifier for the target destination type aspect.
   * <p>
   * The aspect data provided by the notification is the new target destination type (a {@link TargetDestinationType}).
   * </p>
   */
  public static final long TARGET_DESTINATION_TYPE_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Target destination type for DestinationModel");

  /**
   * The unique identifier for the target directory aspect.
   * <p>
   * The aspect data provided by the notification is the new target directory (a {@link File}).
   * </p>
   */
  public static final long TARGET_DIRECTORY_ASPECT_IDENTIFIER = LysItemTypeIdentifierFactory
      .getNewIdFor(LysIdentifiedItemTypeCategory.ASPECT, "Target directory for DestinationModel");

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 2576890440736256989L;

  /**
   * The target destination type.
   */
  private TargetDestinationType targetDestinationType;

  /**
   * The target directory.
   */
  private File targetDirectory;

  /**
   * Constructor.
   */
  public DestinationModel()
  {
    super(MODEL_TYPE_IDENTIFIER);
    targetDestinationType = null;
    targetDirectory = null;
  }

  /**
   * Setter of the target destination type.
   * 
   * @param pNewTargetDestinationType
   *          the target destination type to set
   */
  public void setTargetDestinationType(final TargetDestinationType pNewTargetDestinationType)
  {
    if (targetDestinationType != pNewTargetDestinationType)
    {
      // Update the attribute
      targetDestinationType = pNewTargetDestinationType;
      // Notify the change
      notifyAttributeChange(TARGET_DESTINATION_TYPE_ASPECT_IDENTIFIER, pNewTargetDestinationType);
    }
  }

  /**
   * Setter of the target directory.
   * 
   * @param pNewTargetDirectory
   *          the target directory to set
   */
  public void setTargetDirectory(final File pNewTargetDirectory)
  {
    if (targetDirectory != pNewTargetDirectory)
    {
      // Update the attribute
      targetDirectory = pNewTargetDirectory;
      // Notify the change
      notifyAttributeChange(TARGET_DIRECTORY_ASPECT_IDENTIFIER, targetDirectory);
    }
  }

  /**
   * Getter of the target directory.
   * 
   * @return the target directory.
   */
  public File getTargetDirectory()
  {
    return targetDirectory;
  }

  /**
   * Getter of the target destination type.
   * 
   * @return the target destination type.
   */
  public TargetDestinationType getTargetDestinationType()
  {
    return targetDestinationType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModelValid()
  {
    return (targetDestinationType != null) && (targetDirectory != null) && (targetDirectory.isDirectory());
  }
}
