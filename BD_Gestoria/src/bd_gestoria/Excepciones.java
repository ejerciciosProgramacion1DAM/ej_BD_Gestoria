package bd_gestoria;
public class Excepciones {

    static class VisitaNotFound extends Exception {
        public VisitaNotFound() {
            super("ERROR 404: Not found");
        }
    }
}
