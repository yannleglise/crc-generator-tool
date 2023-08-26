/**
 * Class: GuiConstants.
 *
 *
 *
 * <p>
 * Subjected to GNU GPL Version 3 - LYS
 * </p>
 */
package org.lys.apps.crcgenerator.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JLabel;

/**
 * Constants related to the graphic user interface.
 * 
 * @author Yann Leglise
 */
public final class GuiConstants
{
  /**
   * The title of the tool frame.
   */
  public static final String FRAME_TITLE = "CRC-64-ISO Generator";

  /**
   * The opening markup for HTML.
   */
  public static final String HTML_OPEN_MARKUP = "<html>";

  /**
   * The closing markup for HTML.
   */
  public static final String HTML_CLOSE_MARKUP = "</html>";

  /**
   * The font used for user inputs.
   */
  public static final Font USER_INPUT_FONT = new Font("Courier", Font.BOLD, 12);

  /**
   * The color of the label borders.
   */
  public static final Color LABEL_BORDER_COLOR = new Color(110, 148, 193);

  /**
   * The font for the label borders.
   */
  public static final Font LABEL_BORDER_FONT = new JLabel().getFont();

  /**
   * The default width (in pixels) for the tool frame.
   */
  public static final int DEFAULT_FRAME_WIDTH = 800;

  /**
   * The default height (in pixels) for the tool frame.
   */
  public static final int DEFAULT_FRAME_HEIGHT = 600;

  /**
   * The preferred dimension for the tool frame.
   */
  public static final Dimension FRAME_PREFERRED_DIMENSION = new Dimension(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);

  /**
   * Default margin for top vertical margin.
   */
  public static final int DEFAULT_VERTICAL_TOP_MARGIN = 2;

  /**
   * Default margin for bottom vertical margin.
   */
  public static final int DEFAULT_VERTICAL_BOTTOM_MARGIN = 2;

  /**
   * Default margin for left horizontal margin.
   */
  public static final int DEFAULT_HORIZONTAL_LEFT_MARGIN = 5;

  /**
   * Default margin for right horizontal margin.
   */
  public static final int DEFAULT_HORIZONTAL_RIGHT_MARGIN = 5;

  /**
   * The leader HTML formatting for labels.
   */
  public static final String LABEL_HTML_FORMATTING_LEADER = "<font color='#A4BCD8'>";

  /**
   * The trailer HTML formatting for labels.
   */
  public static final String LABEL_HTML_FORMATTING_TRAILER = "</font>";

  /**
   * The leader HTML formatting for border labels.
   */
  public static final String BORDER_LABEL_HTML_FORMATTING_LEADER = HTML_OPEN_MARKUP + LABEL_HTML_FORMATTING_LEADER;

  /**
   * The trailer HTML formatting for border labels.
   */
  public static final String BORDER_LABEL_HTML_FORMATTING_TRAILER = LABEL_HTML_FORMATTING_TRAILER + HTML_CLOSE_MARKUP;

  /**
   * The text for the label associated with the input files directory field in the source panel.
   */
  public static final String SOURCE_PANEL_INPUT_FILES_DIR_LABEL_TEXT = LABEL_HTML_FORMATTING_LEADER
      + "Directory for the source files:" + LABEL_HTML_FORMATTING_TRAILER;

  /**
   * The text displayed on the frame around the source panel.
   */
  public static final String SOURCE_PANEL_BORDER_TEXT = BORDER_LABEL_HTML_FORMATTING_LEADER + "Files to process"
      + BORDER_LABEL_HTML_FORMATTING_TRAILER;

  /**
   * The text corresponding to the source files directory selection action label.
   */
  public static final String SOURCE_FILES_DIR_SELECTION_ACTION_TEXT = "Select";

  /**
   * The text corresponding to the source files directory selection action tooltip.
   */
  public static final String SOURCE_FILES_DIR_SELECTION_ACTION_TOOLTIP = "Select the directory of the source files to process";

  /**
   * Mnemonic for the source files directory selection action.
   */
  public static final int SOURCE_FILES_DIR_SELECTION_ACTION_MNEMONIC = KeyEvent.VK_C;

  /**
   * Title of the file chooser for selecting the source files directory.
   */
  public static final String SOURCE_FILES_DIR_SELECTION_CHOOSER_DIALOG_TITLE = "Select the directory for the source files";

  /**
   * The text corresponding to the custom destination directory selection action label.
   */
  public static final String CUSTOM_DESTINATION_DIR_SELECTION_ACTION_TEXT = "Select";

