package com.clownvin.softserve.route;

import com.clownvin.util.MimeTypes;
import com.clownvin.softserve.cache.file.FileCache;
import com.clownvin.softserve.http.message.request.Method;
import com.clownvin.softserve.http.message.response.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class Routes {

    public static Route getFileRoute(String routePath, String filePath) {
        final String actualPath = !filePath.endsWith("/") ? filePath + "/" : filePath;
        final FileCache cache = new FileCache();
        RouteMatcher matcher = new PatternedMatcher(Method.GET, routePath + ".*");
        RouteHandler handler = (request) -> {
            File file;
            file = new File(actualPath + request.getUrl().getPath().substring(routePath.length()));
            if (file.isDirectory()) {
                file = new File(actualPath + request.getUrl().getPath().substring(routePath.length()) + "/index.html");
            }
            System.out.println("For path: " + request.getUrl().getPath());
            System.out.println("Finding file at: "+file.getAbsolutePath());
            if (!file.exists() || file.isDirectory()) {
                return Response.builder().status(404).body("\uD83D\uDEAB File not found \uD83D\uDEAB").finish();
            }
            byte[] bytes = cache.get(file.getAbsolutePath());
            if (bytes == null) {
                try {
                    bytes = Files.readAllBytes(file.toPath());
                    cache.store(file.getAbsolutePath(), file, bytes);
                    System.out.println("Caching file: "+file.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    return Response.builder().status(404).body("File not found: " + file.getPath()).finish();
                } catch (IOException e) {
                    return Response.builder().status(500).body("Exception parsing file: " + e.getMessage()).finish();
                }
            } else {
                System.out.println("Using cached file");
            }
            return Response.builder().status(200).body(bytes, MimeTypes.getMimeType(file)).finish();
        };
        return new Route(matcher, handler);
    }
}
