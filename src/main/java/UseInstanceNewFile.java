import java.io.File;
import java.io.IOException;

/**
 * @author Tomasz Lelek
 * @since 2014-06-02
 */
public class UseInstanceNewFile implements UseInstanceWithReturnType<String, IOException> {
    @Override
    public String accept(String path) throws IOException {
        return new File(path).getCanonicalPath();
    }
}
