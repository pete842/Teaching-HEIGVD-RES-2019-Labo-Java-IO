package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidParameterException;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    if(len + off > str.length())
      throw new InvalidParameterException("Offset or length are too big for the char buffer.");

    super.write(str.substring(off, len + off).toUpperCase(), 0, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if(len + off > cbuf.length)
      throw new InvalidParameterException("Offset or length are too big for the char buffer.");

    for(int i = off; i < len + off; ++i)
      cbuf[i] = Character.toUpperCase(cbuf[i]);

    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
