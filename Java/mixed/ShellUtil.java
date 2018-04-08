import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
	execute python script in linux shell or windows commandline
*/
public class ShellUtil {

    public static String runShell(String shStr) throws Exception {
        Process process;
        process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr});
        process.waitFor();
        BufferedReader read = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        String result = "";
        while ((line = read.readLine())!=null){
            result+=line;
        }
        return result;
    }
	//usage: ShellUtil.runCMD("workon scrapy_crawler && python D:/coding/python/untitled2222222/main.py && ....");    
    public static String runCMD(String shStr) throws Exception {
        Process process = Runtime.getRuntime().exec(new String[]{"cmd","/c",shStr});
        //process.waitFor();
        return "";
    }
}