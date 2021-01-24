package app;

import exceptions.FileException;
import tcp.Connection;
import tcp.Server;
import tcp.TCPConnector;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RemoteImpl extends FileHandleImpl implements Remote{
    private InputStream is;
    private Connection connection;
    private String filename;

    @Override
    public void getAcceptConnection(int port) throws IOException {
        Server server = new TCPConnector();
        this.connection = server.acceptConnection(port);

    }

    @Override
    public void callCommandReceivedFromLocal(InputStream is) throws Exception {

        DataInputStream dis = new DataInputStream(is);

        int i = dis.readInt();
        String filename = dis.readUTF();

        if(i == 0){
            InputStream receivedIs = null;
            try {
                receivedIs = new ByteArrayInputStream(dis.readAllBytes());
            } catch (IOException ex) {
                System.err.println("couldn't read data - fatal");
                System.exit(0);
            }
            createFile(filename, receivedIs);
        }else if(i == 1){
            removeFile(filename);
        }else{
            throw new Exception("Not a valid command");
        }

    }

}
