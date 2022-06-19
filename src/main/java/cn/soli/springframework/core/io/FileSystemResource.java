package cn.soli.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.io.*;

public class FileSystemResource implements Resource {

    private final String path;

    private final File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file) {
        Assert.notNull(file, "File must not be null");
        this.file = file;
        this.path = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }
}
