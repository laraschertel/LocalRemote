package local;

import app.FileHandle;
import exceptions.FileException;
import tcp.Connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Local extends FileHandle {

    /**
     *
     * @param hostname
     * @param port
     * @return requests to connect to server hostname
     * @throws IOException if there is any Input/Output problems
     */
    Connection connect(String hostname, int port) throws IOException;

    /**
     * serialize the command to create a file in remote component
     * @param filename is the name of the file to be created in remote
     * @param is stream that will be read from to write in file
     * @param os stream to recipient
     * @throws IOException  if there is any Input/Output problems
     */
    void createRemoteFile(String filename, InputStream is, OutputStream os) throws IOException;

    /**
     * serialize the command to remove a file in remote
     * @param filename is the name of the file to be deleted
     * @param os stream to recipient
     * @throws IOException  if there is any Input/Output problems
     */
    void removeRemoteFile(String filename, OutputStream os) throws IOException;

    /**
     * will copy the file with the name filename to remote
     * @param filename is the name of the file to be coped
     * @param os stream to recipient
     * @throws IOException if there is any Input/Output problems
     * @throws FileException if file does not exist
     */
    void copyFileToRemote(String filename, OutputStream os) throws IOException, FileException;




}
