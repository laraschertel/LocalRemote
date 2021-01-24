package app;

import exceptions.FileException;
import tcp.Connection;

import java.io.IOException;
import java.io.InputStream;

public interface Remote extends FileHandle{

    /**
     *
     * @param port
     * @return connection to local
     * @throws IOException
     */
    void getAcceptConnection(int port) throws IOException;

    /**
     * deserialize the command to call a local method
     * if the first int received = 0 -> creates a file
     * if the first int received = 1 -> removes a file
     * @param
     * @throws FileException
     */
    void callCommandReceivedFromLocal(InputStream is) throws Exception;



}
