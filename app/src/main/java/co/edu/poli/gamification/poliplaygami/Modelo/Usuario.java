package co.edu.poli.gamification.poliplaygami.Modelo;

public class Usuario {

    private String code;
    private String email;
    private String username;
    private String password;
    private String signature;
    private String role;
    private String group;
    private String coins;
    private String level;
    private String badges;

    private String tempRole;
    private String tempTransport;

    public Usuario(){
        this.code = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.signature = "";
        this.role = "";
        this.group = "";
        this.coins = "";
        this.level = "";
        this.badges = "";
    }

    /** LOGIN User create complete */
    public Usuario(
            String code,
            String email,
            String username,
            String password,
            String signature,
            String role,
            String group,
            String coins,
            String level,
            String badges){
        this.code = code;
        this.email = email;
        this.username = username;
        this.password = password;
        this.signature = signature;
        this.role = role;
        this.group = group;
        this.coins = coins;
        this.level = level;
        this.badges = badges;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getSignature() { return signature; }

    public void setSignature(String signature) { this.signature = signature; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }

    public String getGroup() { return group; }

    public void setGroup(String group) { this.group = group; }

    public String getTempRole() { return tempRole; }

    public void setTempRole(String tempRole) { this.tempRole = tempRole; }

    public String getTempTransport() { return tempTransport; }

    public void setTempTransport(String tempTransport) { this.tempTransport = tempTransport; }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getBadges() {
        return badges;
    }

    public void setBadges(String badges) {
        this.badges = badges;
    }
}