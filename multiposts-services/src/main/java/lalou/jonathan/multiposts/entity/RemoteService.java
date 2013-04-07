package lalou.jonathan.multiposts.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * User: Jonathan LALOU
 * Date: 07/04/13
 * Time: 00:36
 */
public class RemoteService implements Serializable {
    /**
     * eg: LinkedIn, Twitter, Viadeo
     */
    private String name;
    /**
     * The API key for current application
     */
    private String apiKey;
    private String url;
    private String login;
    private String password;
    private Map<String, String> options;

    public RemoteService() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
