package exceptions;

    /**
     * Used if the file already exists
     */

    public class FileException extends Exception {
        public FileException() {super (); }
        public FileException(String message) {super (message); }
        public FileException(String message, Throwable t) {super (message, t); }

    }

