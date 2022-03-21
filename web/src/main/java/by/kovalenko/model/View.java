package by.kovalenko.model;

public enum View {
    REGISTRATION("/registration.jsp"),
    LOGIN("/login.jsp"),
    START_PAGE("/index.jsp"),
    ;

    private String path;

    View(String path) {
        this.path = path;
    }

    public String getViewPath(){
        return this.path;
    }
}
