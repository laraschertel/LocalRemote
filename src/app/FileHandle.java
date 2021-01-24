package app;

import java.io.IOException;
import java.io.InputStream;

public interface FileHandle {

    void createFile(String filename, InputStream is) throws Exception;

    void removeFile(String filename) throws IOException;

    void copyFile(String filename) throws IOException;
}
