package com.sstudio.thebadminton;

import com.sstudio.thebadminton.remote.IUploadAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okio.BufferedSink;

public class a {
    File file;
    int DEFAULT_BUFFER_SIZE = 4096;
    IUploadAPI mServices;

    public void writeTo(BufferedSink sink) throws IOException{
        long fileLength = file.length();
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        FileInputStream fileInputStream = new FileInputStream(file);
        long uploaded = 0;


    }


}
