package remote;

import exceptions.FileException;
import tcp.Connection;
import tcp.Server;
import tcp.TCPConnector;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

public class RemoteImpl implements Remote {
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

        if(i == 0 || i == 2){
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
        } else{
            throw new Exception("Not a valid command");
        }

    }
    @Override
    public void createFile(String filename, InputStream is) throws  FileException {

        if(filename == null){
            throw new FileException("File name cannot be null");
        }else if(filename.trim().length() == 0){
            throw new FileException("File name is not valid");
        }

        String path = "remote/" + filename;
        File file = new File(path);

        if(file.exists()) {
            throw new FileException("File already exists and cannot be overwriten");
        }


            byte[] readBuffer = new byte[100];
            try {
                is.read(readBuffer);
            } catch (IOException ex) {
                System.err.println("couldn't read data - fatal");
                System.exit(0);
            }

            try {
                OutputStream os = new FileOutputStream(file);
                try {
                    os.write(readBuffer);
                } catch (IOException ex) {
                    System.err.println("couldn’t write data - fatal");
                    System.exit(0);
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Could not open file - fatal");
                System.exit(0);
            }
    }

    @Override
    public void removeFile(String filename) throws IOException {

        String path = "remote/" + filename;

        if(new File(filename).isFile()){
            Files.delete(Paths.get(path));
            System.out.println("File was deleted");
        } else {
            throw new NoSuchFileException("file does not exist");
        }
    }
}



