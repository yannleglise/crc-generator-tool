/**
 * Class: ICrcGenerationElementStateChangeListener.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.generation;

/**
 * Interface to implement by any entity wanting to be informed about the changes of status of a CRC generation element.
 * 
 * @author Yann Leglise
 */
public interface ICrcGenerationElementStateChangeListener
{
  /**
   * Called whenever the status of a CRC generation element changes.
   * 
   * @param pInstanceIdentifier
   *          the unique identifier of the instance whose state has changed
   * @param pNewStatus
   *          the new status for that instance.
   */
  void handleCrcGenerationNewStatus(long pInstanceIdentifier, CrcGenerationStatus pNewStatus);
}
