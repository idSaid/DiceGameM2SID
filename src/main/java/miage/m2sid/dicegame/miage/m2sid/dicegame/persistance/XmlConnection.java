package miage.m2sid.dicegame.miage.m2sid.dicegame.persistance;


public class XmlConnection implements DBConnection {

    static XmlConnection xmlConnection;
    private static String FILE_NAME;

    public void connection() {
        FILE_NAME = "dice_game.xml";
    }

    public static DBConnection getInstance() {
        if(xmlConnection == null)
            xmlConnection = new XmlConnection();

        return xmlConnection;
    }
}
