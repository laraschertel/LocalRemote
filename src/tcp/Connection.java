package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Connection {
    /**
     * @return output stream  of created connection
     * @throws IOException if there is any Input/Output problems
     */
    OutputStream getOutputStream() throws IOException;

    /**
     *
     * @return input stream of created connection
     * @throws IOException if there is any Input/Output problems
     */
    InputStream getInputStream() throws IOException;
}
