public class  Main {
    public static void main(String[] args)
    {
        User user = User.getUser(); // obtain the singleton instance
        user.begin();
    }

}