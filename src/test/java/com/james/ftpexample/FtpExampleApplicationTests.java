package com.james.ftpexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.remote.FileInfo;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpExampleApplicationTests {

    private final static String TEST_FILE = "src/test/resources/hello_world.txt";

    @Autowired
    private FtpExampleGateway ftpExampleGateway;

    @Autowired
    private FtpExampleProperties ftpExampleProperties;

    @Test
    public void testFtpExampleGateway() throws IOException {
        File localTestFile = new File(TEST_FILE);
        String localDir = (ftpExampleProperties.getLocaldir() != null ? ftpExampleProperties.getLocaldir() : ".") + "/";

        ftpExampleGateway.put(localTestFile, "/hello_world.txt");
        ftpExampleGateway.put(localTestFile, "/hello_2.txt");

        List<FileInfo> remoteFiles = ftpExampleGateway.listFiles("/");
        assertTrue(remoteFiles.stream().anyMatch(f -> "hello_world.txt".equals(f.getFilename())));
        assertTrue(remoteFiles.stream().anyMatch(f -> "hello_2.txt".equals(f.getFilename())));

        File downloadedFile = ftpExampleGateway.getFile("/hello_2.txt");
        assertNotNull(downloadedFile);
        assertEquals(localDir + "hello_2.txt", downloadedFile.getPath());
        Files.delete(downloadedFile.toPath());

        List<File> downloadedFiles = ftpExampleGateway.getFiles("/hello_*");
        assertTrue(downloadedFiles.size() >= 2);
        assertTrue(downloadedFiles.stream().anyMatch(f -> "hello_world.txt".equals(f.getName())));
        assertTrue(downloadedFiles.stream().anyMatch(f -> "hello_2.txt".equals(f.getName())));
        for (File f : downloadedFiles) {
            Files.delete(f.toPath());
        }

        ftpExampleGateway.move("hello_world.txt", "hello_world_3.txt");
        remoteFiles = ftpExampleGateway.listFiles("/");
        assertTrue(remoteFiles.stream().noneMatch(f -> "hello_world.txt".equals(f.getFilename())));
        assertTrue(remoteFiles.stream().anyMatch(f -> "hello_world_3.txt".equals(f.getFilename())));
        assertTrue(remoteFiles.stream().anyMatch(f -> "hello_2.txt".equals(f.getFilename())));

        ftpExampleGateway.deleteFile("hello_world_3.txt");
        ftpExampleGateway.deleteFile("hello_2.txt");
        remoteFiles = ftpExampleGateway.listFiles("/");
        assertTrue(remoteFiles.stream().noneMatch(f -> "hello_world_3.txt".equals(f.getFilename())));
        assertTrue(remoteFiles.stream().noneMatch(f -> "hello_2.txt".equals(f.getFilename())));
    }

}