  /**
   * The text corresponding to the custom destination directory selection action tooltip.
   */
  public static final String CUSTOM_DESTINATION_DIR_SELECTION_ACTION_TOOLTIP = "Select the directory where to generate the CRC files";

  /**
   * Mnemonic for the custom destination directory selection action.
   */
  public static final int CUSTOM_DESTINATION_DIR_SELECTION_ACTION_MNEMONIC = KeyEvent.VK_D;

  /**
   * Title of the file chooser for selecting the custom destination directory.
   */
  public static final String CUSTOM_DESTINATION_DIR_SELECTION_CHOOSER_DIALOG_TITLE = "Select the directory for the generated CRC files";

  /**
   * The text corresponding to the start CRC generation action label.
   */
  public static final String CRC_GENERATION_START_ACTION_TEXT = "Generate";

  /**
   * The text corresponding to the start CRC generation action tooltip.
   */
  public static final String CRC_GENERATION_START_ACTION_TOOLTIP = "Launches the generation of the CRC files";

  /**
   * Mnemonic for the start CRC generation action.
   */
  public static final int CRC_GENERATION_START_ACTION_MNEMONIC = KeyEvent.VK_S;

  /**
   * The text displayed on the frame around the source file extension list panel.
   */
  public static final String SOURCE_FILE_EXTENSION_LIST_PANEL_BORDER_TEXT = BORDER_LABEL_HTML_FORMATTING_LEADER
      + "File extension filtering" + BORDER_LABEL_HTML_FORMATTING_TRAILER;

  /**
   * The text displayed at the start of a checkbox for a file extension.
   */
  public static final String SOURCE_FILE_EXTENSION_CHECKBOX_TEXT_LEADER = "*.";

  /**
   * The text displayed on the frame around the selectable source file list panel.
   */
  public static final String SELECTABLE_SOURCE_FILE_LIST_PANEL_BORDER_TEXT = BORDER_LABEL_HTML_FORMATTING_LEADER
      + "Source files to process" + BORDER_LABEL_HTML_FORMATTING_TRAILER;

  /**
   * The text displayed on the frame around the destination panel.
   */
  public static final String DESTINATION_PANEL_BORDER_TEXT = BORDER_LABEL_HTML_FORMATTING_LEADER
      + "Destination directory selection" + BORDER_LABEL_HTML_FORMATTING_TRAILER;

  /**
   * The text for the radio button associated with the destination type corresponding to the source directory.
   */
  public static final String DESTINATION_TYPE_SAME_DIR_TEXT = LABEL_HTML_FORMATTING_LEADER + "In source directory"
      + LABEL_HTML_FORMATTING_TRAILER;

  /**
   * The tooltip text for the radio button associated with the destination type corresponding to the source directory.
   */
  public static final String DESTINATION_TYPE_SAME_DIR_TOOLTIP = "The destination files will be written in the source directory";

  /**
   * The text for the radio button associated with the destination type corresponding to the CRC directory at the same
   * level as the source directory.
   */
  public static final String DESTINATION_TYPE_CRC_DIR_TEXT = "In CRC directory";

  /**
   * The tooltip text for the radio button associated with the destination type corresponding to the CRC directory at
   * the same level as the source directory.
   */
  public static final String DESTINATION_TYPE_CRC_DIR_TOOLTIP = "The destination files will be written in the CRC directory at the same level as the source directory";

  /**
   * The text for the radio button associated with the destination type corresponding to a custom directory.
   */
  public static final String DESTINATION_TYPE_CUSTOM_DIR_TEXT = "In custom directory";

  /**
   * The toolip text for the radio button associated with the destination type corresponding to a custom directory.
   */
  public static final String DESTINATION_TYPE_CUSTOM_DIR_TOOLTIP = "The destination files will be writting in the specified custom directory";

  /**
   * The text displayed on the frame around the CRC generation panel.
   */
  public static final String CRC_GENERATION_PANEL_BORDER_TEXT = "CRC file generation";

  /**
   * The relative path from the source path to the CRC directory path.
   */
  public static final String CRC_DIR_RELATIVE_PATH = ".." + File.separatorChar + "CRC";

  /**
   * The extension added to source files to create the target CRC file.
   */
  public static final String CRC_FILE_EXTENSION = ".crc";

  /**
   * Size in pixels for the preferred width of the source file column in the CRC file generation table.
   */
  public static final int CFT_SOURCE_FILE_COL_PREF_WIDTH = 200;

  /**
   * Size in pixels for the preferred width of the target CRC file column in the CRC file generation table.
   */
  public static final int CFT_DEST_CRC_FILE_COL_PREF_WIDTH = 200;

