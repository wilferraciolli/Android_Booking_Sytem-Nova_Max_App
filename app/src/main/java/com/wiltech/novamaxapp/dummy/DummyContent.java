package com.wiltech.novamaxapp.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class DummyContent {

    /**
     * An array of sample items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
        addItem(new DummyItem("1", "Products", "http://www.novmaxkeratin.com/products.html"));
        addItem(new DummyItem("2", "Q&A", "http://www.novmaxkeratin.com/questions.html"));
        addItem(new DummyItem("3", "Salons", "http://www.novmaxkeratin.com/salons.html"));
        addItem(new DummyItem("3", "Gallery", "http://www.novmaxkeratin.com/gallery.html"));
        addItem(new DummyItem("3", "Video", "http://www.novmaxkeratin.com/video.html"));
        addItem(new DummyItem("3", "Contact us", "http://www.novmaxkeratin.com/email/"));
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class DummyItem {
        public String id;
        public String name;
        public String url;

        public DummyItem(String id, String name, String url) {
            this.id = id;
            this.name = name;
            this.url = url;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
