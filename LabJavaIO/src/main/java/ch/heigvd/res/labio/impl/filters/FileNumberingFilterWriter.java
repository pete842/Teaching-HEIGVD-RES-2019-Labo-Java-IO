package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private boolean newLine = true;
    private int currentLine = 0;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        for (char c : str.substring(off, len + off).toCharArray())
            write(c);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        for (char c : Arrays.copyOfRange(cbuf, off, len + off))
            write(c);
    }

    @Override
    public void write(int c) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        switch (c) {
            case '\r':
                super.write(c);
                newLine = true;
                break;
            case '\n':
                super.write(c);
                writeLineNumber(++currentLine);
                super.write('\t');
                newLine = false;
                break;
            default:
                if (newLine) {
                    writeLineNumber(++currentLine);
                    super.write('\t');
                    newLine = false;
                }
                super.write(c);
                break;
        }
    }

    private void writeLineNumber(int i) throws IOException {
        for (int c : Integer.toString(i).toCharArray())
            super.write(c);
    }
}
