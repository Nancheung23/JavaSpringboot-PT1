public class Main {
    public static void main(String[] args) {
        user user1 = new user("John", "J221", "Advanced password");
        System.out.println(user1.getId());

        user user2 = new user("Martha", "MJ", "something");

        System.out.println(user2.getId());

    }
}
