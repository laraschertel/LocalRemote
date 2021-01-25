package app;

import exceptions.FileException;

import java.io.IOException;
import java.io.InputStream;

public interface FileHandle {

    void createFile(String filename, InputStream is) throws Exception, FileException;

    void removeFile(String filename) throws IOException;


}
