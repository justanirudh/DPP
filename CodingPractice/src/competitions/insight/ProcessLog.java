package competitions.insight;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by paanir on 7/4/17.
 */
public class ProcessLog {
    public static void main(String[] args) {
        try {
            File file = new File("/Users/paanir/Code/anomaly_detection/sample_dataset/batch_log.json");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ObjectMapper mapper = new ObjectMapper();
//            StringBuffer stringBuffer = new StringBuffer();
            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                stringBuffer.append(line);
//                stringBuffer.append("\n");
//
//            }
            line = bufferedReader.readLine();
            Parameters params = mapper.readValue(line, Parameters.class);
            System.out.println(params.degree + "," + params.transactions);
            fileReader.close();
//            System.out.println("Contents of file:");
//            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
