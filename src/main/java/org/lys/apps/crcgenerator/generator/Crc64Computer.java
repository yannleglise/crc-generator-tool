/**
 * Class: Crc64Computer.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.generator;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Java implementation of CRC 64 (ISO 3309).
 * <p>
 * Based on:
 * </p>
 * <ul>
 * <li><a href=
 * "https://en.wikipedia.org/wiki/Cyclic_redundancy_check">https://en.wikipedia.org/wiki/Cyclic_redundancy_check</a>
 * <li><a href= "https://github.com/blievrouw/crc64iso/blob/master/crc64iso/crc64iso.py">Python CRC64 ISO
 * implementation</a>
 * </ul>
 * 
 * <p>
 * Used polynomial is <b>x<sup>64</sup> + x<sup>4</sup> + x<sup>3</sup> + x<sup>1</sup> + 1</b>. We assume we always
 * work on big-Endian.
 * </p>
 * 
 * @author Yann Leglise
 */
public final class Crc64Computer
{

  /**
   * The length of the CRC table.
   * <p>
   * This is <code>256</code> as we process the data byte per byte, so we have <code>2<sup>8</sup></code> possible
   * values to consider.
   * </p>
   */
  private static final int CRC_TABLE_LENGTH = 256;

  /**
   * Value representing the reversed polynomial.
   * <p>
   * The polynomial is x^16+x^12+x^5+1 = (1) 0001 0000 0010 0001 = 0x1021 <b>x<sup>64</sup> + x<sup>4</sup> +
   * x<sup>3</sup> + x<sup>1</sup> + 1</b> is
   * <code>(1) 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0001 1011</code> (i.e.
   * <code>0x00000001B</code>).
   * </p>
   * 
   * <p>
   * <code>1101 1000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 (1)</code> (i.e.
   * <code>0x00000001B</code>).
   * </p>
   */
  private static final long CRC_64_POLYNOMIAL_REVERSE_VALUE = 0xD8000000L;

  /**
   * Bit toggle for a 32-bit value.
   */
  private static final long BIT_TOGGLE_32_BITS = 0x00000001L << 31;

  /**
   * The unique class instance.
   */
  private static Crc64Computer Instance = null;

  /**
   * The CRC table to help computing the CRC 64 value.
   * <p>
   * It contains the higher word parts.
   * </p>
   */
  private final long[] crcTableForHigherWords;

  /**
   * The CRC table to help computing the CRC 64 value.
   * <p>
   * It contains the lower word parts.
   * </p>
   */
  private final long[] crcTableForLowerWords;

  /**
   * Constructor.
   */
  private Crc64Computer()
  {
    // Instantiate the CRC tables
    crcTableForHigherWords = new long[CRC_TABLE_LENGTH];
    crcTableForLowerWords = new long[CRC_TABLE_LENGTH];

    // Initialize their values
    initializeCrcTables();
  }

  /**
   * Getter of the unique class instance.
   * 
   * @return the unique class instance.
   */
  public static synchronized Crc64Computer getInstance()
  {
    if (Instance == null)
    {
      Instance = new Crc64Computer();
    }
    return Instance;
  }

  /**
   * Initialize the values of the CRC table.
   */
  private void initializeCrcTables()
  {

    // Initialize all the values to 0
    for (int i = 0; i < CRC_TABLE_LENGTH; i++)
    {
      crcTableForHigherWords[i] = 0L;
      crcTableForLowerWords[i] = 0L;
    }

    long lLowerPart;
    long lHigherPart;
    boolean lReverseFlag;
    for (int i = 0; i < CRC_TABLE_LENGTH; i++)
    {

      lLowerPart = i;
      lHigherPart = 0;
      for (int j = 0; j < 8; j++)
      {
        lReverseFlag = (lLowerPart & 1) != 0;
        lLowerPart = lLowerPart >>> 1;
        if ((lHigherPart & 1) == 1)
        {
          lLowerPart = lLowerPart ^ BIT_TOGGLE_32_BITS;
        }
        lHigherPart = lHigherPart >>> 1;
        if (lReverseFlag)
        {
          lHigherPart = lHigherPart ^ CRC_64_POLYNOMIAL_REVERSE_VALUE;
        }
      }
      crcTableForHigherWords[i] = lHigherPart;
      crcTableForLowerWords[i] = lLowerPart;
    }
  }

