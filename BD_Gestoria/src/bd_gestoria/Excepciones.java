package bd_gestoria;
public class Excepciones {

    static class VisitaNotFound extends Exception {
        public VisitaNotFound() {
            super("ERROR 404: Not found");
        }
    }
    static class UsuarioNotFound extends Exception {
        public UsuarioNotFound() {
            super("ERROR 404: Not found");
        }
    }
}
