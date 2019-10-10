package com.clownvin.softserve;

import com.clownvin.softserve.http.HttpServer;
import com.clownvin.softserve.route.Router;
import com.clownvin.softserve.route.Routes;

import java.io.File;

public class SoftServe {
    public static final String VERSION = "0.2.0";
    public static final String OS_INFO = System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
    public static final int SERVE_FILES = 0;
    public static final int SERVE_JSON = 1;
    protected static int mode = 0;

    public static void main(String[] args) {
        String path = args.length == 1 ? args[0] : "./htdocs";

        for (int i = 0; i < args.length; i++) {
            switch (args[i].toLowerCase()) {
                case "--file":
                case "-f":
                    mode = SERVE_FILES;
                    path = args[++i];
                    break;
                case "-j":
                case "-json":
                    mode = SERVE_JSON;
                    path = args[++i];
            }
        }

        File htDir = new File(path);
        if (!htDir.exists() || !htDir.isDirectory()) {
            throw new RuntimeException("document folder could not be found at: " + path);
        }

        Router.Builder builder = Router.builder();
        switch (mode) {
            case SERVE_FILES:
                System.out.println("Serving files from "+htDir.getAbsolutePath());
                builder.add(Routes.getFileRoute("/", path));
                break;
        }
        HttpServer server = new HttpServer(8080, builder.finish());
        server.start();
    }
}
