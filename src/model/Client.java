package model;
/**
 * Klasse welche auf Serverseite die Informationen über eingeloggte Clients enthält
 * @author Jonas
 */
public class Client {
	private String nick;
	private String group;
	private String[] extensions;
    
    /**
     * Konstruktor für Clientinfo
     * @param nick       Nickname des Client
     * @param group      Gruppenname
     * @param extensions Erweiterungen die der Client unterstützt
     * @author Ludwig
     */
    public Client(String nick, String group, String[] extensions) {
        setNick(nick);
        setGroup(group);
        setExtensions(extensions);
    }
    
	/**
	 * @return den Namen des Clients
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * Setzt den Namen des Client
	 * @param nick Der neue Name des Clients
	 */
	protected void setNick(String nick) {
		this.nick = nick;
	}
	/**
	 * @return den Gruppennames des Clients
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * Setzt den neuen Groupennamen
	 * @param group die neue gruppe
	 */
	protected void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return die Liste der unterstützten Extensions
	 */
	public String[] getExtensions() {
		return extensions;
	}
	/**
	 * 
	 * @param extensions Die neue Liste der Extensions
	 */
	protected void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}
}