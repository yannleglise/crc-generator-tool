/**
 * Class: PathUtils.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class for paths.
 * 
 * @author Yann Leglise
 */
public final class PathUtils
{
  /**
   * Constructor.
   */
  private PathUtils()
  {
    // Utility class
  }

  /**
   * Normalize the given file path.
   * 
   * <p>
   * If the given file path would be <code>/c/foo/../CRC</code> then we would return <code>/c/CRC</code>
   * </p>
   * 
   * @param pFilePath
   *          the file path to normalize (must not be <code>null</code>).
   * @return the normalized file path.
   */
  public static File normalize(final File pFilePath)
  {
    Path lPath = Paths.get(pFilePath.getAbsolutePath());
    Path lNormalizedPath = lPath.normalize();

    return lNormalizedPath.toFile();
  }
}
