/**
 * Class: CrcFileGenerationWorker.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui.controllers.generation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.SwingWorker;

import org.lys.apps.crcgenerator.generator.Crc64Computer;
import org.lys.apps.crcgenerator.generator.CrcGenerationException;
import org.lys.apps.crcgenerator.gui.model.generation.CrcFilesGenerationModel;
import org.lys.apps.crcgenerator.gui.model.generation.CrcGenerationElement;
import org.lys.apps.crcgenerator.gui.model.generation.CrcGenerationStatus;

/**
 * A worker to perform the CRC file generation.
 * 
 * @author Yann Leglise
 */
public class CrcFileGenerationWorker extends SwingWorker<Void, Void>
{
  /**
   * The CRC file generation controller.
   */
  private final CrcFileGenerationController controller;

  /**
   * The CRC files generation model.
   */
  private final CrcFilesGenerationModel crcFilesGenerationModel;

  /**
   * Constructor.
   * 
   * @param pController
   *          the CRC files generation controller.
   * @param pCrcFilesGenerationModel
   *          the CRC files generation model to process.
   */
  public CrcFileGenerationWorker(final CrcFileGenerationController pController,
      final CrcFilesGenerationModel pCrcFilesGenerationModel)
  {
    super();
    this.controller = pController;
    this.crcFilesGenerationModel = pCrcFilesGenerationModel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Void doInBackground() throws Exception
  {

    // Iterate on CRC file generation elements
    for (CrcGenerationElement lCge : crcFilesGenerationModel.getCrcGenerationElementList())
    {
      // Only process elements that are not already successful
      if (lCge.getStatus() != CrcGenerationStatus.SUCCESS)
      {
        processCrcFileGeneration(lCge);
      }
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void done()
  {
    // Notify the end to the controller
    controller.handleEndOfCrcFilesGeneration();
  }

  /**
   * Process the CRC file generation for the given element.
   * 
   * @param pCrcGenerationElement
   *          the CRC file generation element.
   */
  private void processCrcFileGeneration(final CrcGenerationElement pCrcGenerationElement)
  {
    // Switch to "to do" state
    pCrcGenerationElement.setStatus(CrcGenerationStatus.PENDING);

    try
    {
      String lCrc = Crc64Computer.getInstance().computeCrc64For(pCrcGenerationElement.getSourceFile());

      try (PrintWriter printWriter = new PrintWriter(pCrcGenerationElement.getDestinationFile()))
      {
        printWriter.print(lCrc);
        pCrcGenerationElement.setStatus(CrcGenerationStatus.SUCCESS);
      }
      catch (FileNotFoundException fnfe)
      {
        throw new CrcGenerationException(fnfe.getMessage());
      }
    }
    catch (CrcGenerationException cge)
    {
      pCrcGenerationElement.setStatus(CrcGenerationStatus.FAILED);
      String lErrorDescription = "Error while generating the CRC file: " + cge.getMessage();
      pCrcGenerationElement.setErrorDescription(lErrorDescription);
    }
  }

}