  /**
   * Computes the CRC 64 hexadecimal representation for the given input string.
   * 
   * @param pInputString
   *          the input string.
   * @return the hexadecimal representation (on 16 digits) of the computed CRC 64.
   * @throws CrcGenerationException
   *           if an error occurs during the process
   */
  public String computeCrc64For(final String pInputString) throws CrcGenerationException
  {
    String lCrcStr = null;
    if (pInputString == null)
    {
      throw new CrcGenerationException("Provided input string parameter is null");
    }
    else
    {
      ByteArrayInputStream lBais = new ByteArrayInputStream(pInputString.getBytes());

      try
      {
        CrcPair lCrcPair = computeCrc64Pair(lBais, new CrcPair(0, 0));
        lCrcStr = lCrcPair.toHexFormat();
      }
      catch (IOException ioe)
      {
        throw new CrcGenerationException("Could not read input string: " + ioe.getMessage());
      }
    }
    return lCrcStr;
  }

  /**
   * Computes the CRC 64 hexadecimal representation for the given source file.
   * 
   * @param pSourceFile
   *          the source file to consider.
   * @return the hexadecimal representation (on 16 digits) of the computed CRC 64.
   * 
   * @throws CrcGenerationException
   *           if an error occurs during the process
   */
  public String computeCrc64For(final File pSourceFile) throws CrcGenerationException
  {

    String lSrcStr = null;
    if (pSourceFile == null)
    {
      throw new CrcGenerationException("Provided file parameter is null");
    }
    else
    {

      try (FileInputStream fis = new FileInputStream(pSourceFile))
      {
        CrcPair lCrcPair = computeCrc64Pair(fis, new CrcPair(0, 0));
        lSrcStr = lCrcPair.toHexFormat();
      }
      catch (FileNotFoundException fnfe)
      {
        throw new CrcGenerationException("Could not find file " + pSourceFile.getAbsolutePath());
      }
      catch (IOException ioe)
      {
        throw new CrcGenerationException(
            "Could not read file " + pSourceFile.getAbsolutePath() + ": " + ioe.getMessage());
      }
    }
    return lSrcStr;
  }

  /**
   * Computes a 64-bit CRC as two 32-bit integers given the bytes to checksum.
   * 
   * @param pByteInputStream
   *          the input stream on the bytes to checksum.
   * @param pSrcPair
   *          the current CRC pair to work with.
   * @return the newly computed CRC pair.
   * @throws IOException
   *           if an error occurs during the process
   */
  private CrcPair computeCrc64Pair(final InputStream pByteInputStream, final CrcPair pSrcPair) throws IOException
  {

    int lCrcHighPart = pSrcPair.getHighValue();
    int lSrcLowPart = pSrcPair.getLowValue();
    int lReadRc = 0;
    byte lReadByte;
    int lShr;
    int lTmp1High;
    int lTmp1Low;
    int lTableIndex;
    while (lReadRc != -1)
    {
      lReadRc = pByteInputStream.read();
      if (lReadRc == -1)
      {
        break;
      }
      lReadByte = (byte) lReadRc;

      lShr = (lCrcHighPart & 0xFF) << 24;

      lTmp1High = (lCrcHighPart >>> 8);

      lTmp1Low = (lSrcLowPart >>> 8) | lShr;

      lTableIndex = (lSrcLowPart ^ lReadByte) & 0xFF;

      lCrcHighPart = (int) (lTmp1High ^ crcTableForHigherWords[lTableIndex]);
      lSrcLowPart = (int) (lTmp1Low ^ crcTableForLowerWords[lTableIndex]);
    }

    return new CrcPair(lCrcHighPart, lSrcLowPart);
  }

}
