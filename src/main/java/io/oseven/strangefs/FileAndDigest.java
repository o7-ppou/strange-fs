package io.oseven.strangefs;

public class FileAndDigest {
    public FileAndDigest(String content, String digest) {
        this.content = content;
        this.digest = digest;
    }

    public String content;
    public String digest;
}
