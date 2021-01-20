package aiot.java.util;

public class ResUtils {

    public static String extractResIdFromUri(String uri) {
        try {
            if (uri.startsWith("/")) uri = uri.substring(1);
            if (uri.endsWith("/")) uri = uri.substring(0, uri.length() - 1);

            String result = null;

            String[] tokens = uri.split("/");
            if (tokens[0].equals("ins-api") || tokens[0].equals("ins-web"))
                if (tokens[1].equals("api")) {
                    if (tokens[2].equals("mock") || tokens[2].equals("ext")) result = tokens[3];
                    else result = tokens[2];
                }

            if (result == null) return null;
            if (!result.substring(0, 3).equals("lia")) return null;
            if (result.length() < 7) return null;
            return result.substring(0, 7);

        } catch (Exception e) {
            return null;
        }
    }
}
