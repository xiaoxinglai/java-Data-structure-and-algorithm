import com.lxx.tokenParser.CharReader;
import com.lxx.tokenParser.Token;
import com.lxx.tokenParser.TokenList;
import com.lxx.tokenParser.Tokenizer;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @ClassName TokenizerTest
 * @Author laixiaoxing
 * @Date 2020/3/2 下午9:28
 * @Description TODO
 * @Version 1.0
 */
public class TokenizerTest {

    @Test
    public void TestTokenizer() throws IOException {
        String str = "{\"id\":1}";
        Reader reader = new InputStreamReader(new ByteArrayInputStream(str.getBytes()));
        CharReader charReader = new CharReader(reader);
        Tokenizer tokenizer = new Tokenizer();
        TokenList tokenList = tokenizer.tokenize(charReader);
        while (tokenList.hasMore()) {
            Token token = tokenList.next();
            System.out.println(token.getValue());
        }
    }
}
