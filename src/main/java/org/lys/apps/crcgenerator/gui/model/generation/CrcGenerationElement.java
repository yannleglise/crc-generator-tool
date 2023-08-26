/**
 * Class: CrcGenerationElement.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.generation;

import java.io.File;

/**
 * A simple class to model a source file and its associated target CRC file, including the generation status.
 * 
 * @author Yann Leglise
 */
public class CrcGenerationElement
{

  /**
   * The next available unique identifier.
   */
  private static long NextAvailableIdentifier = 0;

  /**
   * The instance unique identifier.
   */
  private final long identifier;

  /**
   * The CRC file generation status.
   */
  private CrcGenerationStatus status;

  /**
   * Description of the error if the status is failed.
   */
  private String errorDescription;

  /**
   * The source file to process.
   */
  private final File sourceFile;

  /**
   * The destination CRC file.
   */
  private final File destinationFile;

  /**
   * The listener to invoke when the status changes.
   */
  private ICrcGenerationElementStateChangeListener listener;

  /**
   * Constructor.
   * 
   * @param pSourceFile
   *          the source file to process.
   * @param pDestinationFile
   *          the destination CRC file.
   */
  public CrcGenerationElement(final File pSourceFile, final File pDestinationFile)
  {
    identifier = getNextUniqueIdentfier();
    status = CrcGenerationStatus.TODO;
    errorDescription = "";
    this.sourceFile = pSourceFile;
    this.destinationFile = pDestinationFile;
    listener = null;
  }

  /**
   * Provides the next unique identifier.
   * 
   * @return a new unique identifier.
   */
  private static synchronized long getNextUniqueIdentfier()
  {
    return NextAvailableIdentifier++;
  }

  /**
   * Getter of the unique identifier associate with this instance.
   * 
   * @return the instance unique identifier
   */
  public long getIdentifier()
  {
    return identifier;
  }

  /**
   * Getter of the status.
   * 
   * @return the
   */
  public final CrcGenerationStatus getStatus()
  {
    return status;
  }

  /**
   * Setter of the generation status.
   * 
   * @param pStatus
   *          the status to set
   */
  public final void setStatus(final CrcGenerationStatus pStatus)
  {
    this.status = pStatus;
    if (listener != null)
    {
      listener.handleCrcGenerationNewStatus(identifier, this.status);
    }
  }

  /**
   * Getter of the source file.
   * 
   * @return the source file
   */
  public final File getSourceFile()
  {
    return sourceFile;
  }

  /**
   * Getter of the destination file.
   * 
   * @return the destination file
   */
  public final File getDestinationFile()
  {
    return destinationFile;
  }

  /**
   * Setter of the listener for this instance.
   * 
   * @param pListener
   *          the listener to set
   */
  public void setListener(final ICrcGenerationElementStateChangeListener pListener)
  {
    this.listener = pListener;
  }

  /**
   * Indicates whether this instance matches the given identifier.
   * 
   * @param pInstanceIdentifier
   *          the instance identifier to consider
   * @return <code>true</code> if this instance matches this identifier, <code>false</code> if not
   */
  public boolean matches(final long pInstanceIdentifier)
  {
    return identifier == pInstanceIdentifier;
  }

  /**
   * Getter of the error description (significant only if the status is failed).
   * 
   * @return the error description.
   */
  public String getErrorDescription()
  {
    return errorDescription;
  }

  /**
   * Setter of the error description.
   * 
   * @param pErrorDescription
   *          the error description to set
   */
  public void setErrorDescription(final String pErrorDescription)
  {
    this.errorDescription = pErrorDescription;
  }

}
