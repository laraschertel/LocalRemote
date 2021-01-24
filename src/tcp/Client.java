package tcp;

import java.io.IOException;

public interface Client {
    /**
     *
     * @param hostname try to connect to the hostname
     * @param port
     * @return
     * @throws IOException
     */
    Connection connect(String hostname, int port) throws IOException;
}
