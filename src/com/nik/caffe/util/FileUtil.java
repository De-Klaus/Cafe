package com.nik.caffe.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.nik.caffe.Params.CsvParams.*;

public class FileUtil {

    public static Path createPath(String statisticsName){
        Path path;
        switch (statisticsName) {
            case BUYER_DIR:
                path = Path.of(HEAD_DIR, BUYER_DIR, BUYER_FILE_NAME + System.currentTimeMillis() + FORMAT_FILE);
                break;
            case CASHBOX_DIR:
                path = Path.of(HEAD_DIR,  CASHBOX_DIR, CASHBOX_FILE_NAME + System.currentTimeMillis() + FORMAT_FILE);
                break;
            case COMPARISON_DIR:
                path = Path.of(HEAD_DIR,  COMPARISON_DIR, COMPARISON_FILE_NAME + System.currentTimeMillis() + FORMAT_FILE);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + statisticsName);
        }
        return path;
    }

    /**
     * Append results to a new file
     * @param path
     * @param list
     * @return
     */
    public static boolean writeFile(Path path, List list){
        AtomicBoolean addSuccessfully = new AtomicBoolean(true);
        try {
            List<String> addData = (List<String>) list.stream().map(String::valueOf).collect(Collectors.toList());
            if(!Files.exists(path.getParent()))
                new File(String.valueOf(path.getParent())).mkdirs();
            Files.write(path, addData);
        } catch (IOException e) {
            e.printStackTrace();
            addSuccessfully.set(false);
        }
        return addSuccessfully.get();
    }

}
