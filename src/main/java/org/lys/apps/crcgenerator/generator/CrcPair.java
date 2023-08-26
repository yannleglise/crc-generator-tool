/**
 * Class: CrcPair.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.generator;

/**
 * A pair of high and low parts of a CRC.
 * 
 * @author Yann Leglise
 */
public class CrcPair
{

  /**
   * The high value.
   */
  private final int highValue;

  /**
   * The low value.
   */
  private final int lowValue;

  /**
   * Constructor.
   * 
   * @param pHighValue
   *          the high value of the CRC.
   * @param pLowValue
   *          the low value of the CRC.
   */
  public CrcPair(final int pHighValue, final int pLowValue)
  {
    this.highValue = pHighValue;
    this.lowValue = pLowValue;
  }

  /**
   * Getter of the high value.
   * 
   * @return the CRC high value.
   */
  public int getHighValue()
  {
    return highValue;
  }

  /**
   * Getter of the low value.
   * 
   * @return the CRC low value.
   */
  public int getLowValue()
  {
    return lowValue;
  }

  /**
   * Get the CRC as a hexadecimal string representation (on 16 characters).
   * 
   * @return the CRC hexadecimal representation
   */
  public String toHexFormat()
  {
    return String.format("%08X%08X", highValue, lowValue);
  }
}
