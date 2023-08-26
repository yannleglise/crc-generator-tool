/**
 * Class: CrcGenerationException.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.generator;

/**
 * Exception related to CRC generation.
 * 
 * @author Yann Leglise
 */
public class CrcGenerationException extends Exception
{

  /**
   * Serial ID.
   */
  private static final long serialVersionUID = 1655343864521161270L;

  /**
   * Constructor.
   * 
   * @param pMessage
   *          the error message.
   */
  public CrcGenerationException(final String pMessage)
  {
    super(pMessage);
  }
}
