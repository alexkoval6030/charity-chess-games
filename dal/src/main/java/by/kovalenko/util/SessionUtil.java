package by.kovalenko.util;


public class SessionUtil {

    private static SessionFactory getInstance(){
        SessionFactory INSTANCE = new Configuration().configure().buildSessionFactory();
        return INSTANCE;
    }

    public static Session openSession(){
        return getInstance().openSession();
    }
}