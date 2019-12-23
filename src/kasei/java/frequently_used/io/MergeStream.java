package main.java.se.io;

public class MergeStream {

    public void merge() {
        SequenceInputStream sequenceInputStream=null;
        try {
            InputStream input1 = new FileInputStream("c:\\data\\file1.txt");
            InputStream input2 = new FileInputStream("c:\\data\\file2.txt");

            Vector<InputStream> streams = new Vector<InputStream>();
            streams.add(input1);
            streams.add(input2);

            sequenceInputStream = new SequenceInputStream(streams.elements());

            int data= sequenceInputStream.read();

            while(data != -1){
                System.out.println((char)data);
                data = sequenceInputStream.read();
            }
            sequenceInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (sequenceInputStream != null) {
                try {
                    sequenceInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
