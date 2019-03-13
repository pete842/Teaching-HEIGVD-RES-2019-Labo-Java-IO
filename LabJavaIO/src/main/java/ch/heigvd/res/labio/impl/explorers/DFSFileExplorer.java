package ch.heigvd.res.labio.impl.explorers;

import ch.heigvd.res.labio.interfaces.IFileExplorer;
import ch.heigvd.res.labio.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    exploreRec(rootDirectory, visitor);
  }

  private void exploreRec(File file, IFileVisitor visitor) {
    visitor.visit(file);

    if(!file.isDirectory())
      return;

    File[] files = file.listFiles();
    Arrays.sort(files);

    for (File f : files) {
      exploreRec(f, visitor);
    }
  }

}
