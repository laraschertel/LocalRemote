package app;

import tcp.Client;
import tcp.Connection;
import tcp.TCPConnector;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LocalImpl extends FileHandleImpl implements Local {
    private static final int CREATE = 0;
    private static final int REMOVE = 1;
    private static final int COPY = 2;
    private Connection connection;


    @Override
    public void connect(String hostname, int port) throws IOException {

        Client client = new TCPConnector();
        this.connection = client.connect(hostname, port);
    }

    @Override
    public void createRemoteFile(String filename, InputStream is, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);

        byte[] readBuffer = new byte[100];
        try {
            is.read(readBuffer);
        } catch (IOException ex) {
            System.err.println("couldn't read data - fatal");
            System.exit(0);
        }
        try{
            dos.writeInt(CREATE);
            dos.writeUTF(filename);
            dos.write(readBuffer);
        }catch (IOException e){
            System.err.println("could not remove remote file");
        }

    }

    @Override
    public void removeRemoteFile(String filename, OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);

        try{
            dos.writeInt(REMOVE);
            dos.writeUTF(filename);
        }catch (IOException e){
            System.err.println("could not remove remote file");
        }

    }

}
