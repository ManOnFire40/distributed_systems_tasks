package mo_1;

import java.io.InputStreamReader; 
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.Block;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.hdfs.protocol.LocatedBlock;
import org.apache.hadoop.hdfs.protocol.LocatedBlocks;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.shaded.com.sun.xml.bind.v2.schemagen.xmlschema.List;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import java.lang.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReplicateFile {

  public static void main(String[] args) throws Exception {
  
    // Define the configuration
    Configuration conf = new Configuration();
    conf.set("fs.defaultFS", "hdfs://localhost:9000");
    conf.set("dfs.replication", "5");
   
   
    String inputFile = "123.txt"; // Define the local input file 
    String mainFile = "/V1/outputfile.txt"; 
    String replication = "/V2/outputfile.txt"; 
    Path filePath = new Path(mainFile); 
    Path replicationPath = new Path(replication); 
    uploadFile(   inputFile,   mainFile,    conf );  
    getDataNode(   filePath, conf);
    ReadFile( filePath , conf );
    ReplicateFile(  conf,     filePath,  replicationPath);
    ReadFile( replicationPath , conf );
 
//    FSDataOutputStream outputStream = fs.create(filePath); 
////    outputStream.writeBytes("Hello, Hadoop!");  ,.append 
//    outputStream.close();
 
     
    }
   
  
  //Task
  public static void ReplicateFile( Configuration conf,   Path source, Path replicationPath) throws IOException {
      FileSystem fs = FileSystem.get(conf);
        
      
      InputStream in=fs.open(source);
       OutputStream out =fs.create(replicationPath);
      

  	 
	   
	    IOUtils.copyBytes(in, out, conf, true);     // Copy the input file to the output file (with replication)
	    IOUtils.closeStream(in);// Close the streams
	    IOUtils.closeStream(out); 
	    
      
      //Open Input stream of the existed source file using fs.open()
	  
//Create Path for replication file and open output stream for it using fs.create()

	 
 // Copy the input file to the output file (with replication)
	  // Close the input streams
	 
	   // Close the output streams
	  
  }
  public static void ReadFile(  Path filePath , Configuration conf ) throws IOException { 
	    FileSystem fs1 = filePath.getFileSystem(conf);
	    
	    FSDataInputStream inputStream = fs1.open(filePath);

	    // Code with Students 
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
	    String line = reader.readLine();
	 
	    int i=0;
	    while (i != 10  ) {
	        System.out.println(line);
	        line = reader.readLine();
	     
	        i++;
	    }
	 
	    // Close the input stream and file system
	    reader.close();
	    inputStream.close();
	  
  }
    public static void uploadFile( String inputFile, String hdfsPath,  Configuration conf ) throws IOException {
    	
    	InputStream in = new BufferedInputStream(new FileInputStream(inputFile));// Open the input file
    	FileSystem fs = FileSystem.get(conf); // Open the output file
	    OutputStream out = fs.create(new Path(hdfsPath));
	    IOUtils.copyBytes(in, out, conf, true);     // Copy the input file to the output file (with replication)
	    IOUtils.closeStream(in);// Close the streams
	    IOUtils.closeStream(out); 
	    
    }
    public static void getDataNode(  Path filePath ,  Configuration conf ) throws IOException {
            FileSystem fs = FileSystem.get(conf);
    	    FileStatus fileStatus = fs.getFileStatus(filePath);
           System.out.println(fileStatus);        
        
        if (fileStatus.isFile()) {
          DistributedFileSystem dfs = (DistributedFileSystem) fs;
          Iterable<LocatedBlock> blocks = dfs.getClient().getLocatedBlocks(filePath.toString(), 0, fileStatus.getLen()).getLocatedBlocks();
          System.out.println("The file is stored in the following Blocks");
          for (LocatedBlock block : blocks) {
        	  
        	  System.out.println("Block " + block.getBlock().getBlockName() + " with size " + block.getBlockSize() + " bytes ");
          }
          
          System.out.println("The file is stored in the following DataNodes");
          DatanodeInfo[] dataNodes = dfs.getDataNodeStats();
          for (DatanodeInfo datanode : dataNodes) {
            System.out.println(datanode.getHostName());
          }
        } else {
          System.out.println("The specified path does not represent a file.");
        }
    }
 
}
  

