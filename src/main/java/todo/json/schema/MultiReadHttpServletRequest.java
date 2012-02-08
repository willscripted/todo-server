package todo.json.schema;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author Siegfried Puchbauer
 */
public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private byte[] body;

    public MultiReadHttpServletRequest(HttpServletRequest httpServletRequest)
            throws IOException {

        super(httpServletRequest);
        // Read the request body and save it as a byte array
        InputStream is = super.getInputStream();
        body = IOUtils.toByteArray(is);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamImpl(new ByteArrayInputStream(body));
    }

    @Override
    public BufferedReader getReader() throws IOException {
        String enc = getCharacterEncoding();
        if(enc == null) enc = "UTF-8";
        return new BufferedReader(new InputStreamReader(getInputStream(), enc));
    }


    private class ServletInputStreamImpl extends ServletInputStream {
        private final InputStream is;

        private ServletInputStreamImpl(InputStream inputStream) {
            this.is = inputStream;
        }
        
        @Override
        public int read() throws IOException {
            return is.read();
        }
    }
}
