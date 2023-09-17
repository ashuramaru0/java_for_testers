import java.io.File;

public class Hello {
    public static void main(String[] args) {

        var configFile= new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());

    }
}
