package app;

import exceptions.FileException;

import java.io.IOException;
import java.io.InputStream;

public interface FileHandle {

    /**
     *
     * @param filename is the name of the file that will be created
     * @param is stream that will be read from to write in file
     * @throws FileException if file name only has blank spaces of file already exists
     */
    void createFile(String filename, InputStream is) throws  FileException;

    /**
     *
     * @param filename is the name of the file to be removed
     * @throws IOException if there is any Input/Output problems
     * @throws FileException if file doesnt exist
     */
    void removeFile(String filename) throws IOException, FileException;


}
