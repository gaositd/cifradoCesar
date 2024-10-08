package src.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class FileHandler {

  public static String readFile(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    byte[] bytes = Files.readAllBytes(path);
    return new String(bytes, StandardCharsets.UTF_8);
  }

  // ... other methods for working with files ...

}
