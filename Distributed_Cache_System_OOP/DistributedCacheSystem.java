class CacheEntry {
    private String key;
    private String value;
    private int ttlInSeconds;
    private long createdTime;
    private long lastAccessTime;

    public CacheEntry(String key, String value, int ttlInSeconds) {
        this.key = key;
        this.value = value;
        this.ttlInSeconds = ttlInSeconds;
        this.createdTime = System.currentTimeMillis() / 1000;
        this.lastAccessTime = this.createdTime;
    }

    public boolean isExpired() {
        long currentTime = System.currentTimeMillis() / 1000;
        return currentTime - createdTime >= ttlInSeconds;
    }

    public void updateAccessTime() {
        lastAccessTime = System.currentTimeMillis() / 1000;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }
}

class LRUPolicy {
    public int findLRUIndex(CacheEntry[] entries, int size) {
        int lruIndex = 0;

        for (int i = 1; i < size; i++) {
            if (entries[i].getLastAccessTime() < entries[lruIndex].getLastAccessTime()) {
                lruIndex = i;
            }
        }

        return lruIndex;
    }
}

class Node {
    private String nodeId;
    private int capacity;
    private CacheEntry[] entries;
    private int size;
    private LRUPolicy policy;

    public Node(String nodeId, int capacity, LRUPolicy policy) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.policy = policy;
        this.entries = new CacheEntry[capacity];
        this.size = 0;
    }

    public void put(CacheEntry entry) {
        removeExpiredEntries();

        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equalsIgnoreCase(entry.getKey())) {
                entries[i] = entry;
                System.out.println("Data updated successfully: " + entry.getKey());
                return;
            }
        }

        if (size == capacity) {
            evictLeastRecentlyUsed();
        }

        entries[size] = entry;
        size++;

        System.out.println("Data added successfully: " + entry.getKey());
    }

    public CacheEntry get(String key) {
        removeExpiredEntries();

        for (int i = 0; i < size; i++) {
            if (entries[i].getKey().equalsIgnoreCase(key)) {
                entries[i].updateAccessTime();
                return entries[i];
            }
        }

        return null;
    }

    public void removeExpiredEntries() {
        for (int i = 0; i < size; i++) {
            if (entries[i].isExpired()) {
                System.out.println("Expired entry removed: " + entries[i].getKey());

                for (int j = i; j < size - 1; j++) {
                    entries[j] = entries[j + 1];
                }

                entries[size - 1] = null;
                size--;
                i--;
            }
        }
    }

    public void evictLeastRecentlyUsed() {
        int lruIndex = policy.findLRUIndex(entries, size);

        System.out.println("Cache full. Least Recently Used entry removed: " + entries[lruIndex].getKey());

        for (int i = lruIndex; i < size - 1; i++) {
            entries[i] = entries[i + 1];
        }

        entries[size - 1] = null;
        size--;
    }

    public void displayEntries() {
        removeExpiredEntries();

        System.out.println();
        System.out.println("Active Cache Entries:");

        if (size == 0) {
            System.out.println("Cache is empty.");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(entries[i].getKey() + " : " + entries[i].getValue());
        }
    }

    public String getNodeId() {
        return nodeId;
    }
}

class CacheManager {
    private Node node;
    private LRUPolicy policy;

    public CacheManager(Node node, LRUPolicy policy) {
        this.node = node;
        this.policy = policy;
    }

    public void addData(String key, String value, int ttlInSeconds) {
        CacheEntry entry = new CacheEntry(key, value, ttlInSeconds);
        node.put(entry);
    }

    public String getData(String key) {
        CacheEntry entry = node.get(key);

        if (entry == null) {
            return "Cache Miss";
        }

        return entry.getValue();
    }

    public void displayCache() {
        node.displayEntries();
    }
}

public class DistributedCacheSystem {
    public static void main(String[] args) throws InterruptedException {
        LRUPolicy policy = new LRUPolicy();

        Node node = new Node("NODE-1", 3, policy);

        CacheManager manager = new CacheManager(node, policy);

        manager.addData("A", "Apple", 60);
        manager.addData("B", "Ball", 60);
        manager.addData("C", "Cat", 60);

        System.out.println("Value Found: " + manager.getData("A"));

        manager.addData("D", "Dog", 60);

        manager.displayCache();
    }
}