package ch.heigvd.res.labio.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[] res = new String[2];

    int i;
    int lineLength = lines.length();

    for (i = 0; i < lineLength; ++i) {
      if (lines.charAt(i) == '\n') {
        break;
      } else if (lines.charAt(i) == '\r') {
        if (i + 1 < lineLength && lines.charAt(i+1) == '\n') {
          ++i;
        }
        break;
      }
    }
    ++i;

    if (i > lineLength)
      i = 0;

    res[0] = lines.substring(0, i);
    res[1] = lines.substring(i);

    return res;
  }

}
