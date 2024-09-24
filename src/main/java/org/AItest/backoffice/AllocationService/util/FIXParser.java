package org.AItest.backoffice.AllocationService.util;

import java.util.HashMap;
import java.util.Map;

public class FIXParser {

    // Parse a FIX message into a map of tag-value pairs
    public static Map<String, String> parseFIXMessage(String fixMessage) {
        Map<String, String> fixFields = new HashMap<>();
        String[] keyValuePairs = fixMessage.split("\\u0001"); // SOH delimiter

        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=");
            if (keyValue.length == 2) {
                fixFields.put(keyValue[0], keyValue[1]);
            }
        }
        return fixFields;
    }

    // Build a FIX message from a map of tag-value pairs
    public static String buildFIXMessage(Map<String, String> fixFields) {
        StringBuilder fixMessage = new StringBuilder();
        for (Map.Entry<String, String> entry : fixFields.entrySet()) {
            fixMessage.append(entry.getKey()).append("=").append(entry.getValue()).append("\u0001"); // SOH delimiter
        }
        return fixMessage.toString();
    }
}