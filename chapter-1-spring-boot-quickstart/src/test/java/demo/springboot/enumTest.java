package demo.springboot;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/5/31 12:11
 */
public class enumTest {

    @Test
    public void testEnum(){
        System.out.println(FileFormat.XLS.getFormat());
    }


}

 enum FileFormat {

    XLS(".xls"),

    XLSX(".xlsx"),

    CSV(".csv"),

    PNG(".png");

    private final String format;

    FileFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