  /**
   * Size in pixels for the preferred width of the CRC generation status column in the CRC file generation table.
   */
  public static final int CFT_CRC_GENERATION_STATUS_COL_PREF_WIDTH = 100;

  /**
   * The leader format for the header labels of the CRC file generation table.
   */
  public static final String CFT_HEADER_FORMAT_LEADER = "<font color=\"#CCCCCC\"><b>&nbsp;";

  /**
   * The trailing format for the header labels of the CRC file generation table.
   */
  public static final String CFT_HEADER_FORMAT_TRAILING = "</b></font>";

  /**
   * Label for the source file column in the CRC file generation table.
   */
  public static final String CFT_SOURCE_FILE_COL_LABEL = HTML_OPEN_MARKUP + CFT_HEADER_FORMAT_LEADER + "Source files"
      + CFT_HEADER_FORMAT_TRAILING + HTML_CLOSE_MARKUP;

  /**
   * Label for the destination CRC file column in the CRC file generation table.
   */
  public static final String CFT_DESTINAITON_CRC_FILE_COL_LABEL = HTML_OPEN_MARKUP + CFT_HEADER_FORMAT_LEADER
      + "CRC files" + CFT_HEADER_FORMAT_TRAILING + HTML_CLOSE_MARKUP;

  /**
   * Label for the CRC generation status column in the CRC file generation table.
   */
  public static final String CFT_CRC_GENERATION_STATUS_COL_LABEL = HTML_OPEN_MARKUP + CFT_HEADER_FORMAT_LEADER
      + "Status" + CFT_HEADER_FORMAT_TRAILING + HTML_CLOSE_MARKUP;

  /**
   * The background color for the CRC file generation table header.
   */
  public static final Color CFT_HEADER_BACKGROUND_COLOR = new Color(127, 127, 127);

  /**
   * Leader format for the file columns of the CRC file generation table.
   */
  public static final String CFT_FILE_COL_LEADER = HTML_OPEN_MARKUP + "<code><b>";

  /**
   * Leader format for the file columns of the CRC file generation table.
   */
  public static final String CFT_FILE_COL_TRAILER = "<b><code>" + HTML_CLOSE_MARKUP;

  /**
   * Format for the source file column in the CRC file generation table.
   * <p>
   * Contains a place holder for the source file value.
   * </p>
   */
  public static final String CFT_SOURCE_FILE_COL_FORMAT = CFT_FILE_COL_LEADER + "&nbsp;{0}" + CFT_FILE_COL_TRAILER;

  /**
   * Format for the destination CRC file column in the CRC file generation table.
   * <p>
   * Contains a place holder for the destination CRC file value.
   * </p>
   */
  public static final String CFT_DESTINATION_CRC_FILE_COL_FORMAT = CFT_FILE_COL_LEADER + "&nbsp;{0}"
      + CFT_FILE_COL_TRAILER;

  /**
   * Format for the CRC generation status column in the CRC file generation table, for the "to do" case.
   */
  public static final String CFT_STATUS_TODO_COL_FORMAT = HTML_OPEN_MARKUP + "<font color=#00FFFF>To do</font>"
      + HTML_CLOSE_MARKUP;

  /**
   * Format for the CRC generation status column in the CRC file generation table, for the "pending" case.
   */
  public static final String CFT_STATUS_PENDING_COL_FORMAT = HTML_OPEN_MARKUP + "<font color=#CC99FF>Pending...</font>"
      + HTML_CLOSE_MARKUP;

  /**
   * Format for the CRC generation status column in the CRC file generation table, for the "success" case.
   */
  public static final String CFT_STATUS_SUCCESS_COL_FORMAT = HTML_OPEN_MARKUP + "<font color=#00FF00>Generated</font>"
      + HTML_CLOSE_MARKUP;

  /**
   * Format for the CRC generation status column in the CRC file generation table, for the "failed" case.
   */
  public static final String CFT_STATUS_FAILED_COL_FORMAT = HTML_OPEN_MARKUP + "<font color=#FF6666>Failed</font>"
      + HTML_CLOSE_MARKUP;

  /**
   * The number of columns for input fields displaying a path.
   */
  public static final int PATH_INPUT_FIELD_COLUMN_COUNT = 256;

  /**
   * Color for the background of the blocking glass pane.
   */
  public static final Color BGP_BACKGROUND_COLOR = new Color(33, 33, 66);

  /**
   * Value for alpha of the blocking glass pane.
   */
  public static final float BGP_ALPHA_VALUE = 0.2f;

  /**
   * Constructor.
   */
  private GuiConstants()
  {
    // Constant class
  }

}
