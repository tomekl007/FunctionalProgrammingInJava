import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class HandleException {
    public static void main(String[] args) throws IOException {
        List<String> paths = Arrays.asList("/usr", "/tmp");
        paths.stream()
                .map(path -> {
                    try {
                        return new UseInstanceNewFile().accept(path);
                    } catch (IOException ex) {
                        return ex.getMessage();
                    }
                })
                .forEach(System.out::println);
        }
}