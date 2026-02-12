package util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public final class ResourceUtils {
    private ResourceUtils() {
        // Utility class
    }

    public static Map<String, InputStream> loadResourceFiles(String resourcePath) {
        try {
            URL url = Thread.currentThread().getContextClassLoader().getResource(resourcePath);

            if (url == null) {
                throw new IllegalArgumentException("Resource not found: " + resourcePath);
            }

            URI uri = url.toURI();

            if ("jar".equals(uri.getScheme())) {
                return loadFromJar(uri, resourcePath);
            } else {
                return loadFromFileSystem(uri, resourcePath);
            }

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Failed to load resources from: " + resourcePath, e);
        }
    }

    private static Map<String, InputStream> loadFromJar(URI uri, String resourcePath) throws IOException {
        Map<String, InputStream> result = new HashMap<>();

        FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
        Path root = fs.getPath("/" + resourcePath);

        try (Stream<Path> walk = Files.walk(root)) {
            walk.filter(Files::isRegularFile).forEach(path -> {
                String relative = root.relativize(path).toString().replace("\\", "/");
                try {
                    result.put(relative, Files.newInputStream(path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return result;
    }

    private static Map<String, InputStream> loadFromFileSystem(URI uri, String resourcePath) throws IOException {
        Map<String, InputStream> result = new HashMap<>();

        Path root = Paths.get(uri);

        try (Stream<Path> walk = Files.walk(root)) {
            walk.filter(Files::isRegularFile).forEach(path -> {
                String relative = root.relativize(path).toString().replace("\\", "/");
                try {
                    result.put(relative, Files.newInputStream(path));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return result;
    }
}
