public class user {
    private String userName;
    private String userNickName;
    private String password;
    private static int nextId = 1;
    private int id;
    
    public user(final String userName, String userNickName, String password) {
        this.userName = userName;
        this.userNickName = userNickName;
        this.password = password;
        this.id = nextId;
        nextId += 1;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
