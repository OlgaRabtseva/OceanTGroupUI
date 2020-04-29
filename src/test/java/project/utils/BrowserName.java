package project.utils;

import java.util.ArrayList;
import java.util.List;

public enum BrowserName {

    CHROME("chrome"),
    FIREFOX("firefox"),
    IE("internet explorer"),
    EDGE("edge");

    private final String name;

    BrowserName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static BrowserName fromName(String name) {
        for(BrowserName browserName: BrowserName.values()) {
            if(browserName.getName().equalsIgnoreCase(name)) {
                return browserName;
            }
        }
        return null;
    }

    public static List<String> getPossibleNames() {
        List<String> possibleName = new ArrayList<>();
        for(BrowserName browserName: BrowserName.values()) {
            possibleName.add(browserName.getName());
        }
        return possibleName;
    }
}
