package com.james.ftpexample;

import org.springframework.integration.file.remote.FileInfo;
import org.springframework.messaging.handler.annotation.Header;

import java.io.File;
import java.util.List;

/**
 * @author james
 * @since 11/7/18
 */
public interface FtpExampleGateway {

    /**
     * List files and directories in the given path.
     * <p>
     * All "", ".", or "/" are root directory.
     * <p>
     * Option "-dirs" included but others available
     * Available options: {@code org.springframework.integration.file.remote.gateway.AbstractRemoteFileOutboundGateway.Option}
     *
     * @param path Path to search for
     * @return List of file infos
     */
    List<FileInfo> listFiles(String path);

    /**
     * Downloads the file of the given path.
     * <p>
     * Can start with "/" or be relative to top.
     * Puts the file in example.ftp.localdir
     *
     * @param path Path to file to get
     * @return File in downloaded location
     */
    File getFile(String path);

    /**
     * Downloads the files of the given path with a wildcard.
     * <p>
     * Can start with "/" or be relative to top.
     * Puts the files in example.ftp.localdir
     *
     * @param path Path to files to get with wildcard
     * @return Files in downloaded location
     */
    List<File> getFiles(String path);

    /**
     * Deletes the file from the server
     * <p>
     * Can start with "/" or be relative to top.
     * // TODO figure out how to delete directories
     *
     * @param path ath to file to delete
     */
    void deleteFile(String path);

    /**
     * Moves (renames) a file or directory.
     *
     * @param from File/directory to move
     * @param to   Location to move file to
     */
    void move(String from, @Header(name = "file_renameTo") String to);

    /**
     * Places a file on the remote host
     *
     * @param file        File to place
     * @param destination Full path including file name of where to place file
     */
    void put(File file, @Header(name = "destination") String destination);

    /**
     * Places files on the remote host
     *
     * @param filePattern File name pattern of what to transfer
     * @param destinationDir Path name directory to place files in
     */
    // I think this requires 5.1
//    void putFiles(Collection<File file, @Header(name = "destination-dir") String destinationDir);
}
