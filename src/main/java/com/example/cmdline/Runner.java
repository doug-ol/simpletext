package com.example.cmdline;

import org.apache.lucene.codecs.simpletext.SimpleTextCodec;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Runner {
       public static void main(String args[]) {
           try{
               String path =  "/Users/doug/dev/simpletext/data/idx1";
               createIndex(path);
               readIndex(path);
           }catch(IOException ex){
               System.out.println(ex.toString());
           }

   }

    static void readIndex (String path) throws IOException {
        Directory index = FSDirectory.open(FileSystems.getDefault().getPath(path));
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        TermQuery query = new TermQuery(new Term("first", "kevin"));
        TopDocs docs = searcher.search(query, 5);
        for (ScoreDoc scoreDoc :  docs.scoreDocs){
            Document document = reader.document(scoreDoc.doc);
            System.out.println(document.get("first"));
        }

    }

    static IndexWriter getWriter(String path) throws IOException{
        FSDirectory fsdir = FSDirectory.open(FileSystems.getDefault().getPath(path));
        IndexWriterConfig conf = new IndexWriterConfig(null);
        conf.setCodec(new SimpleTextCodec());
        return new IndexWriter(fsdir, conf);
    }
   static void createIndex (String path) throws IOException {
       IndexWriter writer = getWriter(path);
       writer.addDocument(createDocument("doug", "t", "blue", 1, 1.0f, "Mill Valley"));
       writer.addDocument(createDocument("kevin", "r" ,"blue", 2, 2.0f, "San Francisco"));
       writer.addDocument(createDocument("evan", "n", "red", 3, 4.0f, "San Francisco"));
       writer.commit();
       writer.close();

   }

   static Document createDocument(String first, String last, String color,  long count, float score, String city){
       Document document = new Document();
       document.add(new StringField("first", first , Field.Store.YES));
       document.add(new StringField("last", last , Field.Store.YES));
       document.add(new StringField("color", color, Field.Store.NO));
       document.add(new LongPoint("count", count));
       document.add(new FloatDocValuesField("score", score));
       document.add(new SortedDocValuesField("city", new BytesRef(city)));
       return document;


   }




}
