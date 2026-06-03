package com.wipro.AssignmentDay8;

public class DecoratorSystem {
    public static void main(String[] args) {
        FileWriter file = new PlainFile();
        file = new Compression(file);
        file = new Encryption(file);
        file = new Logging(file);

        System.out.println(file.getDescription());
        System.out.println(file.write());
    }
}

interface FileWriter {
    String getDescription();
    String write();
}

class PlainFile implements FileWriter {
    public String getDescription() {
        return "Plain File";
    }
    public String write() {
        return "Writing data to file";
    }
}

abstract class FileDecorator implements FileWriter {
    protected FileWriter file;
    FileDecorator(FileWriter file) {
        this.file = file;
    }
}

class Compression extends FileDecorator {
    Compression(FileWriter file) {
        super(file);
    }
    public String getDescription() {
        return file.getDescription() + ", Compression";
    }
    public String write() {
        return file.write() + " [Compressed]";
    }
}

class Encryption extends FileDecorator {
    Encryption(FileWriter file) {
        super(file);
    }
    public String getDescription() {
        return file.getDescription() + ", Encryption";
    }
    public String write() {
        return file.write() + " [Encrypted]";
    }
}

class Logging extends FileDecorator {
    Logging(FileWriter file) {
        super(file);
    }
    public String getDescription() {
        return file.getDescription() + ", Logging";
    }
    public String write() {
        return file.write() + " [Logged]";
    }
}