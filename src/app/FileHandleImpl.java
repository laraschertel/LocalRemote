package app;

import exceptions.FileException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

abstract class FileHandleImpl implements FileHandle {

    @Override
    public void createFile(String filename, InputStream is) throws FileException, IOException {
        File file = new File(filename);

        /* if(file.createNewFile()){
            System.out.println("file was created");
        }else{
            throw new FileException("file already exists");
        }

         */

        byte[] readBuffer = new byte[100];
        try {
            is.read(readBuffer);
        } catch (IOException ex) {
            System.err.println("couldn't read data - fatal");
            System.exit(0);
        }

        try {
            OutputStream os = new FileOutputStream(filename);
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
    public void removeFile(String filename) throws IOException, NoSuchFileException {

           if(new File(filename).isFile()){
               Files.delete(Paths.get(filename));
               System.out.println("File was deleted");
           } else {
               throw new NoSuchFileException("file does not exist");
           }
    }

    @Override
    public void copyFile(String filename) throws IOException {

    }

}

