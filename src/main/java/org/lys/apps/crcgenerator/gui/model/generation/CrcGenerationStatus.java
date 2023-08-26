/**
 * Class: CrcGenerationStatus.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.generation;

/**
 * The possible stages for a CRC file generation.
 * 
 * @author Yann Leglise
 */
public enum CrcGenerationStatus
{

  /**
   * Not started yet.
   */
  TODO,

  /**
   * The generation has started but it is not finished yet.
   */
  PENDING,

  /**
   * The generation ended in success.
   */
  SUCCESS,

  /**
   * The generation ended in error.
   */
  FAILED

}
