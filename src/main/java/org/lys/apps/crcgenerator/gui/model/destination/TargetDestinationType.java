/**
 * Class: TargetDestinationType.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.model.destination;

/**
 * The possible choices for the target destination.
 * 
 * @author Yann Leglise
 */
public enum TargetDestinationType
{
  /**
   * The destination files will be placed in the same directory as the source directory.
   */
  SAME_DIRECTORY,

  /**
   * The destination files will be placed in the CRC directory at the same level as the source directory (available only
   * if such a directory exists).
   */
  CRC_DIRECTORY,

  /**
   * The destination files will be placed in a directory chosen by the user.
   */
  CUSTOM_DIRECTORY

  ;
}
