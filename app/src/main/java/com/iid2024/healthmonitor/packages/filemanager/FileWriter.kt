package com.iid2024.healthmonitor.packages.filemanager

import android.os.Environment
import java.io.File
import java.io.FileOutputStream

class FileWriter {

    companion object {
        fun CreateFile(fileName : String): File {
            val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            val folder = File(downloadsDirectory, "Smart Health Monitor");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            val file = File(folder, fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            return file;
        }

        fun GetFiles(): Array<File>? {
            val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            val folder = File(downloadsDirectory, "Smart Health Monitor");
            if (!folder.exists()) {
                return null
            }
            return folder.listFiles();
        }

        fun GetFile(fileName : String) : File? {
            val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            val folder = File(downloadsDirectory, "Smart Health Monitor");
            if (!folder.exists()) {
                return null
            }
            val file = File(folder, fileName);
            if (!file.exists()) {
                return null
            }
            return file
        }

    }
}