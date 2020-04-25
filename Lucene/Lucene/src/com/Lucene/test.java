package com.Lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

import java.io.File;

public class test {
    public static void main(String[] args) throws Exception {
        //1、创建一个Director对象，指定索引库保存的位置。
        Directory directory = FSDirectory.open(new File("C:\\temp\\index").toPath());
        //2、基于Directory对象创建一个IndexWriter对象
        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter indexWriter = new IndexWriter(directory, config);
        //3、读取磁盘上的文件，对应每个文件创建一个文档对象。
        File dir = new File("F:\\searchsource");
        File[] files = dir.listFiles();
        for (File f : files) {
            //取文件名
            String fileName = f.getName();
            //文件的路径
            String filePath = f.getPath();
            //文件的内容
            String fileContent = FileUtils.readFileToString(f, "utf-8");
            //文件的大小
            long fileSize = FileUtils.sizeOf(f);

            Field fieldName = new TextField("name", fileName, Field.Store.YES);
            Field fieldPath = new TextField("path", filePath, Field.Store.YES);
            Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
            Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);

            Document document = new Document();

            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSize);

            indexWriter.addDocument(document);
        }
        indexWriter.close();

    }
}
