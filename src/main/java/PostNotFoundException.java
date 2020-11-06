public class PostNotFoundException extends Throwable {
    public void throwException() throws PostNotFoundException {
        throw new PostNotFoundException();
    }
}
