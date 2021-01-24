package app;

import tcp.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Local extends FileHandle{

    /**
     *
     * @param hostname
     * @param port
     * @return connection to remote
     * @throws IOException
     */
    void connect(String hostname, int port) throws IOException;

    /**
     * serialize the command to create a file in remote
     * @param filename
     * @param
     * @throws IOException
     */
    void createRemoteFile(String filename, InputStream is, OutputStream os) throws IOException;

    /**
     * serialize the command to remove a file in remote
     * @param filename
     * @param
     * @throws IOException
     */
    void removeRemoteFile(String filename, OutputStream os) throws IOException;




}
